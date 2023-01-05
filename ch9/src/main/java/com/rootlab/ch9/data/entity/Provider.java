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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Provider extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "provider", fetch = FetchType.EAGER,
			cascade = CascadeType.PERSIST, orphanRemoval = true)
	@ToString.Exclude
	private List<Product> productList = new ArrayList<>();
}
