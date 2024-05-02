package com.example.member.repository;

import static com.example.member.domain.QMember.member;

import com.example.member.domain.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberCustomRepository {
    private final JPAQueryFactory queryFactory;

    public Member findMemberById(int memberId){
        return queryFactory.selectFrom(member)
            .where(member.memberId.eq(memberId))
            .fetchOne();
    }
}
