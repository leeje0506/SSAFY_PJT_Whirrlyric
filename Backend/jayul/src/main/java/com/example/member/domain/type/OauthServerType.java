package com.example.member.domain.type;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Locale;

@Tag(
    name = "서버 종류를 명시할 enum",
    description = "kakao를 통해 OauthServerType.KAKAO를 찾아오기 위해 fromName() 메서드 구현"
)
public enum OauthServerType {
    KAKAO;

    public static OauthServerType fromName(String type) {
        return OauthServerType.valueOf(type.toUpperCase(Locale.ENGLISH));
    }
}
