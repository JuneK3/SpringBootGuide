package com.rootlab.ch5.controller;

import com.rootlab.ch5.dto.MemberDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/get")
public class GetController {
	private final Logger LOGGER = LoggerFactory.getLogger(GetController.class);

	// http://localhost:8081/api/v1/get/hello
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String getHello() {
		LOGGER.info("getHello 메소드가 호출되었습니다.");
		return "Hello World";
	}

	// http://localhost:8081/api/v1/get/name
	@GetMapping(value = "/name")
	public String getName() {
		LOGGER.info("getName 메소드가 호출되었습니다.");
		return "Junek3";
	}

	// http://localhost:8081/api/v1/get/variable1/{String 값}
	@GetMapping(value = "/variable1/{variable}")
	public String getVariable1(@PathVariable String variable) {
		LOGGER.info("@PathVariable을 통해 들어온 값 : {}", variable);
		return variable;
	}

	// http://localhost:8081/api/v1/get/variable2/{String 값}
	@GetMapping(value = "/variable2/{variable}")
	public String getVariable2(@PathVariable("variable") String var) {
		return var;
	}

	// http://localhost:8081/api/v1/get/request1?name=kim&email=kim@gmail.com&organization=teamkim
//	@ApiOperation(value = "GET 메소드 예제", notes = "@RequestParam을 활용한 GET Method")
	@Operation(
			summary = "GET 메소드 예제",
			description = "@RequestParam을 활용한 GET Method"
	)
	@GetMapping(value = "/request1")
	public String getRequestParam1(
			@Parameter(description = "이름", required = true) @RequestParam String name,
			@Parameter(description = "이메일", required = true) @RequestParam String email,
			@Parameter(description = "회사", required = true) @RequestParam String organization) {
		return name + " " + email + " " + organization;
	}

	/*
	// http://localhost:8081/api/v1/get/request2?key1=value1&key2=value2
	@GetMapping(value = "/request2")
	public String getRequestParam2(
//			@ApiParam(value = "Map 객체를 활용해 쿼리스트링 값 출력하기", required = true)
			@Parameter(name = "param", description = "Map 객체를 활용해 쿼리스트링 값 출력하기", in = ParameterIn.QUERY)
			@RequestParam Map<String, String> param) {
		StringBuilder sb = new StringBuilder();
		param.entrySet().forEach(map -> {
			sb.append(map.getKey() + " : " + map.getValue() + "\n");
		});
		return sb.toString();
	}
	 */

	// http://localhost:8081/api/v1/get/request3?name=value1&email=value2&organization=value3
//	@ApiOperation(value = "GET 메소드 예제", notes = "DTO를 활용한 GET Method")
	@Operation(
			summary = "GET 메소드 예제",
			description = "DTO를 활용한 GET Method"
	)
	@GetMapping(value = "/request3")
	public String getRequestParam3(MemberDto memberDTO) {
		return memberDTO.toString();
	}
}
