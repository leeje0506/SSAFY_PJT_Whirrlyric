package com.example.member.dto.res;

import lombok.Builder;

@Builder
public record TokenReissueResponse(
    String accessToken
) {

}
