package com.example.song.repository;

import static com.example.song.domain.QSong.song;

import com.example.song.domain.Song;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SongCustomRepository {
    private final JPAQueryFactory queryFactory;

    public List<Song> getSongList(int memberId){
        return queryFactory.select(song)
            .from(song)
            .where(song.member.memberId.eq(memberId))
            //.orderBy(song.createdAt.desc())
            .fetch();
    }
}
