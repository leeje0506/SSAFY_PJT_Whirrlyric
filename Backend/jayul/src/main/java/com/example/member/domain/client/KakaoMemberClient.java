package com.example.member.domain.client;

import com.example.common.config.KakaoOauthConfig;
import com.example.member.domain.Member;
import com.example.member.domain.type.OauthServerType;
import com.example.member.dto.req.KakaoToken;
import com.example.member.dto.res.*;
import com.example.member.util.MemberMapper;
import com.example.member.util.OauthIdMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class KakaoMemberClient implements OauthMemberClient {

    private final KakaoApiClient kakaoApiClient;
    private final KakaoOauthConfig kakaoOauthConfig;

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.KAKAO;
    }

    @Override
    public OauthDto fetch(String authCode) {
        KakaoToken tokenInfo = kakaoApiClient.fetchToken(tokenRequestParams(authCode));
        KakaoMemberResponse kakaoMemberResponse =
            kakaoApiClient.fetchMember("Bearer " + tokenInfo.accessToken());
        Member member = MemberMapper.toMember(kakaoMemberResponse);
        return OauthIdMapper.fromMember(member, tokenInfo.accessToken());
    }

    @Override
    public KaKaoLogoutResponse logout(String oauthId) {
        return null;
    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoOauthConfig.clientId());
        params.add("redirect_uri", kakaoOauthConfig.redirectUri());
        params.add("code", authCode);
        params.add("client_secret", kakaoOauthConfig.clientSecret());
        return params;
    }
}
