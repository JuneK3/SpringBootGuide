package com.rootlab.ch8.audit;

import com.rootlab.ch8.config.JpaAuditingConfig;
import com.rootlab.ch8.data.entity.Product;
import com.rootlab.ch8.data.repository.ProductRepository;
import com.rootlab.ch8.service.impl.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({JpaAuditingConfig.class, ProductServiceImpl.class})
//@AutoConfigureTestDatabase(replace = Replace.NONE)
public class JpaAuditingTest {

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void auditingTest() {
		// given
		Product product = Product.builder()
				.name("pen")
				.price(1200)
				.stock(10)
				.build();
		// when
		Product savedProduct = productRepository.save(product);
		// then
		System.out.println("savedProduct.getCreatedAt() = " + savedProduct.getCreatedAt());
		System.out.println("savedProduct.getUpdatedAt() = " + savedProduct.getUpdatedAt());

		Assertions.assertThat(savedProduct.getName()).isEqualTo(product.getName());
		Assertions.assertThat(savedProduct.getCreatedAt()).isEqualTo(product.getCreatedAt());
		Assertions.assertThat(savedProduct.getUpdatedAt()).isEqualTo(product.getUpdatedAt());
	}

	/* 
	// save한 상태의 시간과 update한 상태의 시간대가 같음
	// save, update 과정을 모두 거치고 나서 쿼리가 실행되어 db에 저장됨
	// updatedAt이 수정되는지 제대로 테스트할 수 없음
	// 테스트 환경이 아닌 local 환경에서 post후 put 요청시 제대로 수정되는 것을 확인함
	@Test
	public void auditingUpdatedAtTest() {
		// given
		Product product = Product.builder()
				.name("pen")
				.price(1200)
				.stock(10)
				.build();
		Product savedProduct = productRepository.saveAndFlush(product);
		System.out.println("savedProduct.getCreatedAt() = " + savedProduct.getCreatedAt());
		System.out.println("savedProduct.getUpdatedAt() = " + savedProduct.getUpdatedAt());
		// when
		productService.changeProductName(savedProduct.getNumber(), "pencil");
		// then
		Product updatedProduct = productRepository.findById(savedProduct.getNumber()).orElseThrow(RuntimeException::new);
		System.out.println("updatedProduct.getCreatedAt() = " + updatedProduct.getCreatedAt());
		System.out.println("updatedProduct.getUpdatedAt() = " + updatedProduct.getUpdatedAt());

		Assertions.assertThat(updatedProduct.getName()).isEqualTo("pencil");
		Assertions.assertThat(updatedProduct.getCreatedAt()).isEqualTo(savedProduct.getCreatedAt());
		Assertions.assertThat(updatedProduct.getUpdatedAt()).isNotEqualTo(savedProduct.getUpdatedAt());
	}
	*/
}
