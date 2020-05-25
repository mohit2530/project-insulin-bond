package com.insulinbond.customErrorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDateTime;

/**
 * Api Exception service class
 */
@Service
public class ApiExceptionService {

    @Inject
    private ApiExceptionRepository apiExceptionRepository;

    /**
     * Throw exception and save the error to data base
     * @param message
     * @param httpStatus
     * @return
     */
    public ApiRequestException throwApiException(String message, HttpStatus httpStatus) {
        ApiRequestException e =  new ApiRequestException(message.toUpperCase(), httpStatus);
        ApiException apiException = new ApiException(
                e.getMessage(),
                e,
                e.getHttpStatus(),
                e.getHttpStatus().value(),
                LocalDateTime.now()
        );
        apiExceptionRepository.save(apiException);
        return e;
    }

}
