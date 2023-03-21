package com.rootlab.ch14.service;

import com.rootlab.ch14.domain.ShortUrl;
import com.rootlab.ch14.domain.ShortUrlRedisRepository;
import com.rootlab.ch14.domain.ShortUrlRepository;
import com.rootlab.ch14.dto.NaverApiResultDto;
import com.rootlab.ch14.dto.ShortUrlResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;

@Service
public class ShortUrlService {
    private final Logger LOGGER = LoggerFactory.getLogger(ShortUrlService.class);
    private final ShortUrlRepository shortUrlRepository;
    private final ShortUrlRedisRepository shortUrlRedisRepository;

    @Autowired
    public ShortUrlService(ShortUrlRepository shortUrlRepository, ShortUrlRedisRepository shortUrlRedisRepository) {
        this.shortUrlRepository = shortUrlRepository;
        this.shortUrlRedisRepository = shortUrlRedisRepository;
    }

    public ShortUrlResponseDto getShortUrl(String clientId, String clientSecret, String originalUrl) {
        // Cache Logic
        Optional<ShortUrlResponseDto> responseDto = shortUrlRedisRepository.findById(originalUrl);
        if (responseDto.isPresent()) {
            LOGGER.info("[getShortUrl] Cache Data existed.");
            return responseDto.get();
        } else {
            LOGGER.info("[getShortUrl] Cache Data does not existed.");
        }

        ShortUrl foundEntity = shortUrlRepository.findByOrgUrl(originalUrl);

        if (foundEntity == null) {
            return createShortUrl(clientId, clientSecret, originalUrl);
        }

        // Cache Logic
        ShortUrlResponseDto shortUrlResponseDto = new ShortUrlResponseDto(foundEntity.getOrgUrl(), foundEntity.getUrl());
        LOGGER.info("[getShortUrl] Restore Cache Data.");
        shortUrlRedisRepository.save(shortUrlResponseDto);
        return shortUrlResponseDto;
    }

    public ShortUrlResponseDto generateShortUrl(String clientId, String clientSecret, String originalUrl) {

        if (originalUrl.contains("me2.do")) {
            throw new RuntimeException("이미 Naver Api로 단축된 URL입니다.");
        }

        return createShortUrl(clientId, clientSecret, originalUrl);
    }

    private ShortUrlResponseDto createShortUrl(String clientId, String clientSecret, String originalUrl) {
        ResponseEntity<NaverApiResultDto> responseEntity = requestShortUrl(clientId, clientSecret, originalUrl);

        String orgUrl = responseEntity.getBody().getResult().getOrgUrl();
        String shortUrl = responseEntity.getBody().getResult().getUrl();
        String hash = responseEntity.getBody().getResult().getHash();

        ShortUrl shortUrlEntity = ShortUrl.builder()
                .orgUrl(orgUrl)
                .url(shortUrl)
                .hash(hash)
                .build();

        shortUrlRepository.save(shortUrlEntity);

        // Cache Logic
        ShortUrlResponseDto shortUrlResponseDto = new ShortUrlResponseDto(orgUrl, shortUrl);
        shortUrlRedisRepository.save(shortUrlResponseDto);

        return shortUrlResponseDto;
    }

    private ResponseEntity<NaverApiResultDto> requestShortUrl(String clientId, String clientSecret, String originalUrl) {
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/util/shorturl")
                .queryParam("url", originalUrl)
                .encode()
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(uri, HttpMethod.GET, requestEntity, NaverApiResultDto.class);
    }

    public void deleteShortUrl(String url) {
        if (url.contains("me2.do")) {
            deleteByShortUrl(url);
        } else {
            deleteByOriginalUrl(url);
        }
    }

    private void deleteByShortUrl(String shortUrl) {
        ShortUrl fountEntity = shortUrlRepository.findByUrl(shortUrl);
        shortUrlRepository.delete(fountEntity);
    }

    private void deleteByOriginalUrl(String originalUrl) {
        ShortUrl foundShortUrl = shortUrlRepository.findByOrgUrl(originalUrl);
        shortUrlRepository.delete(foundShortUrl);
    }
}