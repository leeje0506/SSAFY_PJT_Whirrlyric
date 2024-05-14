package com.example.common.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BaseExceptionResponse {
    private final int errorCode;
    private final String errorMessage;
}
