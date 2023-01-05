package com.rootlab.ch8.data.repository;

import com.rootlab.ch8.config.IsolatedSpringBootTest;
import com.rootlab.ch8.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// https://mangkyu.tistory.com/264
// @DataJpaTest와 달리 @SpringBootTest는 @Transactional로 테스트 메소드들이 격리되지 않는다.
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@IsolatedSpringBootTest
class ProductRepositoryTest {

	@Autowired
	ProductRepository productRepository;

	@Test
	public void sortingAndPagingTest() {
		// given
		Product product1 = Product.builder()
				.name("pen")
				.price(1000)
				.stock(100)
				.build();

		Product product2 = Product.builder()
				.name("pen")
				.price(5000)
				.stock(300)
				.build();

		Product product3 = Product.builder()
				.name("pen")
				.price(500)
				.stock(50)
				.build();

		productRepository.save(product1);
		productRepository.save(product2);
		productRepository.save(product3);

		System.out.println(productRepository.findByNameOrderByNumberAsc("pen"));
		System.out.println(productRepository.findByNameOrderByNumberDesc("pen"));
		System.out.println(productRepository.findByNameOrderByPriceAscStockDesc("pen"));

		System.out.println(productRepository.findByName("pen", Sort.by(Order.asc("price"))));
		System.out.println(productRepository.findByName("pen", Sort.by(Order.asc("price"), Order.desc("stock"))));
		System.out.println(productRepository.findByName("pen", getSort()));

		System.out.println(productRepository.findByName("pen", PageRequest.of(0, 2)));
		System.out.println(productRepository.findByName("pen", PageRequest.of(1, 2)));
		Page<Product> productPage = productRepository.findByName("pen", PageRequest.of(0, 2));
		System.out.println(productPage.getContent());

		System.out.println(productRepository.findByName("pen", PageRequest.of(0, 2, Sort.by(Order.asc("price")))));
		System.out.println(productRepository.findByName("pen", PageRequest.of(0, 2, Sort.by(Order.asc("price")))).getContent());
	}

	private Sort getSort() {
		return Sort.by(
				Order.asc("price"),
				Order.desc("stock")
		);
	}

	@Test
	public void queryAnnotationTest() {
		Product product1 = Product.builder()
				.name("pen")
				.price(1000)
				.stock(100)
				.build();

		Product product2 = Product.builder()
				.name("pen")
				.price(5000)
				.stock(300)
				.build();

		Product product3 = Product.builder()
				.name("pen")
				.price(500)
				.stock(50)
				.build();

		productRepository.save(product1);
		productRepository.save(product2);
		productRepository.save(product3);

		System.out.println(productRepository.findByName("pen"));
		System.out.println(productRepository.findByNameParam("pen"));

		List<Object[]> objects = productRepository.findByNameParam2("pen");
		for (Object[] product : objects) {
			System.out.println(product[0]);
			System.out.println(product[1]);
			System.out.println(product[2]);
		}
	}

	@Test
	public void findByNumberTest() {
		// given
		Product product = Product.builder()
				.name("pen")
				.price(1500)
				.stock(150)
				.build();

		Product savedProduct = productRepository.save(product);

		// when
		Product foundProduct = productRepository.findByNumber(savedProduct.getNumber())
				.orElseThrow(RuntimeException::new);

		// then
		assertThat(savedProduct.getNumber()).isEqualTo(foundProduct.getNumber());
		assertThat(savedProduct.getName()).isEqualTo(foundProduct.getName());
		assertThat(savedProduct.getPrice()).isEqualTo(foundProduct.getPrice());
		assertThat(savedProduct.getStock()).isEqualTo(foundProduct.getStock());
	}

	@Test
	public void findAllByNameTest() {
		// given
		Product product1 = Product.builder()
				.name("pen")
				.price(1000)
				.stock(100)
				.build();

		Product product2 = Product.builder()
				.name("pen")
				.price(5000)
				.stock(300)
				.build();

		Product savedProduct1 = productRepository.save(product1);
		Product savedProduct2 = productRepository.save(product2);

		// when
		List<Product> foundProducts = productRepository.findAllByName("pen");
		Product foundProduct1 = foundProducts.get(0);
		Product foundProduct2 = foundProducts.get(1);

		// then
		assertThat(foundProduct1.getNumber()).isEqualTo(savedProduct1.getNumber());
		assertThat(foundProduct1.getName()).isEqualTo(savedProduct1.getName());
		assertThat(foundProduct1.getPrice()).isEqualTo(savedProduct1.getPrice());
		assertThat(foundProduct1.getStock()).isEqualTo(savedProduct1.getStock());

		assertThat(foundProduct2.getNumber()).isEqualTo(savedProduct2.getNumber());
		assertThat(foundProduct2.getName()).isEqualTo(savedProduct2.getName());
		assertThat(foundProduct2.getPrice()).isEqualTo(savedProduct2.getPrice());
		assertThat(foundProduct2.getStock()).isEqualTo(savedProduct2.getStock());
	}

	@Test
	public void queryByNumberTest() {
		// given
		Product product = Product.builder()
				.name("pen")
				.price(1000)
				.stock(100)
				.build();

		Product savedProduct = productRepository.save(product);

		// when
		Product queriedProduct = productRepository.queryByNumber(savedProduct.getNumber());

		// then
		assertThat(savedProduct.getNumber()).isEqualTo(queriedProduct.getNumber());
		assertThat(savedProduct.getName()).isEqualTo(queriedProduct.getName());
		assertThat(savedProduct.getPrice()).isEqualTo(queriedProduct.getPrice());
		assertThat(savedProduct.getStock()).isEqualTo(queriedProduct.getStock());
	}

	@Test
	public void existsByNumberTest() {
		// given
		Product product = Product.builder()
				.name("pen")
				.price(1000)
				.stock(100)
				.build();

		Product savedProduct = productRepository.save(product);

		// when
		boolean isExisted = productRepository.existsByNumber(savedProduct.getNumber());

		// then
		assertThat(isExisted).isTrue();
	}

	@Test
	public void countByNameTest() {
		// given
		Product product = Product.builder()
				.name("pen")
				.price(1000)
				.stock(100)
				.build();

		productRepository.save(product);

		// when
		Long count = productRepository.countByName("pen");

		// then
		assertThat(count).isEqualTo(1);
	}
}