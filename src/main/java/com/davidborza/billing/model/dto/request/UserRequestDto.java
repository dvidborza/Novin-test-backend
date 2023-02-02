package com.davidborza.billing.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Set;
import lombok.Data;

/**
 * Created on 2023. 01. 28.
 *
 * @author David
 */
@Data
public class UserRequestDto {

    private static final int USERNAME_MIN_LENGTH = 4;
    private static final int USERNAME_MAX_LENGTH = 30;

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = USERNAME_MIN_LENGTH, max = USERNAME_MAX_LENGTH)
    private String username;
    private Set<String> roles;
}
