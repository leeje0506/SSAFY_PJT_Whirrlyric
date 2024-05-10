package com.example.member.service;

import com.example.common.exception.BaseException;
import com.example.common.exception.ErrorCode;
import com.example.member.domain.Member;
import com.example.member.domain.OauthId;
import com.example.member.domain.authCode.AuthCodeRequestUrlProviderComposite;
import com.example.member.domain.client.OauthMemberClientComposite;
import com.example.member.domain.type.OauthServerType;
import com.example.member.dto.res.*;
import com.example.member.repository.MemberRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.stereotype.Service;

@Tag(
    name = "OauthServerType을 받아서 해당 인증 서버에서 Auth Code를 받아오기 위한 URL 주소를 생성")
@Service
@RequiredArgsConstructor
public class OauthService extends DefaultOAuth2UserService {

    private final TokenService tokenService;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final MemberRepository memberRepository;
    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final RedisTemplate<String, Object> tokensRedisTemplate;

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

    public LoginDto login(OauthServerType oauthServerType, String authCode) {
        OauthDto dto = oauthMemberClientComposite.fetch(oauthServerType, authCode);

        Member member = memberRepository.findByOauthId(dto.member().getOauthId())
            .orElseGet(() -> memberRepository.save(Member.builder()
                .oauthId(new OauthId(dto.member().getOauthId().getOauthServerId(),
                    OauthServerType.KAKAO))
                .nickname(createNickname())
                .image(createImageUrl())
                .role("user")
                .build()));

        //redis oauthAcessToken 저장
        HashOperations<String, Object, Object> hashOperations = tokensRedisTemplate.opsForHash();
        hashOperations.put(dto.member().getOauthId().getOauthServerId(), "oauthAccessToken",
            dto.accessToken());

        if (member.getIsDeleted()) {    //탈퇴한 회원일 경우
            throw new BaseException(ErrorCode.INVALID_MEMBER_WITHDRAWN);
        }

        return LoginDto.builder()
            .oauthId(member.getOauthId().getOauthServerId())
            .nickname(member.getNickname())
            .accessToken(tokenService.createToken(member))
            .refreshToken(tokenService.createRefreshToken(member))
            .build();
    }

    public KaKaoLogoutResponse logout(HttpServletResponse response, OauthServerType oauthServerType,
        String oauthId) {
        tokenService.deleteHeader(response);
        return oauthMemberClientComposite.logout(oauthServerType, oauthId);
    }

    private Integer createImageUrl() {
        return ThreadLocalRandom.current().nextInt(9) + 1;
    }

    private String createNickname() {
        Random random = new Random();
        StringBuilder sb;

        //중복된 닉네임이 없을 때까지 반복
        do {
            sb = new StringBuilder();

            for (int i = 0; i < 4; i++) {
                sb.append((char) ('a' + random.nextInt(26)));
            }

            for (int i = 0; i < 4; i++) {
                sb.append(random.nextInt(10));
            }
        } while (memberRepository.findByNickname(sb.toString()).isPresent());

        return sb.toString();
    }
}
