package com.example.song.repository;

import static com.example.song.domain.QMainsong.mainsong;
import com.example.song.domain.Mainsong;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MainsongCustomRepository {
    private final JPAQueryFactory queryFactory;

    public Mainsong findMainsongByMemberId(int memberId){
        return queryFactory.selectFrom(mainsong)
            .where(mainsong.member.memberId.eq(memberId))
            .fetchOne();
    }
}
