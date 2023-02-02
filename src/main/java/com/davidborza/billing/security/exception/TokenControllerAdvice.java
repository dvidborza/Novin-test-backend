package com.davidborza.billing.security.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Created on 2023. 01. 29.
 *
 * @author David
 */
@RestControllerAdvice
public class TokenControllerAdvice {

    /**
     * Handle token refresh exception.
     *
     * @param ex The given TokenRefreshException object.
     * @param request The given WebRequest object.
     * @return The ErrorMessage object.
     */
    @ExceptionHandler(value = TokenRefreshException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage handleTokenRefreshException(final TokenRefreshException ex, final WebRequest request) {
        return new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
    }
}
