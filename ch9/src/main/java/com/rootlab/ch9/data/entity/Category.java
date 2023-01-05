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
public class Category extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String code;

	private String name;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id") // foreign key는 반대편 Table에 생성됨
	private List<Product> products = new ArrayList<>();

}