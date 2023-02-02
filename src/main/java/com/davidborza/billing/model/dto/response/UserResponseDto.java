package com.davidborza.billing.model.dto.response;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Data;

/**
 * Created on 2023. 01. 28.
 *
 * @author David
 */
@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String username;
    private LocalDateTime lastLogIn;
    private Set<String> roles;
}
