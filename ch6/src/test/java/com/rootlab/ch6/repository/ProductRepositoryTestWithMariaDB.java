package com.rootlab.ch6.repository;

import com.rootlab.ch6.data.entity.Product;
import com.rootlab.ch6.data.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@Disabled
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryTestWithMariaDB {
	@Autowired
	private ProductRepository productRepository;

	@Test
	public void saveProductTest() {
		// given
		Product givenProduct = Product.builder()
				.name("pen")
				.price(2100)
				.stock(200)
				.build();
		// when
		Product savedProduct = productRepository.save(givenProduct);
		// then
		Assertions.assertThat(savedProduct.getName()).isEqualTo(givenProduct.getName());
		Assertions.assertThat(savedProduct.getPrice()).isEqualTo(givenProduct.getPrice());
		Assertions.assertThat(savedProduct.getStock()).isEqualTo(givenProduct.getStock());
	}

	@Test
	public void selectProductTest() {
		// given
		Product givenProduct = Product.builder()
				.name("pen")
				.price(2100)
				.stock(200)
				.build();
		Product savedProduct = productRepository.saveAndFlush(givenProduct);
		// when
		Product foundProduct = productRepository.findById(savedProduct.getNumber()).orElseThrow(RuntimeException::new);
		// then
		Assertions.assertThat(foundProduct.getName()).isEqualTo(savedProduct.getName());
		Assertions.assertThat(foundProduct.getPrice()).isEqualTo(savedProduct.getPrice());
		Assertions.assertThat(foundProduct.getStock()).isEqualTo(savedProduct.getStock());
	}
}
