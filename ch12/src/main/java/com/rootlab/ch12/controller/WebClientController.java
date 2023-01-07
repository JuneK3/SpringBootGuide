package com.rootlab.ch12.controller;

import com.rootlab.ch12.dto.MemberDto;
import com.rootlab.ch12.service.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web-client")
public class WebClientController {
	private final WebClientService webClientService;

	@Autowired
	public WebClientController(WebClientService webClientService) {
		this.webClientService = webClientService;
	}

	@GetMapping
	public String getName() {
		return webClientService.getName();
	}

	@GetMapping("/path-var")
	public String getNameWithPathVariable() {
		return webClientService.getNameWithPathVariable();
	}

	@GetMapping("/param")
	public String getNameWithParameter() {
		return webClientService.getNameWithParameter();
	}

	@PostMapping("/param")
	public ResponseEntity<MemberDto> postParameter() {
		return webClientService.postWithParam();
	}

	@PostMapping("/body")
	public ResponseEntity<MemberDto> postDto() {
		return webClientService.postWithBody();
	}

	@PostMapping("/header")
	public ResponseEntity<MemberDto> postWithHeader() {
		return webClientService.postWithHeader();
	}

}

