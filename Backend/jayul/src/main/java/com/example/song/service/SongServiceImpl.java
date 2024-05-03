package com.example.song.service;


import com.example.song.config.WebClientService;
import com.example.song.dto.req.SongRequestDto;
import com.example.song.dto.res.SongResultDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.Map;

@Service
public class SongServiceImpl implements SongService {

    // WebClient를 가지고 baseUrl, path, requestDto를 넣어서 리턴값 Map<String, Object> 구조로 변환
    private final WebClientService webClientService;

    @Autowired
    public SongServiceImpl(WebClientService webClientService) {
        this.webClientService = webClientService;
    }

    // SUNO AI API에 노래 생성 요청을 보내고 결과를 Mono로 반환 -> 결과를
    @Override
    public SongResultDto createSong(SongRequestDto requestDto) {


        // 2. suno ai api 결과를 Map으로 받아옴
        Map<String, Object> results = webClientService.post("https://api.sunoaiapi.com/api/v1/","gateway/generate/music", requestDto );

        System.out.println("******1212");

        System.out.println(results.toString());
        System.out.println("******1212");

        // 3. results를 JSONObject 형태로 변환
        JSONObject resultJson = new JSONObject(results);

        // 3. 그 중에서 data 항목만 JSON Array로 가져옴
        JSONArray data = (JSONArray) resultJson.get("data");

        // 로그 찍으려고 둔 것 (나중에 주석처리 해주라)
        System.out.println(data.get(0));



        // 4. data는 기본적으로 2개 element가 있음 (Song1, Song2) 이 중 Song1만 쓸 것
        // 결과 반환할 Dto 하나 생성 (현재 Title, Song_id 만 갖고 있음)
        SongResultDto songResultDto = new SongResultDto();

        // data 중 첫번째 노래를 가져옴
        JSONObject song1 = (JSONObject) data.get(0);

        // title, song_id 저장
        songResultDto.setTitle(song1.getString("title"));
        songResultDto.setSong_id(song1.getString("song_id"));

        // 여태까지 저장한 값 확인용 (ToString Method가 있음)
        System.out.println(songResultDto);



        // 이제 이걸 DB에 save 하는 건 하실 수 있죠? JPA Repository에서 Save 메서드 만들어서 하는 거!

        // 기존 코드
//        Mono<SongResponseDto> result = webClient.post()
//                .uri("gateway/generate/music")
//                .bodyValue(requestDto)
//                .retrieve()
//                .bodyToMono(SongResponseDto.class);


        return songResultDto;
    }
}
