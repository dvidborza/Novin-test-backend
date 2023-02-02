package com.davidborza.billing.converter;

import com.davidborza.billing.entity.Role;
import com.davidborza.billing.entity.User;
import com.davidborza.billing.enums.RoleTypeEnum;
import com.davidborza.billing.model.dto.request.UserRequestDto;
import com.davidborza.billing.service.RoleService;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created on 2023. 01. 28.
 *
 * @author David
 */
@Component
@RequiredArgsConstructor
public class UserRequestDtoToUser implements Converter<UserRequestDto, User> {

    private final RoleService roleService;
    private final PasswordEncoder encoder;

    /**
     * Convert UserRequestDto to User.
     *
     * @param source The given UserRequestDto object.
     * @return User object.
     */
    @Override
    public User convert(final @NonNull UserRequestDto source) {
        final User user = new User();
        user.setId(source.getId());
        user.setUsername(source.getUsername());
        user.setName(source.getName());
        user.setRoles(this.getRolesFromString(source.getRoles()));

        return user;
    }

    private Set<Role> getRolesFromString(final Set<String> rolesString) {
        final Set<Role> roles = new HashSet<>();

        if (rolesString != null) {
            rolesString.forEach(role -> {
                final Optional<Role> foundRole = Optional.ofNullable(roleService.getByName(RoleTypeEnum.valueOf(role))
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found or not exist.")));

                foundRole.ifPresent(roles::add);
            });

        } else {
            final Optional<Role> role = roleService.getByName(RoleTypeEnum.ROLE_USER);
            role.ifPresent(roles::add);
        }

        return roles;
    }
}
