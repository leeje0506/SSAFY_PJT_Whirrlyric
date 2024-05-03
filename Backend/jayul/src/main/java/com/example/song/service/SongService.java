package com.example.song.service;

import com.example.song.dto.res.SongResponse;
import java.util.List;

public interface SongService {
    SongResponse getSongDetail(int songId);

    List<SongResponse> findSongsByGenre(String genre);
}
