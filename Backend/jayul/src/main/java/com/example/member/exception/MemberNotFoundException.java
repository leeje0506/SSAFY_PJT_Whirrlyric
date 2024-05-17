package com.example.member.exception;

import com.example.common.exception.BaseException;
import com.example.common.exception.ErrorCode;

public class MemberNotFoundException extends BaseException {

    public MemberNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND);
    }
}
