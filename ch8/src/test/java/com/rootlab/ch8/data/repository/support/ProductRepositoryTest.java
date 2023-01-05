package com.rootlab.ch8.data.repository.support;

import com.rootlab.ch8.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class ProductRepositoryTest {
	@Autowired
	ProductRepository productRepository;

	@Test
	void findByNameTest() {
		Product product1 = Product.builder()
				.name("pen")
				.price(1000)
				.stock(100)
				.build();

		Product product2 = Product.builder()
				.name("pen")
				.price(1500)
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

		List<Product> productList = productRepository.findByNameAndPriceIs1000("pen");

		for (Product product : productList) {
			System.out.println(product.getNumber());
			System.out.println(product.getName());
			System.out.println(product.getPrice());
			System.out.println(product.getStock());
		}
	}
}