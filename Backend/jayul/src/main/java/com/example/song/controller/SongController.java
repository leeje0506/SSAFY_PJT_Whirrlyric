package com.example.song.controller;


import com.example.common.enums.Genre;
import com.example.song.dto.req.SongRequestDto;
import com.example.song.dto.res.*;
import com.example.song.service.SongService;
import java.util.List;
import java.util.Map;
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

    @GetMapping("/lyrics")
    public ResponseEntity<List<Map<String, String>>> getAllSongLyrics() {
        List<Map<String, String>> lyrics = songService.getAllSongLyrics();
        return ResponseEntity.ok(lyrics);
    }

    @GetMapping("/genres")
    public ResponseEntity<List<Genre>> getAllGenres() {
        List<Genre> genres = songService.getAllGenres();
        return ResponseEntity.ok(genres);
    }

    // 가사 파트 목록 가져오기
    @GetMapping("/lyrics/parts")
    public ResponseEntity<List<LyricsPartDto>> getLyricsParts() {
        List<LyricsPartDto> parts = songService.getLyricsParts();
        return ResponseEntity.ok(parts);
    }

    //가이드 라인 가져오기
    @GetMapping("/lyrics/guide")
    public ResponseEntity<LyricsGuideDto> getLyricsGuide() {
        LyricsGuideDto guide = songService.getLyricsGuide();
        return ResponseEntity.ok(guide);
    }

    // 장르 데이터 목록 가져오기
    @GetMapping("/genres/data")
    public ResponseEntity<List<Map<String, Object>>> getGenresData() {
        List<Map<String, Object>> genresData = songService.getGenresData();
        return ResponseEntity.ok(genresData);
    }
}
