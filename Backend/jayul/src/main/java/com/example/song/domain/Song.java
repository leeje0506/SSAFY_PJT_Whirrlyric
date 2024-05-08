package com.example.song.domain;

import com.example.song.config.Genre;
import com.example.member.domain.Member;
import com.example.song.config.GenreConverter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
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
    public Song(String title, String lyrics, String imageUrl, String songUrl, Member member){
        this.title = title;
        this.lyrics = lyrics;
        this.imageUrl = imageUrl;
        this.songUrl = songUrl;
        this.member = member;
    }

}
