package com.rootlab.ch9.data.repository;

import com.rootlab.ch9.data.entity.Category;
import com.rootlab.ch9.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CategoryRepositoryTest {
	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Test
	void relationshipTest() {
		Product product = Product.builder()
				.name("Java의 정석")
				.price(6000)
				.stock(20)
				.build();
		productRepository.save(product);

		Category category = new Category();
		category.setCode("S1");
		category.setName("book");
		category.getProducts().add(product);
		categoryRepository.save(category);

		Category foundCategory = categoryRepository.findById(category.getId()).orElseThrow(RuntimeException::new);
		List<Product> products = foundCategory.getProducts();
		for (Product p : products) {
			// p에는 category_id가 존재하지 않음
			System.out.println(p);
		}
	}

}