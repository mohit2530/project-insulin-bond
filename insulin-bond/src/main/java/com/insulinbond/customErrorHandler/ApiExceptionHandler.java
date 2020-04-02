package com.insulinbond.customErrorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Api Exception handler which help make custom exception
 */
@ControllerAdvice
public class ApiExceptionHandler {

    /**
     * Handle Api request Exception
     * @param e
     * @return
     */
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<ApiException> handleApiRequestException(ApiRequestException e) {
        HttpStatus httpStatus = null;
        if (e.getHttpStatus() == HttpStatus.FORBIDDEN) {
            httpStatus = HttpStatus.FORBIDDEN;
        }
        if (e.getHttpStatus() == HttpStatus.BAD_REQUEST) {
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        // payload containing exception Details
        ApiException apiException = new ApiException(
                e.getMessage(),
                e,
                httpStatus,
                e.getHttpStatus().value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiException, httpStatus);
    }
}
