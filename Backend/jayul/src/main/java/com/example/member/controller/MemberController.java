package com.example.member.controller;

import com.example.member.dto.res.TokenReissueResponse;
import com.example.member.dto.res.TokenResponse;
import com.example.member.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final TokenService tokenService;

    @Operation(summary = "access 토큰 재발급", description = "만료된 access token을 재발급 한다.")
    @PostMapping("/reissue")
    public ResponseEntity<TokenReissueResponse> reissueAccessToken(HttpServletRequest request,
        HttpServletResponse response) {
        TokenResponse tokenResponse = tokenService.reissueAccessToken(request, response);

        return ResponseEntity.ok(
            TokenReissueResponse.builder().accessToken(tokenResponse.accessToken()).build());
    }
}
