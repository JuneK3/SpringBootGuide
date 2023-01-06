package com.rootlab.ch10.controller;


import javax.validation.Valid;

import com.rootlab.ch10.data.dto.ValidRequestDto;
import com.rootlab.ch10.data.dto.ValidatedRequestDto;
import com.rootlab.ch10.data.group.ValidationGroup1;
import com.rootlab.ch10.data.group.ValidationGroup2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validation")
public class ValidationController {

	@PostMapping("/valid")
	public ResponseEntity<String> checkValidationByValid(
			@Valid @RequestBody ValidRequestDto validRequestDto) {
		return ResponseEntity.status(HttpStatus.OK).body(validRequestDto.toString());
	}

	/** @Valid와 @Validated - 참고: https://mangkyu.tistory.com/174
	 * @Valid는 기본적으로 컨트롤러에서만 동작하며 기본적으로 다른 계층에서는 검증이 되지 않는다.
	 * 다른 계층에서 파라미터를 검증하기 위해서는 @Validated와 결합되어야 한다.
	 * 입력 파라미터의 유효성 검증은 컨트롤러에서 최대한 처리하고 넘겨주는 것이 좋다.
	 * 하지만 불가피하게 다른 곳에서 파라미터를 검증해야 하는 경우를 위해
	 * Spring은 AOP기반으로 메소드의 요청을 가로채서 유효성 검증을 진행해주는 @Validated를 지원한다.
	 * @Validated는 JSR 표준 기술이 아니며 Spring 프레임워크에서 제공하는 어노테이션(기능)이다.
	 * 클래스에 @Validated를 붙여주고, 유효성을 검증할 메소드의 파라미터에 @Valid를 붙여주면 유효성 검증이 진행된다.
	 */

	/** @Validated의 그룹 지정 기능 - 코드가 복잡해지므로 사용을 자제해야 할 것 같음 이러한 기능이 있다는 것만 참고
	 * @Validated에 특정 그룹을 지정하지 않는 경우: groups가 없는 속성들만 처리
	 * @Validated에 특정 그룹을 지정한 경우: 지정된 클래스를 groups로 가진 제약사항만 처리
	 */

	@PostMapping("/validated")
	public ResponseEntity<String> checkValidation(
			@Validated @RequestBody ValidatedRequestDto validatedRequestDto) {
		return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
	}

	@PostMapping("/validated/group1")
	public ResponseEntity<String> checkValidation1(
			@Validated(ValidationGroup1.class) @RequestBody ValidatedRequestDto validatedRequestDto) {
		return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
	}

	@PostMapping("/validated/group2")
	public ResponseEntity<String> checkValidation2(
			@Validated(ValidationGroup2.class) @RequestBody ValidatedRequestDto validatedRequestDto) {
		return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
	}

	@PostMapping("/validated/all-group")
	public ResponseEntity<String> checkValidation3(
			@Validated({ValidationGroup1.class,
					ValidationGroup2.class}) @RequestBody ValidatedRequestDto validatedRequestDto) {
		return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
	}
}
