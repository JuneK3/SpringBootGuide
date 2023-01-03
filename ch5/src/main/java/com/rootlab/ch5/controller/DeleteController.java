package com.rootlab.ch5.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delete")
public class DeleteController {

	// http://localhost:8081/api/v1/delete/{String 값}
	@DeleteMapping(value = "/{variable}")
	public String DeleteVariable(@PathVariable String variable) {
		return variable;
	}

	// http://localhost:8081/api/v1/delete/request1?email=value
	@DeleteMapping(value = "/request1")
	public String getRequestParam1(@RequestParam String email) {
		return "e-mail : " + email;
	}

}
