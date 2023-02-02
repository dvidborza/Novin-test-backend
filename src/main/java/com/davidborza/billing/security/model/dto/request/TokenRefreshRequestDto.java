package com.davidborza.billing.security.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Created on 2023. 01. 29.
 *
 * @author David
 */
@Data
public class TokenRefreshRequestDto {

    @NotBlank
    private String refreshToken;
}
