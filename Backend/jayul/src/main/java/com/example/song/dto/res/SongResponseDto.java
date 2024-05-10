package com.example.song.dto.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongResponseDto {
    private String userId;
    private String songId; //저장할 때 songURL
    private String status;
    private String title;
    private String imageLargeUrl;
    private String imageUrl;
    private String modelName;
    private String videoUrl;
    private String audioUrl;
    private String metaTags; // 장르 정보
    private String metaPrompt; // 가사 정보
    private Integer metaDuration;
    private String metaErrorMsg;
    private String metaErrorType;
}
