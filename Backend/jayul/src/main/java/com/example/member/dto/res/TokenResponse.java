package com.example.member.dto.res;

import lombok.Builder;

@Builder
public record TokenResponse(
    String accessToken,
    String refreshToken
) {

}
