package com.example.member.service;

import com.example.member.domain.Member;
import com.example.member.repository.MemberRepository;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;


    @Override
    public Member getMemberById(Integer memberId) {
        // 주어진 ID가 null인 경우 IllegalArgumentException을 던집니다.
        Objects.requireNonNull(memberId, "Member ID는 null일 수 없습니다.");

        // 회원 ID를 이용하여 회원 정보를 조회하고 반환합니다.
        return memberRepository.findById(memberId)
            .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없습니다."));
    }

}
