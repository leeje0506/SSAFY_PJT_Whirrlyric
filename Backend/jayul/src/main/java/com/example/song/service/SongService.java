package com.example.song.service;


import com.example.song.config.Genre;
import com.example.song.domain.Song;
import com.example.song.dto.req.SongRequestDto;
import com.example.song.dto.res.SongResultDto;
import java.util.List;
import java.util.Map;


public interface SongService {

    SongResultDto createSong(SongRequestDto requestDto);

    List<Map<String, String>> getAllSongLyrics();

    List<Genre> getAllGenres();
    List<Map<String, Object>> getLyricsParts();
    List<Map<String, Object>> getGenresData();


}