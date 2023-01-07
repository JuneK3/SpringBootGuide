package com.rootlab.ch12.service;

import com.rootlab.ch12.dto.MemberDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {
	public String getName() {
		URI uri = UriComponentsBuilder.fromUriString("http://localhost:9090")
				.path("/api/v1/crud-api")
				.encode()
				.build()
				.toUri();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		return responseEntity.getBody();
	}

	public String getNameWithPathVariable() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")
				.path("/api/v1/crud-api/{name}")
				.encode()
				.build()
				.expand("hongik") // 복수의 값을 넣어야할 경우 , 를 추가하여 구분
				.toUri();

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		return responseEntity.getBody();
	}

	public String getNameWithParameter() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")
				.path("/api/v1/crud-api/param")
				.queryParam("name", "hongik")
				.encode()
				.build()
				.toUri();

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
		return responseEntity.getBody();
	}

	public ResponseEntity<MemberDto> postWithParam() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")
				.path("/api/v1/crud-api/param")
				.queryParam("name", "hongik")
				.queryParam("email", "hongik@hongik.ac.kr")
				.queryParam("team", "hice")
				.encode()
				.build()
				.toUri();

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> request = new HttpEntity<>("", headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MemberDto> responseEntity = restTemplate.postForEntity(uri, request, MemberDto.class);
		return responseEntity;
	}

	public ResponseEntity<MemberDto> postWithBody() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")
				.path("/api/v1/crud-api/body")
				.encode()
				.build()
				.toUri();

		MemberDto memberDto = new MemberDto();
		memberDto.setName("hongik");
		memberDto.setEmail("hongik@hongik.ac.kr");
		memberDto.setTeam("hice");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<MemberDto> request = new HttpEntity<>(memberDto, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MemberDto> responseEntity = restTemplate.postForEntity(uri, request, MemberDto.class);
		return responseEntity;
	}

	public ResponseEntity<MemberDto> postWithHeader() {
		URI uri = UriComponentsBuilder
				.fromUriString("http://localhost:9090")
				.path("/api/v1/crud-api/add-header")
				.encode()
				.build()
				.toUri();

		MemberDto memberDto = new MemberDto();
		memberDto.setName("hongik");
		memberDto.setEmail("hongik@hongik.ac.kr");
		memberDto.setTeam("hice");

		RequestEntity<MemberDto> requestEntity = RequestEntity
				.post(uri)
				.header("my-header", "Wikibooks API")
				.body(memberDto);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MemberDto> responseEntity = restTemplate.exchange(requestEntity, MemberDto.class);
		return responseEntity;
	}
}
