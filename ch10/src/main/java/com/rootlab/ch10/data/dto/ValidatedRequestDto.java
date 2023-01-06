package com.rootlab.ch10.data.dto;

import com.rootlab.ch10.config.annotation.Telephone;
import com.rootlab.ch10.data.group.ValidationGroup1;
import com.rootlab.ch10.data.group.ValidationGroup2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidatedRequestDto {

	@NotBlank
	private String name;

	@Email
	private String email;

//	@Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
	@Telephone
	private String phoneNumber;

	@Min(value = 20, groups = ValidationGroup1.class)
	@Max(value = 40, groups = ValidationGroup1.class)
	private int age;

	@Size(min = 0, max = 40)
	private String description;

	@Positive(groups = ValidationGroup2.class)
	private int count;

	@AssertTrue
	private boolean booleanCheck;

}
