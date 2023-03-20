package com.rootlab.ch14.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NaverApiResultDto {
    private String message;
    private String code;
    private Result result;

    @Getter
    @Setter
    public static class Result {
        private String hash;
        private String url;
        private String orgUrl;
    }
}