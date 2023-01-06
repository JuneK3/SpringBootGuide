package com.rootlab.ch10.service.impl;

import com.rootlab.ch10.data.dto.ProductRequestDto;
import com.rootlab.ch10.data.dto.ProductResponseDto;
import com.rootlab.ch10.data.entity.Product;
import com.rootlab.ch10.data.repository.ProductRepository;
import com.rootlab.ch10.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	private final ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public ProductResponseDto getProduct(Long number) {
		LOGGER.info("[getProduct] input number : {}", number);
		Product product = productRepository.findById(number).orElseThrow(
				() -> new RuntimeException("해당 number에 해당하는 Product가 존재하지 않습니다.")
		);
		LOGGER.info("[getProduct] product number : {}, name : {}", product.getNumber(), product.getName());
		return product.toResponseDto();
	}

	@Override
	public ProductResponseDto saveProduct(ProductRequestDto productRequestDto) {
		LOGGER.info("[saveProduct] productRequestDto : {}", productRequestDto);
		Product product = productRequestDto.toEntity();
//		product.setCreatedAt(LocalDateTime.now());
//		product.setUpdatedAt(LocalDateTime.now());
		Product savedProduct = productRepository.save(product);
		LOGGER.info("[saveProduct] savedProduct : {}", savedProduct);
		return savedProduct.toResponseDto();
	}

	@Override
	public ProductResponseDto changeProductName(Long number, String name) {
		Product product = productRepository.findById(number).orElseThrow(
				() -> new RuntimeException("해당 number에 해당하는 Product가 존재하지 않습니다.")
		);
		product.setName(name);
//		product.setUpdatedAt(LocalDateTime.now());
		Product updatedProduct = productRepository.save(product);
		return updatedProduct.toResponseDto();
	}

	@Override
	public void deleteProduct(Long number) {
		Product product = productRepository.findById(number)
				.orElseThrow(() ->
						new RuntimeException("해당 number에 해당하는 Product가 존재하지 않습니다.")
				);
		productRepository.delete(product);
	}
}

