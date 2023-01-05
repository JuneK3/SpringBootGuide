package com.rootlab.ch8.data.repository;

import com.rootlab.ch8.config.TestConfig;
import com.rootlab.ch8.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@DataJpaTest
@Import({QProductRepositorySupport.class, TestConfig.class})
class QProductRepositorySupportTest {
	@Autowired
	private QProductRepositorySupport qProductRepositorySupport;

	@Autowired
	private QProductRepository qProductRepository;

	@Test
	void findByNameAndPriceIs500Test(){
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

		qProductRepository.save(product1);
		qProductRepository.save(product2);
		qProductRepository.save(product3);

		List<Product> productList = qProductRepositorySupport.findByNameAndPriceIs500("pen");

		for (Product product : productList) {
			System.out.println(product.getNumber());
			System.out.println(product.getName());
			System.out.println(product.getPrice());
			System.out.println(product.getStock());
		}
	}
	
	
}