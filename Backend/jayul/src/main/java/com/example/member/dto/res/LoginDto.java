package com.example.member.dto.res;

import lombok.Builder;

@Builder
public record LoginDto(
	String oauthId,
	String nickname,
	String accessToken,
	String refreshToken
) {
}
