package com.example.song.exception;

import com.example.common.exception.BaseException;
import com.example.common.exception.ErrorCode;

public class SongNotFoundException extends BaseException {
    public SongNotFoundException(){super(ErrorCode.SONG_NOT_FOUND);}
}
