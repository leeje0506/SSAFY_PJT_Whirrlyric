package com.example.member.dto.res;

import com.example.member.domain.Member;
import lombok.Builder;

@Builder
public record OauthDto(
	Member member,
	String accessToken
) {
}
