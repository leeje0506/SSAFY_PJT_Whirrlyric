package com.example.member.domain.client;

import com.example.member.domain.type.OauthServerType;
import com.example.member.dto.res.KaKaoLogoutResponse;
import com.example.member.dto.res.OauthDto;

public interface OauthMemberClient {

    OauthServerType supportServer();

    KaKaoLogoutResponse logout(String oauthId);

    OauthDto fetch(String code);
}
