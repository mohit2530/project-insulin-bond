package com.insulinbond.customErrorHandler;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository for Api Exception which get trigger when the service call this interface
 */
public interface ApiExceptionRepository extends JpaRepository<ApiException, Long> {
}
