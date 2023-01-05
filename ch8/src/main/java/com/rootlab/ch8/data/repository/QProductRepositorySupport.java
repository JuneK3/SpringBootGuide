package com.rootlab.ch8.data.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rootlab.ch8.data.entity.Product;
import com.rootlab.ch8.data.entity.QProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QProductRepositorySupport extends QuerydslRepositorySupport {
	private final JPAQueryFactory jpaQueryFactory;

	@Autowired
	public QProductRepositorySupport(JPAQueryFactory jpaQueryFactory) {
		super(Product.class);
		this.jpaQueryFactory = jpaQueryFactory;
	}

	public List<Product> findByNameAndPriceIs500(String name) {
		QProduct product = QProduct.product;
		return jpaQueryFactory.selectFrom(product)
				.where(product.name.eq(name).and(product.price.eq(500)))
				.fetch();
	}
}
