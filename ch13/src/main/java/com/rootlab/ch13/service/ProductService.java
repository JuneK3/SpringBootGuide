package com.rootlab.ch13.service;


import com.rootlab.ch13.data.dto.ProductRequestDto;
import com.rootlab.ch13.data.dto.ProductResponseDto;

public interface ProductService {
	ProductResponseDto getProduct(Long number);

	ProductResponseDto saveProduct(ProductRequestDto product);

	ProductResponseDto changeProductName(Long number, String name);

	void deleteProduct(Long number);

}
