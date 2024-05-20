package com.example.song.domain;

import com.example.common.domain.BaseEntity;
import com.example.common.enums.Genre;
import com.example.member.domain.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class Song extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer songId;

    private String title;

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
    @JsonIgnore
    private Member member;

    // 재생 횟수 + 1
    public void updatePlayCount() {
        this.playCount += 1;
    }

    @Builder
    public Song(Integer songId, String title, String lyrics, Genre genre, String songUrl,
        String imageUrl, Member member) {
        this.songId = songId;
        this.title = title;
        this.lyrics = lyrics;
        this.genre = genre;
        this.songUrl = songUrl;
        this.imageUrl = imageUrl;
        this.member = member;
    }

}
