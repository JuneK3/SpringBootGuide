package com.rootlab.ch6.data.entity;

import com.rootlab.ch6.data.dto.ProductResponseDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long number;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer price;

	@Column(nullable = false)
	private Integer stock;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	public ProductResponseDto toResponseDto() {
		return ProductResponseDto.builder()
				.number(number)
				.name(name)
				.price(price)
				.stock(stock)
				.build();
	}

}