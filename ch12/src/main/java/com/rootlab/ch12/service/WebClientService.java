package com.rootlab.ch12.service;

import com.rootlab.ch12.dto.MemberDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * WebClient는 RestTemplate이 할 수 있는 동기호출을 할 수 있고 비동기 호출도 가능하다.
 * 하지만 RestTemplate은 WebClient가 가능한 비동기 호출을 할 수 없다.
 * RestTemplate가 deprecated되어 Webclient를 사용하는 것이 권장된다.
 */

@Service
public class WebClientService {

	public String getName() {
		WebClient webClient = WebClient.builder()
				.baseUrl("http://localhost:9090")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();

		return webClient.get()
				.uri("/api/v1/crud-api")
				.retrieve()
				.bodyToMono(String.class)
				.block();
	}

	public String getNameWithPathVariable() {
		WebClient webClient = WebClient.create("http://localhost:9090");

		ResponseEntity<String> responseEntity = webClient.get()
				.uri(uriBuilder -> uriBuilder
						.path("/api/v1/crud-api/{name}")
						.build("Hongik"))
				.retrieve()
				.toEntity(String.class)
				.block();

		return responseEntity.getBody();
	}

	public String getNameWithParameter() {
		WebClient webClient = WebClient.create("http://localhost:9090");

		return webClient
				.get()
				.uri(uriBuilder -> uriBuilder
						.path("/api/v1/crud-api")
						.queryParam("name", "hongik")
						.build())
				.exchangeToMono(response -> {
					if (response.statusCode().equals(HttpStatus.OK)) {
						return response.bodyToMono(String.class);
					} else {
						return response.createException().flatMap(Mono::error);
					}
				})
				.block();
	}

	public ResponseEntity<MemberDto> postWithParam() {
		WebClient webClient = WebClient.builder()
				.baseUrl("http://localhost:9090")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();

		return webClient.post().uri(uriBuilder -> uriBuilder
						.path("/api/v1/crud-api/param")
						.queryParam("name", "hongik")
						.queryParam("email", "hongik@hongik.ac.kr")
						.queryParam("team", "hice")
						.build())
				.retrieve()
				.toEntity(MemberDto.class)
				.block();
	}

	public ResponseEntity<MemberDto> postWithBody() {
		WebClient webClient = WebClient.builder()
				.baseUrl("http://localhost:9090")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();

		MemberDto memberDto = new MemberDto();
		memberDto.setName("hongik");
		memberDto.setEmail("hongik@hongik.ac.kr");
		memberDto.setTeam("hice");

		return webClient.post().uri(uriBuilder -> uriBuilder
						.path("/api/v1/crud-api/body")
						.build())
				.bodyValue(memberDto)
				.retrieve()
				.toEntity(MemberDto.class)
				.block();
	}

	public ResponseEntity<MemberDto> postWithHeader() {
		WebClient webClient = WebClient.builder()
				.baseUrl("http://localhost:9090")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();

		MemberDto memberDto = new MemberDto();
		memberDto.setName("hongik");
		memberDto.setEmail("hongik@hongik.ac.kr");
		memberDto.setTeam("hice");

		return webClient
				.post()
				.uri(uriBuilder -> uriBuilder
						.path("/api/v1/crud-api/add-header")
						.build())
				.header("my-header", "Wikibooks API")
				.bodyValue(memberDto)
				.retrieve()
				.toEntity(MemberDto.class)
				.block();
	}
}
