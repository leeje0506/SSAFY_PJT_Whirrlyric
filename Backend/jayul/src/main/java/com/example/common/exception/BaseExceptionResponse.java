package com.example.common.exception;

import lombok.*;

@Getter
@Builder
public class BaseExceptionResponse {
    private final int errorCode;
    private final String errorMessage;
}
