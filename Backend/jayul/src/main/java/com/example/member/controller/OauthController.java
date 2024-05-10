package com.example.member.controller;

import com.example.member.domain.Member;
import com.example.member.domain.type.OauthServerType;
import com.example.member.dto.res.*;
import com.example.member.service.OauthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(
    name = "인증 대행 컨트롤러",
    description = "Oauth 관련 로그인 및 로그아웃 기능 등이 포함되어 있음"
)
@RequiredArgsConstructor
@RequestMapping("/oauth")
@RestController
@Slf4j
public class OauthController {

    private final OauthService oauthService;

    @SneakyThrows
    @GetMapping("/{oauthServerType}")
    ResponseEntity<Void> redirectAuthCodeRequestUrl(
        @PathVariable OauthServerType oauthServerType,
        HttpServletResponse response
    ) {
        String redirectUrl = oauthService.getAuthCodeRequestUrl(oauthServerType);
        response.sendRedirect(redirectUrl);
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "카카오 로그인 및 회원가입",
        description = "카카오 계정을 통해 로그인을 진행한다."
    )
    @GetMapping("/login/{oauthServerType}")
    ResponseEntity<LoginResponse> login(
        HttpServletResponse response,
        @PathVariable OauthServerType oauthServerType,
        @RequestParam("code") String code
    ) {
        LoginDto login = oauthService.login(oauthServerType, code);

        Cookie cookie = new Cookie("refreshToken", login.refreshToken());
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        //기존의 쿠키 설정을 문자열로 변환
        String cookieValue = "refreshToken=" + login.refreshToken() +
            "; HttpOnly; Secure; Path=/; SameSite=None";

        //응답 헤더에 쿠키 추가
        response.addHeader("Set-Cookie", cookieValue);
        return ResponseEntity.ok(LoginResponse
            .builder()
            .oauthId(login.oauthId())
            .nickname(login.nickname())
            .accessToken(login.accessToken())
            .build());
    }

    @Operation(
        summary = "로그아웃",
        description = "로그아웃 이후 헤더와 쿠키를 초기화 시킨다."
    )
    @PostMapping("/logout/{oauthServerType}")
    public ResponseEntity<KaKaoLogoutResponse> logout(
        @AuthenticationPrincipal Member member,
        HttpServletResponse response,
        @PathVariable OauthServerType oauthServerType
    ) {
        return ResponseEntity.ok(
            oauthService.logout(response, oauthServerType, member.getOauthId().getOauthServerId()));
    }
}
