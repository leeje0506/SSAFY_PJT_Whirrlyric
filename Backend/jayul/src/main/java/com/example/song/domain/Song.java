package com.example.song.domain;

import com.example.common.enums.Genre;
import com.example.member.domain.Member;
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

    @Column(columnDefinition = "char(2)")
    private Genre genre;        // 임시 KPOP 00, HIPHOP 01, ROCK 02, RAP 03

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
