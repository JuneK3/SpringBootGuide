package com.rootlab.ch8.data.repository.support;

import com.rootlab.ch8.data.entity.Product;

import java.util.List;

// 필요한 쿼리를 작성할 메소드를 정의하는 인터페이스
public interface ProductRepositoryCustom {
	List<Product> findByNameAndPriceIs1000(String name);
}
