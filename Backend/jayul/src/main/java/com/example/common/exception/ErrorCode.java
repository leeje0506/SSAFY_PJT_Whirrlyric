package com.example.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    MAINSONG_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "자신의 대표곡만 수정할 수 있습니다."),
    SONG_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 곡을 찾을 수 없습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 멤버를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;

}
