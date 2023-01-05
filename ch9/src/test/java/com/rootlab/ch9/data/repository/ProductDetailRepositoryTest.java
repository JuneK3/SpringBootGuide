package com.rootlab.ch9.data.repository;

import com.rootlab.ch9.data.entity.Product;
import com.rootlab.ch9.data.entity.ProductDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductDetailRepositoryTest {
	@Autowired
	ProductDetailRepository productDetailRepository;

	@Autowired
	ProductRepository productRepository;

	@Test
	public void saveAndReadTest() {
		Product product = Product.builder()
				.name("스프링 부트 JPA")
				.price(5000)
				.stock(10)
				.build();
		productRepository.save(product);
		ProductDetail productDetail = ProductDetail.builder()
				.description("스프링 부트와 JPA를 함께 볼 수 있는 책")
				.product(product)
				.build();
		ProductDetail savedData = productDetailRepository.save(productDetail);
		ProductDetail foundProductDetail = productDetailRepository.findById(savedData.getId()).orElseThrow(RuntimeException::new);

		System.out.println("savedProduct : " + foundProductDetail.getProduct());
		System.out.println("savedProductDetail : " + foundProductDetail);
	}

}