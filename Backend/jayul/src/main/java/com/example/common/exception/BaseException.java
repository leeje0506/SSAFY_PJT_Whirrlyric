package com.example.common.exception;
public class BaseException extends RuntimeException{

    ErrorCode errorCode;

    public BaseException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }

}
