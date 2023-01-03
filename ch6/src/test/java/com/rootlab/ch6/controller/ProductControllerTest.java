package com.rootlab.ch6.controller;

import com.google.gson.Gson;
import com.rootlab.ch6.data.dto.ProductRequestDto;
import com.rootlab.ch6.data.dto.ProductResponseDto;
import com.rootlab.ch6.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	ProductService productService;

	@Test
	@DisplayName("MockMvc를 통한 Product 데이터 가져오기 테스트")
	public void getProductTest() throws Exception {
		// given
		ProductResponseDto productResponseDto = ProductResponseDto.builder()
				.number(123L)
				.name("pen")
				.price(2100)
				.stock(200)
				.build();
		given(productService.getProduct(123L)).willReturn(productResponseDto);
		String productId = "123";
		// when & then
		mockMvc.perform(MockMvcRequestBuilders.get("/product?number=" + productId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.number").exists())
				.andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.price").exists())
				.andExpect(jsonPath("$.stock").exists())
				.andDo(print());

		verify(productService).getProduct(123L);
	}

	@Test
	@DisplayName("Product 데이터 생성 테스트")
	public void createProductTest() throws Exception {
		// given
		ProductRequestDto productRequestDto = ProductRequestDto.builder()
				.name("pen")
				.price(2100)
				.stock(200)
				.build();
		ProductResponseDto productResponseDto = ProductResponseDto.builder()
				.number(1234L)
				.name("pen")
				.price(2100)
				.stock(200)
				.build();
		given(productService.saveProduct(productRequestDto)).willReturn(productResponseDto);
		Gson gson = new Gson();
		String content = gson.toJson(productRequestDto);
		// when & then
		mockMvc.perform(post("/product")
						.contentType(MediaType.APPLICATION_JSON)
						.content(content))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.number").exists())
				.andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.price").exists())
				.andExpect(jsonPath("$.stock").exists())
				.andDo(print());

		verify(productService).saveProduct(productRequestDto);
	}

}
