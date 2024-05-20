package com.example.member.domain;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

import com.example.member.domain.type.OauthServerType;
import jakarta.persistence.*;
import lombok.*;

@Embeddable
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
public class OauthId {

    @Column(nullable = false, name = "oauth_server_id")
    private String oauthServerId;

    @Enumerated(STRING)
    @Column(nullable = false, name = "provider")
    private OauthServerType oauthServerType;
}
