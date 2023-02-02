package com.davidborza.billing.security.model.dto.response;

import lombok.Data;

/**
 * Created on 2023. 01. 29.
 *
 * @author David
 */
@Data
public class TokenRefreshResponseDto {
    private String accessToken;
    private String refreshToken;
    private final String tokenType = "Bearer";

    public TokenRefreshResponseDto(final String accessToken, final String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
