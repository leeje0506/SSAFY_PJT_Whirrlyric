package com.example.song.dto.req;

import com.example.song.domain.Lyrics;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongRequestDto {
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


//public class SongRequestDto {
//    private String title;
//    private String genre;
//    private String intro;
//    private String outro;
//
//    // Getters and setters
//    public String getTitle() { return title; }
//    public void setTitle(String title) { this.title = title; }
//
//    public String getGenre() { return genre; }
//    public void setGenre(String genre) { this.genre = genre; }
//
//    public String getIntro() { return intro; }
//    public void setIntro(String intro) { this.intro = intro; }
//
//    public String getOutro() { return outro; }
//    public void setOutro(String outro) { this.outro = outro; }
//}
