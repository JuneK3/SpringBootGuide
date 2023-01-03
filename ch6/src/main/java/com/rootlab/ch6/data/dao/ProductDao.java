package com.rootlab.ch6.data.dao;

import com.rootlab.ch6.data.entity.Product;

public interface ProductDao {
	Product insertProduct(Product product);

	Product selectProduct(Long number);

	Product updateProductName(Long number, String name);

	void deleteProduct(Long number);
}
