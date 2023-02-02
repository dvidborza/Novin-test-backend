package com.davidborza.billing.security.model.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

/**
 * Created on 2023. 01. 29.
 *
 * @author David
 */
@Data
public class JwtResponseDto {
    private String token;
    private final String type = "Bearer";
    private String refreshToken;
    private Long id;
    private String username;
    private List<String> roles;
    private LocalDateTime lastLogIn;

    public JwtResponseDto(final String accessToken, final String refreshToken, final Long id, final String username, final List<String> roles, final LocalDateTime lastLogIn) {
        this.token = accessToken;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.lastLogIn = lastLogIn;
    }
}
