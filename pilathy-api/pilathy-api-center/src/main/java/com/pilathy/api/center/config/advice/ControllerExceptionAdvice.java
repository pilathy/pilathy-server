package com.pilathy.api.center.config.advice;

import com.pilathy.common.exception.model.InvalidException;
import com.pilathy.common.exception.model.NotFoundException;
import com.pilathy.common.model.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidException.class)
    public ApiResponse<Object> handleInvalidException(InvalidException exception) {
        log.error(exception.getMessage());
        return ApiResponse.error(exception.getErrorCode());
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ApiResponse<Object> handleNotFoundException(NotFoundException exception) {
        log.error(exception.getMessage());
        return ApiResponse.error(exception.getErrorCode());
    }

}
