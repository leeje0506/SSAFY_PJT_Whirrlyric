package com.example.song.service;


import com.example.song.dto.req.SongRequestDto;
import com.example.song.dto.res.SongResponseDto;
import com.example.song.dto.res.SongResultDto;
import reactor.core.publisher.Mono;

public interface SongService {
    SongResultDto createSong(SongRequestDto requestDto);
}
