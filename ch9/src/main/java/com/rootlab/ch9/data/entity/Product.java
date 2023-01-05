package com.rootlab.ch9.data.entity;

import com.rootlab.ch9.data.dto.ProductResponseDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true) // 부모클래스의 필드를 포함하도록 설정
@EqualsAndHashCode(callSuper = true) // 부모클래스의 필드를 포함하도록 설정
public class Product extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long number;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer price;

	@Column(nullable = false)
	private Integer stock;

	public ProductResponseDto toResponseDto() {
		return ProductResponseDto.builder()
				.number(number)
				.name(name)
				.price(price)
				.stock(stock)
				.build();
	}
}
