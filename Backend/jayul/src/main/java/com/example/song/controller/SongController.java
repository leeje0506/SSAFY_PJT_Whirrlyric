package com.example.song.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import com.example.song.dto.req.SongRequestDto;
import com.example.song.dto.res.SongResponseDto;
import com.example.song.service.SongService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;

    @PostMapping
    public Mono<ResponseEntity<SongResponseDto>> createSong(@RequestBody SongRequestDto requestDto) {
        return songService.createSong(requestDto)
                .map(response -> ResponseEntity.ok(response))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
