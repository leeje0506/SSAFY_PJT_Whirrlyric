package com.example.song.service;

import com.example.song.domain.Song;
import com.example.song.dto.res.SongResponse;
import com.example.song.repository.SongCustomRepository;
import com.example.song.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService{
    private final SongRepository songRepository;
    private final SongCustomRepository songCustomRepository;

    @Override
    public SongResponse getSongDetail(int songId) {
        Song song = songCustomRepository.findSongBySongId(songId);

        return SongResponse.builder()
            .song(song)
            .nickname(song.getMember().getNickname())
            .build();
    }
}
