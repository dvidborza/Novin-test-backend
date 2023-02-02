package com.davidborza.billing.security.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2023. 01. 29.
 *
 * @author David
 */
@Data
@Configuration
public class JwtConfiguration {

    @Value("${jwtSecret}")
    private String jwtSecret;

    @Value("${jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${jwtRefreshExpirationMs}")
    private int jwtRefreshExpirationMs;
}
