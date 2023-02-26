package com.pilathy.api.center.config.advice;

import com.pilathy.common.exception.model.InvalidException;
import com.pilathy.common.exception.model.NotFoundException;
import com.pilathy.common.exception.model.PilathyBaseException;
import com.pilathy.common.model.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static com.pilathy.common.exception.ErrorCode.*;
import static java.util.stream.Collectors.joining;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ApiResponse<Object> handleBadRequest(BindException exception) {
        String errorMessage = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(joining("\n"));
        log.error("BindException: {}", errorMessage);
        return ApiResponse.error(E400_INVALID, errorMessage);
    }

    /**
     * Pilathy Custom Exception
     */
    @ExceptionHandler(PilathyBaseException.class)
    private ResponseEntity<ApiResponse<Object>> handleBaseException(PilathyBaseException exception, HttpServletRequest request) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.status(exception.getStatus())
                .body(ApiResponse.error(exception.getErrorCode()));
    }

}
