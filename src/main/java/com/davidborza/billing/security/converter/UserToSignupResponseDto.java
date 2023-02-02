package com.davidborza.billing.security.converter;

import com.davidborza.billing.entity.Role;
import com.davidborza.billing.entity.User;
import com.davidborza.billing.security.model.dto.response.SignupResponseDto;
import java.util.HashSet;
import java.util.Set;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created on 2023. 01. 29.
 *
 * @author David
 */
@Component
public class UserToSignupResponseDto implements Converter<User, SignupResponseDto> {

    /**
     * Convert User to SignupResponseDto.
     *
     * @param source The given User object.
     * @return SignupResponseDto object.
     */
    @Override
    public SignupResponseDto convert(final @NonNull User source) {
        final SignupResponseDto signupResponseDto = new SignupResponseDto();
        signupResponseDto.setId(source.getId());
        signupResponseDto.setName(source.getName());
        signupResponseDto.setUsername(source.getUsername());
        signupResponseDto.setRoles(this.getRolesStringFromRoles(source.getRoles()));

        return signupResponseDto;
    }

    private Set<String> getRolesStringFromRoles(final Set<Role> roles) {
        final Set<String> rolesStrings = new HashSet<>();
        roles.forEach(role -> rolesStrings.add(role.getName().toString()));

        return rolesStrings;
    }
}
