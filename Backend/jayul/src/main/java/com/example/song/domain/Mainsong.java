package com.example.song.domain;

import com.example.common.domain.BaseEntity;
import com.example.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class Mainsong extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "song_id")
    @Setter
    private Song song;

    @Builder
    public Mainsong(Member member, Song song){
        this.member = member;
        this.song = song;
    }
}
