package com.example.song.service;

import com.example.song.dto.req.SongRequestDto;
import com.example.song.dto.res.SongResponseDto;

public interface SongService {

    SongResponseDto createSong(SongRequestDto requestDto);
}
