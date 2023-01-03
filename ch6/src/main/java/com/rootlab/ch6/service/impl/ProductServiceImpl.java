package com.rootlab.ch6.service.impl;

import com.rootlab.ch6.data.dao.ProductDao;
import com.rootlab.ch6.data.dto.ProductRequestDto;
import com.rootlab.ch6.data.dto.ProductResponseDto;
import com.rootlab.ch6.data.entity.Product;
import com.rootlab.ch6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductDao productDao;

	@Autowired
	public ProductServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public ProductResponseDto getProduct(Long number) {
		Product product = productDao.selectProduct(number);
		return product.toResponseDto();
	}

	@Override
	public ProductResponseDto saveProduct(ProductRequestDto productRequestDto) {
		Product savedProduct = productDao.insertProduct(productRequestDto.toEntity());
		return savedProduct.toResponseDto();
	}

	@Override
	public ProductResponseDto changeProductName(Long number, String name) {
		Product updateProduct = productDao.updateProductName(number, name);
		return updateProduct.toResponseDto();
	}

	@Override
	public void deleteProduct(Long number) {
		productDao.deleteProduct(number);
	}
}
