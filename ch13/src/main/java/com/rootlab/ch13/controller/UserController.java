package com.rootlab.ch13.controller;

import com.rootlab.ch13.data.dto.SignInResultDto;
import com.rootlab.ch13.data.dto.SignUpResultDto;
import com.rootlab.ch13.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sign-api")
public class UserController {
	private final Logger log = LoggerFactory.getLogger(UserController.class);
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/sign-in")
	public SignInResultDto signIn(
			@Parameter(description = "ID", required = true) @RequestParam String id,
			@Parameter(description = "Password", required = true) @RequestParam String password)
			throws RuntimeException {
		log.info("[signIn] 로그인을 시도하고 있습니다. id : {}, pw : ****", id);
		SignInResultDto signInResultDto = userService.signIn(id, password);

		if (signInResultDto.getCode() == 0) {
			log.info("[signIn] 정상적으로 로그인되었습니다. id : {}, token : {}", id, signInResultDto.getToken());
		}
		return signInResultDto;
	}

	@PostMapping(value = "/sign-up")
	public SignUpResultDto signUp(
			@Parameter(description = "ID", required = true) @RequestParam String id,
			@Parameter(description = "비밀번호", required = true) @RequestParam String password,
			@Parameter(description = "이름", required = true) @RequestParam String name,
			@Parameter(description = "권한", required = true) @RequestParam String role) {
		log.info("[signUp] 회원가입을 수행합니다. id : {}, password : ****, name : {}, role : {}", id, name, role);
		SignUpResultDto signUpResultDto = userService.signUp(id, password, name, role);
		log.info("[signUp] 회원가입을 완료했습니다. id : {}", id);
		return signUpResultDto;
	}

	@GetMapping(value = "/exception")
	public void exceptionResponse() throws RuntimeException {
		throw new RuntimeException("접근이 금지되었습니다.");
	}

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<Map<String, String>> exceptionHandler(RuntimeException e) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		log.error("ExceptionHandler 호출, {}, {}", e.getCause(), e.getMessage());

		Map<String, String> map = new HashMap<>();
		map.put("error type", httpStatus.getReasonPhrase());
		map.put("code", "400");
		map.put("message", e.getMessage());
//		map.put("message", "에러 발생");
		return new ResponseEntity<>(map, headers, httpStatus);
	}
}
