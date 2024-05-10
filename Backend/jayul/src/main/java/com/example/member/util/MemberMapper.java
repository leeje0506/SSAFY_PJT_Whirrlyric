package com.example.member.util;

import com.example.member.domain.Member;
import com.example.member.domain.OauthId;
import com.example.member.domain.type.OauthServerType;
import com.example.member.dto.res.KakaoMemberResponse;

public class MemberMapper {

    public static Member toMember(KakaoMemberResponse kakaoMemberResponse) {
        return Member.builder()
            .oauthId(new OauthId(String.valueOf(kakaoMemberResponse.id()), OauthServerType.KAKAO))
            .build();
    }
}
