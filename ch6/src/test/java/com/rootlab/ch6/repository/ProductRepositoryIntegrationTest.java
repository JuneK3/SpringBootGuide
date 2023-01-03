package com.rootlab.ch6.repository;

import com.rootlab.ch6.data.entity.Product;
import com.rootlab.ch6.data.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductRepositoryIntegrationTest {
	@Autowired
	private ProductRepository productRepository;

	@Test
	public void basicCRUDTest() {
		/* create */
		// given
		Product givenProduct = Product.builder()
				.name("note")
				.price(1000)
				.stock(500)
				.build();
		// when
		Product savedProduct = productRepository.save(givenProduct);
		// then
		assertThat(savedProduct.getNumber()).isEqualTo(givenProduct.getNumber());
		assertThat(savedProduct.getName()).isEqualTo(givenProduct.getName());
		assertThat(savedProduct.getPrice()).isEqualTo(givenProduct.getPrice());
		assertThat(savedProduct.getStock()).isEqualTo(givenProduct.getStock());

		/* read */
		// when
		Product selectedProduct = productRepository.findById(savedProduct.getNumber())
				.orElseThrow(RuntimeException::new);
		// then
		assertThat(selectedProduct.getNumber()).isEqualTo(givenProduct.getNumber());
		assertThat(selectedProduct.getName()).isEqualTo(givenProduct.getName());
		assertThat(selectedProduct.getPrice()).isEqualTo(givenProduct.getPrice());
		assertThat(selectedProduct.getStock()).isEqualTo(givenProduct.getStock());

		/* update */
		// when
		Product foundProduct = productRepository.findById(selectedProduct.getNumber())
				.orElseThrow(RuntimeException::new);
		foundProduct.setName("paper");
		Product updatedProduct = productRepository.save(foundProduct);
		// then
		assertThat(updatedProduct.getName()).isEqualTo("paper");

		/* delete */
		// when
		productRepository.delete(updatedProduct);
		// then
		assertThat(productRepository.findById(selectedProduct.getNumber()).isPresent()).isFalse();
	}
}
