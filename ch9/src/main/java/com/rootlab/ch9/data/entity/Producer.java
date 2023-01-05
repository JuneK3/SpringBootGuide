package com.rootlab.ch9.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true) // 부모클래스의 필드를 포함하도록 설정
@EqualsAndHashCode(callSuper = true) // 부모클래스의 필드를 포함하도록 설정
public class Producer extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToMany
	@ToString.Exclude
	private List<Product> products = new ArrayList<>();

	public void addProduct(Product product){
		products.add(product);
	}
}
