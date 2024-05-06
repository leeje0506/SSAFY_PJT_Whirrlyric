package com.example.song.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class WebClientService {

    @Value("${api.sunoai.key}")
    private String apiKey;
    private String path;

    public Map<String, Object> get(String baseUrl, String path){

        // buffer 설정 max로 변경
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(-1)) // to unlimited memory size
                .build();

        // webClient 기본 설정
        WebClient webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .exchangeStrategies(exchangeStrategies)
                .build();
        // api 요청
        Map response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(path)
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        // 결과 확인
        log.info(response.toString());

        return response;
    }

    public List<Map> getJSONArray(String baseUrl, String path){

        // buffer 설정 max로 변경
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(-1)) // to unlimited memory size
                .build();

        // webClient 기본 설정
        WebClient webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .exchangeStrategies(exchangeStrategies)
                .build();

        // api 요청
        List<Map> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(path)
                        .build())
                .retrieve()
                .bodyToFlux(Map.class)
                .collectList()
                .block();

        // 결과 확인
        log.info(response.toString());

        return  response;
    }


    public Map<String, Object> post(String baseUrl, Object Dto, Map<String, Object> apiRequest){

        // buffer 설정 max로 변경
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(-1)) // to unlimited memory size
                .build();

        // webClient 기본 설정
        WebClient webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .exchangeStrategies(exchangeStrategies)
                .build();
        // api 요청
        Map response = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(path)
                        .build())
                .header(HttpHeaders.CONTENT_TYPE)
                .contentType(MediaType.APPLICATION_JSON)
                .header("api-key", apiKey) // <- 하드코딩 되어있던 부분
                .body(BodyInserters.fromValue(Dto))
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        // 결과 확인
        log.info(response.toString());

        return response;
    }




}