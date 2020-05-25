package com.insulinbond.customErrorHandler;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * MongoDB Repository for Api Exception which get trigger when the service call this interface
 */
public interface ApiExceptionRepository extends MongoRepository<ApiException, String> {
}
