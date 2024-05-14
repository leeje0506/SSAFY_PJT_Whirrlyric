package com.example.song.service;

import com.example.member.domain.Member;
import com.example.song.dto.res.SongListResponse;
import com.example.song.dto.res.SongResponse;
import com.example.common.enums.Genre;
import com.example.song.dto.req.SongRequestDto;
import com.example.song.dto.res.*;
import java.util.List;
import java.util.Map;

public interface SongService {

    SongResponse getSongDetail(int songId);

    SongListResponse getSongList();

    SongResultDto createSong(SongRequestDto requestDto, Member member);

    List<Map<String, String>> getAllSongLyrics();

    List<Genre> getAllGenres();

    List<LyricsPartDto> getLyricsParts();

    List<Map<String, Object>> getGenresData();

    LyricsGuideDto getLyricsGuide();

    SongResponse songCountPlus(int id);
}