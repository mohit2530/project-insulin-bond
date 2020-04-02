package com.insulinbond.customErrorHandler;

import org.springframework.http.HttpStatus;

/**
 * Api Request Exception  which extends run time exception
 */
public class ApiRequestException extends RuntimeException {

    // Http status
    private HttpStatus httpStatus;

    /**
     * Constructor for error message and httpstatus
     */
    public ApiRequestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    // Getter and setter for above value
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
