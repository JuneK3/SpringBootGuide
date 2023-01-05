package com.rootlab.ch8.data.repository;

import com.querydsl.core.types.Predicate;
import com.rootlab.ch8.data.entity.Product;
import com.rootlab.ch8.data.entity.QProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class QProductRepositoryTest {
	@Autowired
	QProductRepository qProductRepository;

	@BeforeEach
	public void prepareProductData() {
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
	}

	@Test
	public void queryDSLTest1() {
		Predicate predicate = QProduct.product.name.containsIgnoreCase("pen")
				.and(QProduct.product.price.between(1200, 2500));

		Product foundProduct = qProductRepository.findOne(predicate).orElseThrow(RuntimeException::new);
		System.out.println("foundProduct = " + foundProduct);
	}

	@Test
	public void queryDSLTest2() {
		QProduct qProduct = QProduct.product;

		Iterable<Product> productList = qProductRepository.findAll(
				qProduct.name.contains("pen")
						.and(qProduct.price.between(550, 1500))
		);

		for (Product product : productList) {
			System.out.println("product = " + product);
		}
	}

}