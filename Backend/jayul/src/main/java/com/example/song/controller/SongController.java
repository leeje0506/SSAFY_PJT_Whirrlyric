package com.example.song.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.song.dto.req.SongRequestDto;
import com.example.song.dto.res.SongResponseDto;
import com.example.song.service.SongService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping
    public ResponseEntity<SongResponseDto> createSong(@RequestBody SongRequestDto requestDto) {
        SongResponseDto response = songService.createSong(requestDto);
        return ResponseEntity.ok(response);
    }
}
