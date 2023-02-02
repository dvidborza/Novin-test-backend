package com.davidborza.billing.security.exception;

import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created on 2023. 01. 29.
 *
 * @author David
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class TokenRefreshException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public TokenRefreshException(final String token, final String message) {
        super(String.format("Failed for [%s]: %s", token, message));
    }
}
