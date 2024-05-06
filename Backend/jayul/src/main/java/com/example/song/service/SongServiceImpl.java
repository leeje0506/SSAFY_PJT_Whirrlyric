package com.example.song.service;

import com.example.song.config.WebClientService;
import com.example.song.domain.Lyrics;
import com.example.song.domain.Song;
import com.example.song.dto.req.SongRequestDto;
import com.example.song.dto.res.SongResultDto;
import com.example.song.repository.LyricsRepository;
import com.example.song.repository.SongRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SongServiceImpl implements SongService {

    private final WebClientService webClientService;
    private final SongRepository songRepository;
    private final LyricsRepository lyricsRepository;

    @Autowired
    public SongServiceImpl(WebClientService webClientService, SongRepository songRepository, LyricsRepository lyricsRepository) {
        this.webClientService = webClientService;
        this.songRepository = songRepository;
        this.lyricsRepository = lyricsRepository;
    }

    @Override
    public SongResultDto createSong(SongRequestDto requestDto) {
        // 사용자 입력을 포맷에 맞게 조합
        String formattedLyrics = formatLyrics(requestDto);
//        Map<String, Object> results = webClientService.post("https://api.sunoaiapi.com/api/v1/","gateway/generate/music", requestDto );

        // API 요청을 위한 JSON 객체 생성
        Map<String, Object> apiRequest = new HashMap<>();
        apiRequest.put("title", requestDto.getTitle());
        apiRequest.put("tags", requestDto.getGenre());
        apiRequest.put("prompt", formattedLyrics);
        apiRequest.put("mv", "chirp-v3-0");
        apiRequest.put("continue_at", 120);
        apiRequest.put("continue_clip_id", "");
        Map<String, Object> response = webClientService.post("https://api.sunoaiapi.com/api/v1/","/gateway/generate/music", apiRequest );
//        Map<String, Object> response = webClientService.post("/gateway/generate/music", apiRequest);

        JSONObject resultJson = new JSONObject(response);
        JSONArray data = (JSONArray) resultJson.get("data");
        JSONObject songData = data.getJSONObject(0);

        Lyrics lyrics = new Lyrics();
        lyrics.setIntro(requestDto.getIntro());
        lyrics.setVerse1(requestDto.getVerse1());
        lyrics.setVerse2(requestDto.getVerse2());
        lyrics.setChorus(requestDto.getChorus());
        lyrics.setBridge(requestDto.getBridge());
        lyrics.setOutro(requestDto.getOutro());
        lyricsRepository.save(lyrics);

        Song song = new Song();
        song.setTitle(songData.getString("title"));
        song.setLyrics(lyrics);
        song.setSongUrl(songData.getString("song_id"));
        songRepository.save(song);

        return new SongResultDto(song.getTitle(), song.getSongUrl(), requestDto.getGenre(), formattedLyrics);
    }

    // 사용자 입력 가사를 포맷에 맞게 조합
    private String formatLyrics(SongRequestDto requestDto) {
        return String.format("[intro]\n%s\n[outro]\n%s", requestDto.getIntro(), requestDto.getOutro());
    }

}