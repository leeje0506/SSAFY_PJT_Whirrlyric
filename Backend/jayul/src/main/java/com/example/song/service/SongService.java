package com.example.song.service;


import com.example.song.dto.req.SongRequestDto;
import com.example.song.dto.res.SongResponseDto;
import reactor.core.publisher.Mono;

public interface SongService {
    Mono<SongResponseDto> createSong(SongRequestDto requestDto);
}
