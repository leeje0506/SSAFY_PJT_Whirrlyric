package com.example.song.controller;


import com.example.member.domain.Member;
import com.example.song.dto.res.SongListResponse;
import com.example.song.dto.res.SongResponse;
import com.example.common.enums.Genre;
import com.example.song.dto.req.SongRequestDto;
import com.example.song.dto.res.*;
import com.example.song.service.SongService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getSongDetail(@PathVariable int id, @AuthenticationPrincipal Member member){
        SongResponse response = songService.getSongDetail(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getSongList(@AuthenticationPrincipal Member member){
        SongListResponse response = songService.getSongList();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<SongResultDto> createSong(@RequestBody SongRequestDto requestDto, @AuthenticationPrincipal Member member) {
        SongResultDto songResultDto = songService.createSong(requestDto,member);
        return ResponseEntity.ok(songResultDto);
    }

    @GetMapping("/lyrics")
    public ResponseEntity<List<Map<String, String>>> getAllSongLyrics(@AuthenticationPrincipal Member member) {
        List<Map<String, String>> lyrics = songService.getAllSongLyrics();
        return ResponseEntity.ok(lyrics);
    }

    @GetMapping("/genres")
    public ResponseEntity<List<Genre>> getAllGenres(@AuthenticationPrincipal Member member) {
        List<Genre> genres = songService.getAllGenres();
        return ResponseEntity.ok(genres);
    }

    // 가사 파트 목록 가져오기
    @GetMapping("/lyrics/parts")
    public ResponseEntity<List<LyricsPartDto>> getLyricsParts(@AuthenticationPrincipal Member member) {
        List<LyricsPartDto> parts = songService.getLyricsParts();
        return ResponseEntity.ok(parts);
    }

    //가이드 라인 가져오기
    @GetMapping("/lyrics/guide")
    public ResponseEntity<LyricsGuideDto> getLyricsGuide(@AuthenticationPrincipal Member member) {
        LyricsGuideDto guide = songService.getLyricsGuide();
        return ResponseEntity.ok(guide);
    }

    // 장르 데이터 목록 가져오기
    @GetMapping("/genres/data")
    public ResponseEntity<List<Map<String, Object>>> getGenresData(@AuthenticationPrincipal Member member) {
        List<Map<String, Object>> genresData = songService.getGenresData();

        return ResponseEntity.ok(genresData);
    }

    @PatchMapping("/{id}/play")
    public ResponseEntity<?> songCountPlus(@PathVariable int id){
        return ResponseEntity.ok(songService.songCountPlus(id));
    }

}
