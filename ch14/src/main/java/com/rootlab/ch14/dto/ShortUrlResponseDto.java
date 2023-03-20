package com.rootlab.ch14.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShortUrlResponseDto {
    private String shortUrl;
    private String orgUrl;
}