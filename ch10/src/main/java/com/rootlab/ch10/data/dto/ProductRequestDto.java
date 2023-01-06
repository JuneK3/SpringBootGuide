package com.rootlab.ch10.data.dto;

import com.rootlab.ch10.data.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDto {
	private String name;

	private int price;

	private int stock;

	public Product toEntity() {
		return Product.builder()
				.name(name)
				.price(price)
				.stock(stock)
				.build();
	}
}
