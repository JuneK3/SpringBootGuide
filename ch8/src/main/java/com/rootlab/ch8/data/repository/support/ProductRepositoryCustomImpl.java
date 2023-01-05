package com.rootlab.ch8.data.repository.support;

import com.rootlab.ch8.data.entity.Product;
import com.rootlab.ch8.data.entity.QProduct;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRepositoryCustomImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {

	public ProductRepositoryCustomImpl() {
		super(Product.class);
	}

	@Override
	public List<Product> findByNameAndPriceIs1000(String name) {
		QProduct product = QProduct.product;
		List<Product> productList = from(product)
				.where(product.name.eq(name).and(product.price.eq(1000)))
				.select(product)
				.fetch();
		return productList;
	}
}
