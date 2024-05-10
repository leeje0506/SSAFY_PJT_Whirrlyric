//package com.example.common.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestControllerAdvice
//public class ExceptionManager {
//
//    @ExceptionHandler(BaseException.class)
//    public ResponseEntity<?> myExceptionHandler(BaseException e) {
//        e.printStackTrace();
//        return ResponseEntity.status(e.getErrorCode().getErrorCode())
//            .body(BaseExceptionResponse.builder()
//                .errorCode(e.getErrorCode())
//                .errorMessage(e.getErrorCode().getMessage()).build());
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public String runtimeExceptionHandler(RuntimeException e) {
//        e.printStackTrace();
//        return "Runtime Exception 발생!";
//    }
//
//}
