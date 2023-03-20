package com.rootlab.ch14.controller;

import com.rootlab.ch14.service.ShortUrlService;
import com.rootlab.ch14.dto.ShortUrlResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/short-url")
public class ShortUrlController {
    @Value("${short-url.client.id}")
    private String CLIENT_ID;

    @Value("${short-url.client.secret}")
    private String CLIENT_SECRET;

    private final ShortUrlService shortUrlService;

    @Autowired
    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ShortUrlResponseDto generateShortUrl(String originalUrl) {
        return shortUrlService.generateShortUrl(CLIENT_ID, CLIENT_SECRET, originalUrl);
    }

    @GetMapping
    public ShortUrlResponseDto getShortUrl(@RequestParam String originalUrl) {
        return shortUrlService.getShortUrl(CLIENT_ID, CLIENT_SECRET, originalUrl);
    }

    @DeleteMapping(produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> deleteShortUrl(@RequestParam String url) {
        try {
            shortUrlService.deleteShortUrl(url);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }
}