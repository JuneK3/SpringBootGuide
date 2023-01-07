package com.rootlab.ch12.controller;

import com.rootlab.ch12.dto.MemberDto;
import com.rootlab.ch12.service.RestTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest-template")
public class RestTemplateController {
	private final RestTemplateService restTemplateService;

	public RestTemplateController(RestTemplateService restTemplateService) {
		this.restTemplateService = restTemplateService;
	}

	@GetMapping
	public String getName() {
		return restTemplateService.getName();
	}

	@GetMapping("/path-var")
	public String getNameWithPathVariable() {
		return restTemplateService.getNameWithPathVariable();
	}

	@GetMapping("/param")
	public String getNameWithParameter() {
		return restTemplateService.getNameWithParameter();
	}

	@PostMapping("/param")
	public ResponseEntity<MemberDto> postParameter() {
		return restTemplateService.postWithParam();
	}

	@PostMapping("/body")
	public ResponseEntity<MemberDto> postDto() {
		return restTemplateService.postWithBody();
	}

	@PostMapping("/header")
	public ResponseEntity<MemberDto> postWithHeader() {
		return restTemplateService.postWithHeader();
	}
	
}
