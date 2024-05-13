package com.example.song.service;

import com.example.common.enums.Genre;
import com.example.member.domain.Member;
import com.example.member.service.MemberService;
import com.example.song.config.WebClientService;
import com.example.song.domain.Song;
import com.example.song.dto.req.SongRequestDto;
import com.example.song.dto.res.*;
import com.example.song.exception.SongNotFoundException;
import com.example.song.repository.SongCustomRepository;
import com.example.song.repository.SongRepository;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final SongCustomRepository songCustomRepository;
    private final WebClientService webClientService;
    private final MemberService memberService;

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

    @Override
    public SongResultDto createSong(SongRequestDto requestDto, Member member) {

        // Member 객체를 현재 영속성 컨텍스트에 다시 연결
        Member managedMember = memberService.merge(member);

        // 사용자 입력을 포맷에 맞게 조합
        String formattedLyrics = formatLyrics(requestDto);
        Genre genre = Genre.valueOf(requestDto.getGenre());

        // API 요청을 위한 JSON 객체 생성
        Map<String, Object> apiRequest = prepareApiRequest(requestDto, genre, formattedLyrics);
        JSONObject songData = postCreateSong(apiRequest);

        // 노래 객체 생성 및 저장
        Song song = buildSong(genre, songData, requestDto, managedMember);
        songRepository.save(song);

        return new SongResultDto(song.getSongId(), song.getTitle(), song.getSongUrl(),
            song.getImageUrl(), genre.getLabel(), formattedLyrics, managedMember);
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

    @Override
    public List<LyricsPartDto> getLyricsParts() {
        return Arrays.asList(
            new LyricsPartDto(1, "Title"),
            new LyricsPartDto(2, "Intro"),
            new LyricsPartDto(3, "Verse1"),
            new LyricsPartDto(4, "Verse2"),
            new LyricsPartDto(5, "Chorus"),
            new LyricsPartDto(6, "Bridge"),
            new LyricsPartDto(7, "Outro")
        );
    }

    @Override
    public LyricsGuideDto getLyricsGuide() {
        return new LyricsGuideDto("이것은 사용법에 대한 가이드라인입니다.");
    }


    private Map<String, Object> prepareApiRequest(SongRequestDto requestDto, Genre genre,
        String formattedLyrics) {
        Map<String, Object> apiRequest = new HashMap<>();
        apiRequest.put("title", requestDto.getTitle());
        apiRequest.put("tags", genre.getLabel());
        apiRequest.put("prompt", formattedLyrics);
        apiRequest.put("mv", "chirp-v3-0");
        apiRequest.put("continue_at", 120);
        apiRequest.put("continue_clip_id", "");
        return apiRequest;
    }

    private JSONObject postCreateSong(Map<String, Object> apiRequest) {
        Map<String, Object> response = webClientService.post("https://api.sunoaiapi.com/api/v1/",
            "gateway/generate/music", apiRequest);
        return new JSONObject(response).getJSONArray("data").getJSONObject(0);
    }

    private Song buildSong(Genre genre, JSONObject songData, SongRequestDto requestDto,
        Member member) {
        return Song.builder()
            .songId(requestDto.getSongId())
            .genre(genre)
            .title(songData.getString("title"))
            .lyrics(requestDto.toString())
            .songUrl("cdn1.suno.ai/" + songData.getString("song_id") + ".mp3")
            .imageUrl("cdn1.suno.ai/image_" + songData.getString("song_id") + ".png")
            .member(member)
            .build();
    }

    private Map<String, String> parseLyrics(Song song) {
        Map<String, String> lyricsParts = new HashMap<>();
        String[] parts = song.getLyrics().split("\n");
        for (String part : parts) {
            if (part.startsWith("[Intro]")) {
                lyricsParts.put("intro", part.substring(7));
            } else if (part.startsWith("[Verse1]")) {
                lyricsParts.put("verse1", part.substring(8));
            } else if (part.startsWith("[Verse2]")) {
                lyricsParts.put("verse2", part.substring(8));
            } else if (part.startsWith("[Chorus]")) {
                lyricsParts.put("chorus", part.substring(8));
            } else if (part.startsWith("[Bridge]")) {
                lyricsParts.put("bridge", part.substring(8));
            } else if (part.startsWith("[Outro]")) {
                lyricsParts.put("outro", part.substring(7));
            }
        }
        return lyricsParts;
    }

    private String formatLyrics(SongRequestDto requestDto) {
        return String.format(
            "[INTRO]\n%s\n[VERSE1]\n%s\n[VERSE2]\n%s\n[CHORUS]\n%s\n[BRIDGE]\n%s\n[OUTRO]\n%s",
            requestDto.getIntro(), requestDto.getVerse1(), requestDto.getVerse2(),
            requestDto.getChorus(), requestDto.getBridge(), requestDto.getOutro());
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
