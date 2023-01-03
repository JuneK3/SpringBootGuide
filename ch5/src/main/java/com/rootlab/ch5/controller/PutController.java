package com.rootlab.ch5.controller;

import com.rootlab.ch5.dto.MemberDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put")
public class PutController {

	// http://localhost:8081/api/v1/put/default
	@PutMapping(value = "/default")
	public String putMethod() {
		return "Hello World!";
	}

	// http://localhost:8081/api/v1/put/member
	@PutMapping(value = "/member")
	public String putMember(@RequestBody Map<String, Object> putData) {
		StringBuilder sb = new StringBuilder();
		putData.entrySet().forEach(map -> {
			sb.append(map.getKey() + " : " + map.getValue() + "\n");
		});
		return sb.toString();
	}

	// http://localhost:8081/api/v1/put/member1
	@PutMapping(value = "/member1")
	public String putMemberDto1(@RequestBody MemberDto memberDto) {
		return memberDto.toString();
	}

	// http://localhost:8081/api/v1/put/member2
	@PutMapping(value = "/member2")
	public MemberDto putMemberDto2(@RequestBody MemberDto memberDto) {
		return memberDto;
	}

	// http://localhost:8081/api/v1/put/member3
//	@ApiResponses(value = {
//			@ApiResponse(responseCode = "202", description = "Member 정보 수정 성공", content = @Content(schema = @Schema(implementation = MemberDto.class)))
//	})
	@Operation(
			summary = "Member 정보 수정",
			description = "json값을 받아 Member 정보를 수정합니다.",
			responses = {
					@ApiResponse(responseCode = "202", description = "Member 정보 수정 성공", content = @Content(schema = @Schema(implementation = MemberDto.class)))
			}
	)
	@PutMapping(value = "/member3")
	public ResponseEntity<MemberDto> putMemberDto3(@RequestBody MemberDto memberDto) {
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(memberDto);
	}

}
