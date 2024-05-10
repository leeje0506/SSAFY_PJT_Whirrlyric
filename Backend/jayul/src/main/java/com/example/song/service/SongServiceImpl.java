package com.example.song.service;

import com.example.common.enums.Genre;
import com.example.song.domain.Song;
import com.example.song.dto.res.*;
import com.example.song.exception.SongNotFoundException;
import com.example.song.repository.SongCustomRepository;
import com.example.song.repository.SongRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final SongCustomRepository songCustomRepository;

    @Override
    public SongResponse getSongDetail(int songId) {

        Song song = songRepository.findById(songId).orElseThrow(SongNotFoundException::new);

        return SongResponse.builder()
            .song(song)
            .nickname(song.getMember().getNickname())
            .build();
    }

    @Override
    public SongListResponse getSongList() {
        List<SongResponse> popularSongList = songCustomRepository.findSongsSortedByPopularity();
        List<SongResponse> latestSongList = songCustomRepository.findSongsOrderByDate();

        List<GenreList> genreSongList = new ArrayList<>();

        List<SongResponse> SongResponseList = songCustomRepository.findAllSongs();

        for (Genre genre : Genre.values()) {
            List<SongResponse> list = SongResponseList.stream()
                .filter(songResponse -> songResponse.getSong().getGenre() == genre)
                .collect(Collectors.toList());

            GenreList songListByGenre = GenreList.builder()
                .genre(genre)
                .songList(list)
                .build();
            genreSongList.add(songListByGenre);
        }

        return SongListResponse.builder()
            .genreSongList(genreSongList)
            .latestSongList(latestSongList)
            .popularSongList(popularSongList)
            .build();
    }
}
