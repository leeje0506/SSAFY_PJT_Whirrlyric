package com.example.member.service;

import com.example.member.domain.Member;
import com.example.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    @PersistenceContext
    private EntityManager entityManager;

    private final MemberRepository memberRepository;


    @Override
    public Member getMemberById(Integer memberId) {
        // 주어진 ID가 null인 경우 IllegalArgumentException을 던집니다.
        Objects.requireNonNull(memberId, "Member ID는 null일 수 없습니다.");

        // 회원 ID를 이용하여 회원 정보를 조회하고 반환합니다.
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
    }

    @Transactional  // 이 메소드가 트랜잭션 컨텍스트 내에서 실행되도록 보장
    @Override
    public Member merge(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("Member는 null일 수 없습니다.");
        }
        return entityManager.merge(member);
    }

}
