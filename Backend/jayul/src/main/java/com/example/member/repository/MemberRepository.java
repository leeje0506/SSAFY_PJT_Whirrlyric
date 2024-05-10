package com.example.member.repository;

import com.example.member.domain.Member;
import com.example.member.domain.OauthId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByOauthId(OauthId oauthId);

    Optional<Member> findByNickname(String nickname);
}
