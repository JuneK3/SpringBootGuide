package com.rootlab.ch9.data.repository;

import com.rootlab.ch9.data.entity.Product;
import com.rootlab.ch9.data.entity.Provider;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class ProviderRepositoryTest {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProviderRepository providerRepository;

	@Test
	public void relationshipTest1() {
		Provider provider = new Provider();
		provider.setName("홍익물산");
		providerRepository.save(provider);
		Product product = Product.builder()
				.name("scissor")
				.price(2000)
				.stock(500)
				.provider(provider)
				.build();
		Product savedProduct = productRepository.save(product);
		Product foundProduct = productRepository.findById(savedProduct.getNumber()).orElseThrow(RuntimeException::new);

		System.out.println("product : " + foundProduct);
		System.out.println("provider : " + foundProduct.getProvider());
	}

	@Test
	public void relationshipTest2() {
		Provider provider = new Provider();
		provider.setName("홍익상사");
		providerRepository.save(provider);

		Product product1 = Product.builder()
				.name("pen")
				.price(2000)
				.stock(100)
				.provider(provider)
				.build();

		Product product2 = Product.builder()
				.name("bag")
				.price(20000)
				.stock(200)
				.provider(provider)
				.build();

		Product product3 = Product.builder()
				.name("note")
				.price(3000)
				.stock(1000)
				.provider(provider)
				.build();

		productRepository.save(product1);
		productRepository.save(product2);
		productRepository.save(product3);

		Provider foundProvider = providerRepository.findById(provider.getId()).orElseThrow(RuntimeException::new);
		// @OneToMany는 Lazy하게 fetch하므로 Eager fetch로 설정해야 오류없이 동작함
		List<Product> products = foundProvider.getProductList();
		for (Product product : products) {
			System.out.println(product);
		}
	}

	@Test
	@Transactional
	public void cascadeTest() {
		Provider provider = savedProvider("새로운 공급업체");
		Product product1 = savedProduct("상품1", 1000, 1000);
		Product product2 = savedProduct("상품2", 500, 1500);
		Product product3 = savedProduct("상품3", 750, 500);

		product1.setProvider(provider);
		product2.setProvider(provider);
		product3.setProvider(provider);

		provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));
		providerRepository.save(provider); // 영속성 전파(PERSIST)

		// Eager fetch로 설정했음에도 오류가 발생하여 @Transactional로 설정하고 테스트
		Provider foundProvider = providerRepository.findById(provider.getId()).orElseThrow(RuntimeException::new);
		List<Product> products = foundProvider.getProductList();
		Assertions.assertThat(products.size()).isEqualTo(3);
	}

	@Test
	@Transactional
	void orphanRemovalTest() {
		Provider provider = savedProvider("새로운 공급업체");
		Product product1 = savedProduct("상품1", 1000, 1000);
		Product product2 = savedProduct("상품2", 500, 1500);
		Product product3 = savedProduct("상품3", 750, 500);

		product1.setProvider(provider);
		product2.setProvider(provider);
		product3.setProvider(provider);

		provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));
		providerRepository.saveAndFlush(provider);

		// save는 영속성 컨텍스트에 저장하고 flush 또는 commit 메소드가 실행될 때 DB에 저장
		// saveAndFlush는 즉시 DB에 데이터를 반영함

		System.out.println("## Before Removal ##");
		System.out.println("## product list ##");
		productRepository.findAll().forEach(System.out::println);

		// 연관관계 제거
		Provider foundProvider = providerRepository.findById(provider.getId()).orElseThrow(RuntimeException::new);
		foundProvider.getProductList().remove(0);

		System.out.println("## After Removal ##");
		System.out.println("## product list ##");
		productRepository.findAll().forEach(System.out::println);
	}

	private Provider savedProvider(String name) {
		Provider provider = new Provider();
		provider.setName(name);
		return provider;
	}

	private Product savedProduct(String name, Integer price, Integer stock) {
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setStock(stock);
		return product;
	}
}