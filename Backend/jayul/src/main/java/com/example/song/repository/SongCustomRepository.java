package com.example.song.repository;

import static com.example.member.domain.QMember.member;
import static com.example.song.domain.QSong.song;

import com.example.song.domain.Song;
import com.example.song.dto.res.SongResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SongCustomRepository {
    private final JPAQueryFactory queryFactory;

    public List<Song> getMySongList(int memberId){
        return queryFactory.select(song)
            .from(song)
            .where(song.member.memberId.eq(memberId))
            //.orderBy(song.createdAt.desc())
            .fetch();
    }

    public Song findSongBySongId(int songId){
        return queryFactory.selectFrom(song)
            .where(song.songId.eq(songId))
            .fetchOne();
    }

    public List<SongResponse> findSongsSortedByPopularity() {
        List<Song> songs = queryFactory
            .selectFrom(song)
            .leftJoin(song.member, member)
            .orderBy(song.playCount.desc())
            .limit(25)
            .fetch();

        return songs.stream()
            .map(s -> new SongResponse(s, s.getMember() != null ? s.getMember().getNickname() : null, s.getMember() != null ? s.getMember().getMemberId() : null))
            .collect(Collectors.toList());
    }

    public List<SongResponse> findSongsOrderByDate() {
        List<Song> songs = queryFactory
            .selectFrom(song)
            .leftJoin(song.member, member)
            .orderBy(song.createdAt.desc())
            .fetch();

        return songs.stream()
            .map(s -> new SongResponse(s, s.getMember() != null ? s.getMember().getNickname() : null, s.getMember() != null ? s.getMember().getMemberId() : null))
            .collect(Collectors.toList());
    }

    public List<SongResponse> findAllSongs(){
        List<Song> songs = queryFactory
            .selectFrom(song)
            .leftJoin(song.member, member)
            .orderBy(song.playCount.desc())
            .fetch();

        return songs.stream()
            .map(s -> new SongResponse(s, s.getMember() != null ? s.getMember().getNickname() : null, s.getMember() != null ? s.getMember().getMemberId() : null))
            .collect(Collectors.toList());
    }
}
