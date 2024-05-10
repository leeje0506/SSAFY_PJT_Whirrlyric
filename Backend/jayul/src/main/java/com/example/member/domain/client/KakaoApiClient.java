package com.example.member.domain.client;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

import com.example.member.dto.req.KakaoToken;
import com.example.member.dto.res.KaKaoLogoutResponse;
import com.example.member.dto.res.KakaoMemberResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface KakaoApiClient {

    @Operation(
        summary = "토큰 받는 API"
    )
    @PostExchange(url = "https://kauth.kakao.com/oauth/token", contentType = APPLICATION_FORM_URLENCODED_VALUE)
    KakaoToken fetchToken(@RequestParam MultiValueMap<String, String> params);

    @Operation(
        summary = "사용자 정보 가져오기 API",
        description = "AccessToken을 통해 회원 정보를 조회"
    )
    @GetExchange("https://kapi.kakao.com/v2/user/me")
    KakaoMemberResponse fetchMember(@RequestHeader(name = AUTHORIZATION) String bearerToken);

    @Operation(
        summary = "로그아웃 API"
    )
    @PostExchange(url = "https://kapi.kakao.com/v1/user/logout")
    KaKaoLogoutResponse logout(@RequestHeader(name = AUTHORIZATION) String bearerToken);
}
