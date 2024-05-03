//package com.example.song.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.web.reactive.function.client.WebClient;
//
//@Configuration
//public class WebClientConfig {
//
//    @Value("${api.sunoai.key}")
//    private String apiKey;
//
//    // WebClient 전역 설정, 기본 URL과 헤더 설정 포함
//    @Bean
//    public WebClient webClient(WebClient.Builder builder) {
//        return builder
//                .baseUrl("https://api.sunoaiapi.com/api/v1/")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
////                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey) // 인증 헤더에 토큰 추가
//                .defaultHeader("api-key", apiKey) // 인증 헤더에 토큰 추가
//                .build();
//    }
//}
