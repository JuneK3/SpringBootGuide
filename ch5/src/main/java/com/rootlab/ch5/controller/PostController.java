package com.rootlab.ch5.controller;

import java.util.Map;

import com.rootlab.ch5.dto.MemberDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

	@RequestMapping(value = "/domain", method = RequestMethod.POST)
	public String postExample(){
		return "Hello Post API";
	}

	// http://localhost:8081/api/v1/post/member
	@PostMapping(value = "/member")
	public String postMember(@RequestBody Map<String, Object> postData) {
		StringBuilder sb = new StringBuilder();
		postData.entrySet().forEach(map -> {
			sb.append(map.getKey() + " : " + map.getValue() + "\n");
		});
		return sb.toString();
	}

	// http://localhost:8081/api/v1/post/member2
	@PostMapping(value = "/member2")
	public String postMemberDto(@RequestBody MemberDto memberDTO) {
		return memberDTO.toString();
	}

}
