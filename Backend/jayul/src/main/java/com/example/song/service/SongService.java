package com.example.song.service;



import com.example.common.enums.Genre;
import com.example.song.dto.req.SongRequestDto;
import com.example.song.dto.res.*;
import java.util.List;
import java.util.Map;


public interface SongService {

    SongResultDto createSong(SongRequestDto requestDto);

    List<Map<String, String>> getAllSongLyrics();

    List<Genre> getAllGenres();

    List<LyricsPartDto> getLyricsParts();

    List<Map<String, Object>> getGenresData();

    LyricsGuideDto getLyricsGuide();
}