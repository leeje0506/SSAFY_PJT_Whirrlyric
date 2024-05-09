package com.example.song.domain;

import com.example.member.domain.Member;
import com.example.song.config.Genre;
import com.example.song.config.GenreConverter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer songId;

    private String title;

    @Convert(converter = GenreConverter.class)
    @Column(columnDefinition = "char(2)")
    private Genre genre;// KPOP 01, HIPHOP 02, BALLAD 03, RAP 04, MUSICAL 05

    // 가사를 문자열로 저장
    @Column(columnDefinition = "text")
    private String lyrics;

    @Column(columnDefinition = "text")
    private String intro;

    @Column(columnDefinition = "text")
    private String verse1;

    @Column(columnDefinition = "text")
    private String verse2;

    @Column(columnDefinition = "text")
    private String chorus;

    @Column(columnDefinition = "text")
    private String bridge;

    @Column(columnDefinition = "text")
    private String outro;

    @Setter
    private String imageUrl;

    private String songUrl;

    @Column
    private Integer playCount = 0;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private Member member;

    // 재생 횟수 + 1
    public void updatePlayCount(){
        this.playCount += 1;
    }

    @Builder
    public Song(String title, String lyrics, Genre genre, String intro, String verse1, String verse2, String chorus, String bridge, String outro, String songUrl, Member member){
        this.title = title;
        this.lyrics = lyrics;
        this.genre = genre;
        this.intro = intro;
        this.verse1 = verse1;
        this.verse2 = verse2;
        this.chorus = chorus;
        this.bridge = bridge;
        this.outro = outro;
        this.songUrl = songUrl;
        this.member = member;
    }

}
