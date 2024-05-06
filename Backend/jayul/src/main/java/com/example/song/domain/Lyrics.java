package com.example.song.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "가사")
@Getter
@Setter
@NoArgsConstructor
public class Lyrics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lyricsId;
    private String intro;
    private String verse1;
    private String verse2;
    private String chorus;
    private String bridge;
    private String outro;


    @Builder
    public  Lyrics (Integer lyricsId, String intro, String verse1, String verse2, String chorus, String bridge, String outro){
        this.lyricsId = lyricsId;
        this.intro = intro;
        this.verse1 = verse1;
        this.verse2 = verse2;
        this.chorus = chorus;
        this.bridge = bridge;
        this.outro = outro;
    }

}
