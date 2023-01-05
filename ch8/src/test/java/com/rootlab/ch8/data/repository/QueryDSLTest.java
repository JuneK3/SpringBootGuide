package com.rootlab.ch8.data.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rootlab.ch8.config.TestConfig;
import com.rootlab.ch8.data.entity.Product;
import com.rootlab.ch8.data.entity.QProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@DataJpaTest
@Import({TestConfig.class})
public class QueryDSLTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private JPAQueryFactory jpaQueryFactory;

	@Autowired
	private ProductRepository productRepository;

	@BeforeEach
	public void prepareProductData() {
		Product product1 = Product.builder()
				.name("pen")
				.price(1000)
				.stock(100)
				.build();

		Product product2 = Product.builder()
				.name("pen")
				.price(1500)
				.stock(300)
				.build();

		Product product3 = Product.builder()
				.name("pen")
				.price(500)
				.stock(50)
				.build();

		productRepository.save(product1);
		productRepository.save(product2);
		productRepository.save(product3);
	}

	@Test
	void queryDslTest() {
		JPAQuery<Product> query = new JPAQuery(entityManager);
		QProduct qProduct = QProduct.product;

		List<Product> productList = query
				.from(qProduct)
				.where(qProduct.name.eq("pen"))
				.orderBy(qProduct.price.asc())
				.fetch();

		for (Product product : productList) {
			System.out.println("----------------");
			System.out.println();
			System.out.println("Product Number : " + product.getNumber());
			System.out.println("Product Name : " + product.getName());
			System.out.println("Product Price : " + product.getPrice());
			System.out.println("Product Stock : " + product.getStock());
			System.out.println();
			System.out.println("----------------");
		}
	}

	@Test
	void queryDslTest2() {
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
		QProduct qProduct = QProduct.product;

		List<Product> productList = jpaQueryFactory.selectFrom(qProduct)
				.where(qProduct.name.eq("pen"))
				.orderBy(qProduct.price.asc())
				.fetch();

		for (Product product : productList) {
			System.out.println("----------------");
			System.out.println();
			System.out.println("Product Number : " + product.getNumber());
			System.out.println("Product Name : " + product.getName());
			System.out.println("Product Price : " + product.getPrice());
			System.out.println("Product Stock : " + product.getStock());
			System.out.println();
			System.out.println("----------------");
		}
	}

	@Test
	void queryDslTest3() {
		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
		QProduct qProduct = QProduct.product;

		List<String> productList = jpaQueryFactory
				.select(qProduct.name)
				.from(qProduct)
				.where(qProduct.name.eq("pen"))
				.orderBy(qProduct.price.asc())
				.fetch();

		for (String product : productList) {
			System.out.println("----------------");
			System.out.println("Product Name : " + product);
			System.out.println("----------------");
		}

		List<Tuple> tupleList = jpaQueryFactory
				.select(qProduct.name, qProduct.price)
				.from(qProduct)
				.where(qProduct.name.eq("pen"))
				.orderBy(qProduct.price.asc())
				.fetch();

		for (Tuple product : tupleList) {
			System.out.println("----------------");
			System.out.println("Product Name : " + product.get(qProduct.name));
			System.out.println("Product Price : " + product.get(qProduct.price));
			System.out.println("----------------");
		}
	}

	@Test
	void queryDslTest4() {
		QProduct qProduct = QProduct.product;

		List<String> productList = jpaQueryFactory
				.select(qProduct.name)
				.from(qProduct)
				.where(qProduct.name.eq("pen"))
				.orderBy(qProduct.price.asc())
				.fetch();

		for (String product : productList) {
			System.out.println("----------------");
			System.out.println("Product Name : " + product);
			System.out.println("----------------");
		}
	}

}
