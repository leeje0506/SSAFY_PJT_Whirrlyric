package com.example.member.util;

import com.example.member.domain.Member;
import com.example.member.dto.res.OauthDto;

public class OauthIdMapper {

    public static OauthDto fromMember(Member member, String at) {
        return OauthDto.builder()
            .member(member)
            .accessToken(at)
            .build();
    }
}
