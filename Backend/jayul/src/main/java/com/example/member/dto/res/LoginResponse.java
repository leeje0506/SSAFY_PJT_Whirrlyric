package com.example.member.dto.res;

import lombok.Builder;

@Builder
public record LoginResponse(
    String oauthId,
    String nickname,
    String accessToken
) {

}
