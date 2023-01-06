package com.rootlab.ch10.controller;

import com.rootlab.ch10.common.Constants;
import com.rootlab.ch10.common.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/exception")
public class ExceptionController {
	private final Logger log = LoggerFactory.getLogger(ExceptionController.class);

	@GetMapping
	public void getRuntimeException() {
		throw new RuntimeException("ExceptionController.getRuntimeException 메소드 호출");
	}

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<Map<String, String>> handleException(RuntimeException e, HttpServletRequest request) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

		log.error("ExceptionController의 handleException 호출, {}, {}", request.getRequestURI(), e.getMessage());

		Map<String, String> map = new HashMap<>();
		map.put("error type", httpStatus.getReasonPhrase());
		map.put("code", "400");
		map.put("message", e.getMessage());

		return new ResponseEntity<>(map, responseHeaders, httpStatus);
	}

	@GetMapping("/custom")
	public void getCustomException() throws CustomException {
		throw new CustomException(Constants.ExceptionClass.PRODUCT, HttpStatus.BAD_REQUEST, "ExceptionController.getCustomException() 호출");
	}
}
