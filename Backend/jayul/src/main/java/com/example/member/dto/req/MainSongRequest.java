package com.example.member.dto.req;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MainSongRequest {
    private String nickname;
    private int songId;
}
