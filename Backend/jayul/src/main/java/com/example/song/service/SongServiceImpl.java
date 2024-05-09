package com.example.song.service;

import com.example.member.service.MemberService;
import com.example.song.config.Genre;
import com.example.song.config.WebClientService;
import com.example.song.domain.Song;
import com.example.song.dto.req.SongRequestDto;
import com.example.song.dto.res.SongResultDto;
import com.example.song.repository.SongRepository;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final WebClientService webClientService;
    private final SongRepository songRepository;
    private final MemberService memberService;

    @Override
    public SongResultDto createSong(SongRequestDto requestDto) {
        // 사용자 입력을 포맷에 맞게 조합
        String formattedLyrics = formatLyrics(requestDto);

        // 장르 코드가 유효한지 확인
        Genre genre;
        try {
            genre = Genre.fromName(requestDto.getGenre());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown genre name: " + requestDto.getGenre(), e);
        }

        // API 요청을 위한 JSON 객체 생성
        Map<String, Object> apiRequest = prepareApiRequest(requestDto, genre, formattedLyrics);
        JSONObject songData = postCreateSong(apiRequest);

        // 노래 객체 생성 및 저장
        Song song = buildSong(genre, songData, requestDto);
        songRepository.save(song);

        return new SongResultDto(song.getTitle(), song.getSongUrl(), genre.getCode(), formattedLyrics);
    }

    @Override
    public List<Map<String, String>> getAllSongLyrics() {
        List<Song> songs = songRepository.findAll();
        return songs.stream().map(this::parseLyrics).collect(Collectors.toList());
    }

    @Override
    public List<Genre> getAllGenres() {
        return Arrays.asList(Genre.values());
    }

    private Map<String, Object> prepareApiRequest(SongRequestDto requestDto, Genre genre, String formattedLyrics) {
        Map<String, Object> apiRequest = new HashMap<>();
        apiRequest.put("title", requestDto.getTitle());
        apiRequest.put("tags", genre.getCode());
        apiRequest.put("prompt", formattedLyrics);
        apiRequest.put("mv", "chirp-v3-0");
        apiRequest.put("continue_at", 120);
        apiRequest.put("continue_clip_id", "");
        return apiRequest;
    }

    private JSONObject postCreateSong(Map<String, Object> apiRequest) {
        Map<String, Object> response = webClientService.post("https://api.sunoaiapi.com/api/v1/", "gateway/generate/music", apiRequest);
        return new JSONObject(response).getJSONArray("data").getJSONObject(0);
    }

    private Song buildSong(Genre genre, JSONObject songData, SongRequestDto requestDto) {
        return Song.builder()
            .genre(genre)
            .title(songData.getString("title"))
            .lyrics(requestDto.toString()) // 모든 가사를 포맷된 문자열로도 저장
            .intro(requestDto.getIntro())
            .verse1(requestDto.getVerse1())
            .verse2(requestDto.getVerse2())
            .chorus(requestDto.getChorus())
            .bridge(requestDto.getBridge())
            .outro(requestDto.getOutro())
            .songUrl("cdn1.suno.ai/" + songData.getString("song_id") + ".mp3") // API에서 반환된 song_id 사용
            .build();
    }

    private Map<String, String> parseLyrics(Song song) {
        Map<String, String> lyricsParts = new HashMap<>();
        String[] parts = song.getLyrics().split("\n");
        for (String part : parts) {
            if (part.startsWith("[intro]")) {
                lyricsParts.put("intro", part.substring(7));
            } else if (part.startsWith("[verse1]")) {
                lyricsParts.put("verse1", part.substring(8));
            } else if (part.startsWith("[verse2]")) {
                lyricsParts.put("verse2", part.substring(8));
            } else if (part.startsWith("[chorus]")) {
                lyricsParts.put("chorus", part.substring(8));
            } else if (part.startsWith("[bridge]")) {
                lyricsParts.put("bridge", part.substring(8));
            } else if (part.startsWith("[outro]")) {
                lyricsParts.put("outro", part.substring(7));
            }
        }
        return lyricsParts;
    }

    private String formatLyrics(SongRequestDto requestDto) {
        return String.format(
            "[intro]\n%s\n[verse1]\n%s\n[verse2]\n%s\n[chorus]\n%s\n[bridge]\n%s\n[outro]\n%s",
            requestDto.getIntro(), requestDto.getVerse1(), requestDto.getVerse2(),
            requestDto.getChorus(), requestDto.getBridge(), requestDto.getOutro());
    }

    @Override
    public List<Map<String, Object>> getLyricsParts() {
        List<Map<String, Object>> parts = new ArrayList<>();
        parts.add(new HashMap<>() {{ put("id", 1); put("lyricsname", "title"); }});
        parts.add(new HashMap<>() {{ put("id", 2); put("lyricsname", "intro"); }});
        parts.add(new HashMap<>() {{ put("id", 3); put("lyricsname", "verse1"); }});
        parts.add(new HashMap<>() {{ put("id", 4); put("lyricsname", "verse2"); }});
        parts.add(new HashMap<>() {{ put("id", 5); put("lyricsname", "chorus"); }});
        parts.add(new HashMap<>() {{ put("id", 6); put("lyricsname", "bridge"); }});
        parts.add(new HashMap<>() {{ put("id", 7); put("lyricsname", "outro"); }});
        return parts;
    }

    @Override
    public List<Map<String, Object>> getGenresData() {
        List<Map<String, Object>> genres = new ArrayList<>();
        int id = 1;
        for (Genre genre : Genre.values()) {
            Map<String, Object> genreMap = new HashMap<>();
            genreMap.put("id", id++);
            genreMap.put("genrename", genre.name());
            genres.add(genreMap);
        }
        return genres;
    }
}
