package com.example.member.domain;

import com.example.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "member",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "oauth_id_unique",
            columnNames = {
                "oauth_server_id",
                "provider"
            }
        ),
    }
)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;

    @Setter
    @Column(unique = true)
    private String nickname;

    @Embedded
    private OauthId oauthId;

    @Setter
    private String role;

    @Setter
    private Integer profileImage;

    @Setter
    @NonNull
    private Boolean isDeleted = false;

    @Builder
    public Member(String nickname, OauthId oauthId, String role, Integer image) {
        this.nickname = nickname;
        this.oauthId = oauthId;
        this.role = role;
        this.profileImage = image;
    }
}
