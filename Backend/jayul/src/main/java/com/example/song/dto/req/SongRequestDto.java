package com.example.song.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongRequestDto {
    private String title;
    private String tags; // 장르 대신 태그 사용
    private String prompt; // 가사 대신 프롬프트 사용
    private String mv;
    private Integer continueAt;
    private String continueClipId;
}
