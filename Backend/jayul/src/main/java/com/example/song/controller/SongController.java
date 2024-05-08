package com.example.song.controller;

import com.example.song.dto.req.SongRequestDto;
import com.example.song.dto.res.SongResultDto;
import com.example.song.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;

    @PostMapping
    public ResponseEntity<SongResultDto> createSong(@RequestBody SongRequestDto requestDto) {

        SongResultDto songResultDto = songService.createSong(requestDto);

        return ResponseEntity.ok(songResultDto);
    }
}
