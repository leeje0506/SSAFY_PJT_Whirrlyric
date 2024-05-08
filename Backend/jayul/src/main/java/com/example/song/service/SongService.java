package com.example.song.service;


import com.example.song.dto.req.SongRequestDto;
import com.example.song.dto.res.SongResultDto;

public interface SongService {

    SongResultDto createSong(SongRequestDto requestDto);
}