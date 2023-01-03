package com.rootlab.ch6.service;

import com.rootlab.ch6.data.dto.ProductRequestDto;
import com.rootlab.ch6.data.dto.ProductResponseDto;
import com.rootlab.ch6.data.entity.Product;
import com.rootlab.ch6.data.repository.ProductRepository;
import com.rootlab.ch6.service.impl.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// SpringExtension 클래스는 Junit5의 테스트에 Spring TestContext Framework를 통합하는 역할을 수행한다.
// @ExtendWith(SpringExtension.class)를 사용해 Junit5의 테스트에서 Spring TestContext를 사용하도록 설정한다.
// ProductService를 @Autowired로 주입받기 위해 @Import를 사용해야 한다.
@ExtendWith(SpringExtension.class)
@Import({ProductServiceImpl.class})
public class ProductServiceTestWithSpringExtension {

	@MockBean
	private ProductRepository productRepository;

	@Autowired
	private ProductServiceImpl productService;

	@Test
	public void getProductTest() {
		// given
		Product givenProduct = Product.builder()
				.number(123L)
				.name("pen")
				.price(2100)
				.stock(200)
				.build();
		when(productRepository.findById(123L)).thenReturn(Optional.of(givenProduct));
		// when
		ProductResponseDto productResponseDto = productService.getProduct(123L);
		// then
		Assertions.assertThat(productResponseDto.getNumber()).isEqualTo(givenProduct.getNumber());
		Assertions.assertThat(productResponseDto.getName()).isEqualTo(givenProduct.getName());
		Assertions.assertThat(productResponseDto.getPrice()).isEqualTo(givenProduct.getPrice());
		Assertions.assertThat(productResponseDto.getStock()).isEqualTo(givenProduct.getStock());
		verify(productRepository).findById(123L);
	}

	@Test
	public void saveProductTest() {
		// given
		when(productRepository.save(any(Product.class))).then(returnsFirstArg());
		// when
		ProductRequestDto productRequestDto = ProductRequestDto.builder()
				.name("pen")
				.price(2100)
				.stock(200)
				.build();
		ProductResponseDto productResponseDto = productService.saveProduct(productRequestDto);
		// then
		Assertions.assertThat(productResponseDto.getName()).isEqualTo(productRequestDto.getName());
		Assertions.assertThat(productResponseDto.getPrice()).isEqualTo(productRequestDto.getPrice());
		Assertions.assertThat(productResponseDto.getStock()).isEqualTo(productRequestDto.getStock());
		verify(productRepository).save(any());
	}

}
