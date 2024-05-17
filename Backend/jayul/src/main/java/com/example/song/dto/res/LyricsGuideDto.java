package com.example.song.dto.res;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class LyricsGuideDto {
//    private String description;
    private String intro;
    private String verse;
    private String chorus;
    private String bridge;
    private String outro;
    private String closingRemark;
}

