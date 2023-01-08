package com.rootlab.ch13.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rootlab.ch13.data.dto.EntryPointErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 인증 실패시 결과를 처리해주는 로직을 가지고 있는 클래스
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final Logger log = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
						 AuthenticationException ex) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		log.info("[commence] 인증 실패로 response.sendError 발생");

		EntryPointErrorResponse entryPointErrorResponse = new EntryPointErrorResponse();
		entryPointErrorResponse.setMsg("인증이 실패하였습니다.");

		response.setStatus(401);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(objectMapper.writeValueAsString(entryPointErrorResponse));

		// dto 전송방식이 아니어도 바로 error를 전송하는 방식도 가능함
		//response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}
}
