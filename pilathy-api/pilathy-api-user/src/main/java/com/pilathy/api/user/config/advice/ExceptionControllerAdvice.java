package com.pilathy.api.user.config.advice;

import com.pilathy.common.exception.CustomException;
import com.pilathy.common.model.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Object>> customExceptionHandle(CustomException e) {
        log.error(e.getErrorCode().getMessage(), e);
        return ResponseEntity.status(e.getStatus())
                .body(ApiResponse.error(e.getErrorCode()));
    }
}
