package com.example.song.dto.res;

/*
 *   작성일시 : 2024.05.02
 *   작성 내용 : Suno Ai API에서 반환받은 값을 DB에 저장할 때 쓰는 DTO
 *   추가할 내용 : 현재 title과 Song_id만 있으니 나머지는 추가할 것!
 *   수정할 내용 : Setter 나중에 build 패턴으로 바꾸기. Setter로 쓰면 아무곳에서나 Setter로 특정 값 변경이 가능해서 좋지 얺음
 *
 * */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

@ToString
@Getter
@Setter // Setter도 좋은 구현은 아니니까 나중에 build 패턴으로 변경하고 setter 지울 것
public class SongResultDto {
    private String title;
    private String song_id;

}
