package com.example.song.service;

import com.example.member.service.MemberService;
import com.example.song.config.Genre;
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
    private final MemberService memberService;

    @Override
    public SongResultDto createSong(SongRequestDto requestDto) {
        // 사용자 입력을 포맷에 맞게 조합
        String formattedLyrics = formatLyrics(requestDto);

        //        Genre genre = Genre.fromCode(requestDto.getGenre());

        // 장르 코드가 유효한지 확인
        Genre genre;
        try {
            genre = Genre.fromName(requestDto.getGenre());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown genre name: " + requestDto.getGenre(), e);
        }


        // 현재 로그인한 사용자의 ID 가져오기
//        Integer memberId = getCurrentUserId();
//        Integer memberId = 1;

        // API 요청을 위한 JSON 객체 생성
        Map<String, Object> apiRequest = new HashMap<>();
        apiRequest.put("title", requestDto.getTitle());
        apiRequest.put("tags", genre.getCode());
        apiRequest.put("prompt", formattedLyrics);
        apiRequest.put("mv", "chirp-v3-0");
        apiRequest.put("continue_at", 120);
        apiRequest.put("continue_clip_id", "");
        Map<String, Object> response = webClientService.post("https://api.sunoaiapi.com/api/v1/",
            "gateway/generate/music", apiRequest);

        JSONObject resultJson = new JSONObject(response);
        JSONArray data = (JSONArray) resultJson.get("data");
        //첫번째 노래만 저장
        JSONObject songData = data.getJSONObject(0);

//        Member member = memberService.getMemberById(requestDto.getMemberId());


        Song song = Song.builder()
            .genre(genre)
            .title(songData.getString("title"))
            .lyrics(formattedLyrics)
            .songUrl("cdn1.suno.ai/" + songData.getString("song_id") + ".mp3")
//            .member(member)
            .build();
        songRepository.save(song);

        return new SongResultDto(song.getTitle(), song.getSongUrl(), genre.getCode(),
            formattedLyrics);
//            song.getMember().getMemberId());
    }

    // 사용자 입력 가사를 포맷에 맞게 조합
    private String formatLyrics(SongRequestDto requestDto) {
        return String.format(
            "[intro]\n%s\n[verse1]\n%s\n[verse2]\n%s\n[chorus]\n%s\n[bridge]\n%s\n[outro]\n%s",
            requestDto.getIntro(), requestDto.getVerse1(), requestDto.getVerse2(),
            requestDto.getChorus(),
            requestDto.getBridge(), requestDto.getOutro());
    }

//    private Integer getCurrentUserId() {
//    //member_id 찾는 로직
      //시큐리티 땜에 추후에..
//        return 1; // 예시로 1을 반환
//    }
}