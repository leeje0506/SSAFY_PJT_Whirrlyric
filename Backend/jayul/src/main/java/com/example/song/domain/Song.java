package com.example.song.domain;

import com.example.song.config.Genre;
import com.example.member.domain.Member;
import com.example.song.config.GenreConverter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "노래")
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

    @ManyToOne
    @JoinColumn(name = "lyrics_id")
    private Lyrics lyrics;

    @Setter
    private String imageUrl;

    @Column(name = "song_url")
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
    public Song(String title, Lyrics lyrics, String imageUrl, String songUrl, Member member){
        this.title = title;
        this.lyrics = lyrics;
        this.imageUrl = imageUrl;
        this.songUrl = songUrl;
        this.member = member;
    }

}
