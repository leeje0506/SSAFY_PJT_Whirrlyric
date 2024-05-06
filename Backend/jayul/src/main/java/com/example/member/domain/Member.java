package com.example.member.domain;

import com.example.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;

    @Setter
    @Column(unique = true)
    private String nickname;

    private String provider;

    @Setter
    private String role;

    @Setter
    private String profileImage;

    @Column
    private Boolean isDeleted = false;

    @Builder
    public Member(String nickname, String provider, String role, String image){
        this.nickname = nickname;
        this.provider = provider;
        this.role = role;
        this.profileImage = image;
    }
}
