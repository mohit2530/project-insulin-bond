package com.insulinbond.customErrorHandler;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Api Exception class to show and save in database and this is also Mongodb Collection
 */
@Document
public class ApiException {

    // Error Message
    private final String message;

    // Reason of error
    private Throwable throwable;

    // http error message
    private HttpStatus httpStatus;

    // http error code
    private int statusCode;

    // date and time error recorded
    private final LocalDateTime timestamp;

    // constructor to show error message in UI browser Network
    public ApiException(String message, HttpStatus httpStatus, int statusCode, LocalDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.timestamp = timestamp;
    }

    // constructor to save error message in Database
    public ApiException(String message, Throwable throwable, HttpStatus httpStatus, int statusCode, LocalDateTime timestamp) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
        this.statusCode = statusCode;
        this.timestamp = timestamp;
    }

    // Getters for above Value
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
