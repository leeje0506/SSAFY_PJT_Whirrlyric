package com.example.song.service;


import com.example.song.dto.req.SongRequestDto;
import com.example.song.dto.res.SongResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SongServiceImpl implements SongService {

    private final WebClient webClient;

    @Autowired
    public SongServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    // SUNO AI API에 노래 생성 요청을 보내고 결과를 Mono로 반환
    @Override
    public Mono<SongResponseDto> createSong(SongRequestDto requestDto) {
        return webClient.post()
            .uri("gateway/generate/music")
            .bodyValue(requestDto)
            .retrieve()
            .bodyToMono(SongResponseDto.class);
    }
}
