package com.davidborza.billing.converter;

import com.davidborza.billing.entity.Role;
import com.davidborza.billing.entity.User;
import com.davidborza.billing.model.dto.response.UserResponseDto;
import java.util.HashSet;
import java.util.Set;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created on 2023. 01. 28.
 *
 * @author David
 */
@Component
public class UserToUserResponseDto implements Converter<User, UserResponseDto> {

    /**
     * Convert User to UserResponseDto.
     *
     * @param source The given User object.
     * @return UserResponseDto object.
     */
    @Override
    public UserResponseDto convert(final @NonNull User source) {
        final UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(source.getId());
        userResponseDto.setName(source.getName());
        userResponseDto.setUsername(source.getUsername());
        userResponseDto.setLastLogIn(source.getLastLogIn());
        userResponseDto.setRoles(this.getRolesStringFromRoles(source.getRoles()));

        return userResponseDto;
    }

    private Set<String> getRolesStringFromRoles(final Set<Role> roles) {
        final Set<String> rolesStrings = new HashSet<>();
        roles.forEach(role -> rolesStrings.add(role.getName().toString()));

        return rolesStrings;
    }
}
