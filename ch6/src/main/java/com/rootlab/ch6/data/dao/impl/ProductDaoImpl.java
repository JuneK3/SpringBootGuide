package com.rootlab.ch6.data.dao.impl;

import com.rootlab.ch6.data.dao.ProductDao;
import com.rootlab.ch6.data.entity.Product;
import com.rootlab.ch6.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductDaoImpl implements ProductDao {

	private final ProductRepository productRepository;

	@Autowired
	public ProductDaoImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Product insertProduct(Product product) {
		Product savedProduct = productRepository.save(product);
		return savedProduct;
	}

	@Override
	public Product selectProduct(Long number) {
		Product product = productRepository.findById(number)
				.orElseThrow(() ->
						new RuntimeException("해당 number에 해당하는 Product가 존재하지 않습니다.")
				);
		return product;
	}

	@Override
	public Product updateProductName(Long number, String name) {
		Product product = productRepository.findById(number)
				.orElseThrow(() ->
						new RuntimeException("해당 number에 해당하는 Product가 존재하지 않습니다.")
				);
		product.setName(name);
		product.setUpdatedAt(LocalDateTime.now());
		Product updatedProduct = productRepository.save(product);
		return updatedProduct;
	}

	@Override
	public void deleteProduct(Long number) {
		Product product = productRepository.findById(number)
//				.orElseThrow(() -> {
//					return new RuntimeException("해당 number에 해당하는 Product가 존재하지 않습니다.");
//				})
				.orElseThrow(() ->
						new RuntimeException("해당 number에 해당하는 Product가 존재하지 않습니다.")
				);
		productRepository.delete(product);
	}
}
