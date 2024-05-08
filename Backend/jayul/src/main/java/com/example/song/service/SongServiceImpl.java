package com.example.song.service;

import com.example.song.config.WebClientService;
import com.example.song.domain.Song;
import com.example.song.dto.req.SongRequestDto;
import com.example.song.dto.res.SongResultDto;
import com.example.song.repository.SongRepository;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final WebClientService webClientService;
    private final SongRepository songRepository;

    @Override
    public SongResultDto createSong(SongRequestDto requestDto) {
        // 사용자 입력을 포맷에 맞게 조합
        String formattedLyrics = formatLyrics(requestDto);

        // API 요청을 위한 JSON 객체 생성
        Map<String, Object> apiRequest = new HashMap<>();
        apiRequest.put("title", requestDto.getTitle());
        apiRequest.put("tags", requestDto.getGenre());
        apiRequest.put("prompt", formattedLyrics);
        apiRequest.put("mv", "chirp-v3-0");
        apiRequest.put("continue_at", 120);
        apiRequest.put("continue_clip_id", "");
        Map<String, Object> response = webClientService.post("https://api.sunoaiapi.com/api/v1/","gateway/generate/music", apiRequest );

        JSONObject resultJson = new JSONObject(response);
        JSONArray data = (JSONArray) resultJson.get("data");
        JSONObject songData = data.getJSONObject(0);


        Song song = Song.builder()
                .title(songData.getString("title"))
                .lyrics(formattedLyrics)
                .songUrl("cdn1.suno.ai/" + songData.getString( "song_id" ) + ".mp3")
                .build();
        songRepository.save(song);

        return new SongResultDto(song.getTitle(), song.getSongUrl(), requestDto.getGenre(), formattedLyrics);
    }

    // 사용자 입력 가사를 포맷에 맞게 조합
    private String formatLyrics(SongRequestDto requestDto) {
        return String.format("[intro]\n%s\n[verse1]\n%s\n[verse2]\n%s\n[chorus]\n%s\n[bridge]\n%s\n[outro]\n%s",
                requestDto.getIntro(), requestDto.getVerse1(), requestDto.getVerse2(), requestDto.getChorus(),
                requestDto.getBridge(), requestDto.getOutro());
    }

}