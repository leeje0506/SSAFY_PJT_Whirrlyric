package com.example.song.domain;

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

    @Column(columnDefinition = "char(1)", length = 1)
    private String hasLyrics;       // Y or N

    private String lyrics;

    private String mood;

    private String imageUrl;

    private String voiceUrl;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Song(String title, String hasLyrics, String lyrics, String mood, String imageUrl, String voiceUrl, Member member){
        this.title = title;
        this.hasLyrics = hasLyrics;
        this.lyrics = lyrics;
        this.mood = mood;
        this.imageUrl = imageUrl;
        this.voiceUrl = voiceUrl;
        this.member = member;
    }

}
