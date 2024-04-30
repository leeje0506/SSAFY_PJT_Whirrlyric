package com.example.song.service;

import com.example.song.dto.req.SongRequestDto;
import com.example.song.dto.res.SongResponseDto;
import com.example.song.repository.SongRepository;
import com.example.song.domain.Song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public SongResponseDto createSong(SongRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", "Your API Key");

        HttpEntity<SongRequestDto> entity = new HttpEntity<>(requestDto, headers);
        ResponseEntity<SongResponseDto> response = restTemplate.postForEntity("https://api.sunoaiapi.com/api/v1/gateway/generate/music", entity, SongResponseDto.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            Song song = saveSong(response.getBody());
            return response.getBody();
        } else {
            throw new RuntimeException("API call failed");
        }
    }

    private Song saveSong(SongResponseDto dto) {
        Song song = new Song();
        song.setTitle(dto.getTitle());
        song.setGenre(dto.getGenre());
        song.setLyrics(dto.getLyrics());
        song.setSongUrl(dto.getSongUrl());
        songRepository.save(song);
        return song;
    }
}
