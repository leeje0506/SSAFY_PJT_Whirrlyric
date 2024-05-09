package com.example.song.dto.req;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongRequestDto {
    private Integer memberId;
    private String title;
    private String genre; //  요청할 때 tags로
    private String mv;
    private Integer continueAt;
    private String continueClipId;

//    private Lyrics lyrics; //  요청할 때 prompt로
    private String intro;
    private String verse1;
    private String verse2;
    private String chorus;
    private String bridge;
    private String outro;

}

