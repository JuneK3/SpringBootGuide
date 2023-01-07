package com.rootlab.server.controller;

import com.rootlab.server.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 예제 12.1
@RestController
@RequestMapping("/api/v1/crud-api")
public class CrudController {

	@GetMapping
	public String getName() {
		return "Hongik";
	}

	@GetMapping(value = "/{variable}")
	public String getVariable(@PathVariable String variable) {
		return variable;
	}

	@GetMapping("/param")
	public String getNameWithParam(@RequestParam String name) {
		return "Hello. " + name + "!";
	}

	@PostMapping("/param")
	public ResponseEntity<MemberDto> getMemberFromParam(
			@RequestParam String name,
			@RequestParam String email,
			@RequestParam String team
	) {
		MemberDto memberDto = new MemberDto();
		memberDto.setName(name);
		memberDto.setEmail(email);
		memberDto.setTeam(team);
		return ResponseEntity.status(HttpStatus.OK).body(memberDto);
	}

	@PostMapping("/body")
	public ResponseEntity<MemberDto> getMember(@RequestBody MemberDto request) {
		MemberDto memberDto = new MemberDto();
		memberDto.setName(request.getName());
		memberDto.setEmail(request.getEmail());
		memberDto.setTeam(request.getTeam());
		return ResponseEntity.status(HttpStatus.OK).body(memberDto);
	}

	@PostMapping(value = "/add-header")
	public ResponseEntity<MemberDto> addHeader(
			@RequestHeader("my-header") String header,
			@RequestBody MemberDto memberDto
	) {
		System.out.println(header);
		return ResponseEntity.status(HttpStatus.OK).body(memberDto);
	}
}
