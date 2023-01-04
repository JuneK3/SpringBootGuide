package com.rootlab.ch8.data.entity;

import com.rootlab.ch8.data.dto.ProductResponseDto;
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
@Generated // lombok으로 생성되는 코드들을 테스트 커버리지에서 제외
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
