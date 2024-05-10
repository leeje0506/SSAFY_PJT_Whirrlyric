package com.example.member.domain.client;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import com.example.member.domain.type.OauthServerType;
import com.example.member.dto.res.KaKaoLogoutResponse;
import com.example.member.dto.res.OauthDto;
import java.util.*;
import org.springframework.stereotype.Component;

@Component
public class OauthMemberClientComposite {

    private final Map<OauthServerType, OauthMemberClient> mapping;

    public OauthMemberClientComposite(Set<OauthMemberClient> clients) {
        mapping = clients.stream()
            .collect(toMap(
                OauthMemberClient::supportServer,
                identity()
            ));
    }

    public OauthDto fetch(OauthServerType oauthServerType, String authCode) {
        return getClient(oauthServerType).fetch(authCode);
    }

    public KaKaoLogoutResponse logout(OauthServerType oauthServerType, String oauthId) {
        return getClient(oauthServerType).logout(oauthId);
    }

    private OauthMemberClient getClient(OauthServerType oauthServerType) {
        return Optional.ofNullable(mapping.get(oauthServerType))
            .orElseThrow(() -> new RuntimeException("지원하지 않는 소셜 로그인 타입입니다."));
    }
}
