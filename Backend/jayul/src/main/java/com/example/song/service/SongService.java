package com.example.song.service;

import com.example.song.dto.res.SongListResponse;
import com.example.song.dto.res.SongResponse;

public interface SongService {
    SongResponse getSongDetail(int songId);

    SongListResponse getSongList();
}
