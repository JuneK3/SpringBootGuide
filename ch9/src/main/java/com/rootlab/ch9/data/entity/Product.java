package com.rootlab.ch9.data.entity;

import com.rootlab.ch9.data.dto.ProductResponseDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
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

	/* 단방향 참조만 사용하기
	@OneToOne(mappedBy = "product")
//	@JsonBackReference // 순환 참조 계속 발생
	@ToString.Exclude
	private ProductDetail productDetail;
	 */

	@ManyToOne
	@JoinColumn(name = "provider_id")
	@ToString.Exclude // 전체 테스트시 lazy loading 오류가 발생해 추가함
	private Provider provider;

	@ManyToMany
	@ToString.Exclude // 전체 테스트시 lazy loading 오류가 발생해 추가함
	private List<Producer> producers = new ArrayList<>();

	public void addProducer(Producer producer){
		this.producers.add(producer);
	}
}
