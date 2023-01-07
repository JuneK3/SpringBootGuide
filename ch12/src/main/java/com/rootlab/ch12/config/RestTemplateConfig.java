package com.rootlab.ch12.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * https://sjh836.tistory.com/141
 * RestTemplate의 단점:
 * RestTemplate은 기본적으로 connection pool을 사용하지 않는다.
 * 따라서 연결할 때마다 tcp connection 을 맺는다.
 * 이때 문제는 close() 이후에 사용된 소켓은 TIME_WAIT 상태가 되는데,
 * 요청량이 많다면 이런 소켓들을 재사용하지 못해 응답이 지연될 것이다.
 * 이런 경우 커넥션풀을 사용해서 해결할 수 있는데,
 * RestTemplate에서 커넥션풀을 적용하려면 HttpClient를 만들어 커넥션풀을 설정하고 setHttpClient()를 해주면 된다.
 */

/**
 * Apache HttpComponents를 사용하는 이유:
 * Apache HttpComponents를 사용하면 커넥션풀과 함께 CloseableHttpClient로 RestTemplate를 설정할 수 있다.
 * HttpCliet의 경우 response content를 netwoek socket에 바로 stream하기 때문에 HTTP Connection이 Response Object에 의해 유지된다.
 * CloseableHttpClient는 close 메소드를 가진 CloseableHttpResponse를 반환하여 시스템 resource deallocation을 보장해준다.
 */

@Configuration
public class RestTemplateConfig {

	@Bean
	public HttpComponentsClientHttpRequestFactory factory() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

		// setMaxConnTotal : 최대 오픈되는 커넥션 수를 제한한다.
		// setMaxConnPerRoute : IP, 포트 1쌍에 대해 수행할 연결 수를 제한한다.

		CloseableHttpClient httpClient = HttpClientBuilder.create()
				.setMaxConnTotal(500)
				.setMaxConnPerRoute(500)
				.build();

		factory.setHttpClient(httpClient);
		factory.setConnectTimeout(2000);
		factory.setReadTimeout(5000);
		return factory;
	}

	@Bean
	public RestTemplate restTemplate(HttpComponentsClientHttpRequestFactory factory) {
		RestTemplate restTemplate = new RestTemplate(factory);
		return restTemplate;
	}

}
