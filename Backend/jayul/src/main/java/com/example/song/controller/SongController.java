package com.example.song.controller;

import com.example.song.domain.Song;
import com.example.song.config.Genre;
import com.example.song.dto.req.SongRequestDto;
import com.example.song.dto.res.SongResultDto;
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

    // 가사 파트 목록 가져오기 (To.여민)
    @GetMapping("/lyrics/parts")
    public ResponseEntity<List<Map<String, Object>>> getLyricsParts() {
        List<Map<String, Object>> parts = songService.getLyricsParts();
        return ResponseEntity.ok(parts);
    }

    // 장르 데이터 목록 가져오기 (To.여민)
    @GetMapping("/genres/data")
    public ResponseEntity<List<Map<String, Object>>> getGenresData() {
        List<Map<String, Object>> genresData = songService.getGenresData();
        return ResponseEntity.ok(genresData);
    }
}
