package com.davidborza.billing.service;

import com.davidborza.billing.entity.Role;
import com.davidborza.billing.enums.RoleTypeEnum;
import java.util.List;
import java.util.Optional;

/**
 * Created on 2023. 01. 28.
 *
 * @author David
 */
public interface RoleService {

    /**
     * Save the given role.
     *
     * @param role The given role.
     * @return Save Role.
     */
    Role save(Role role);

    /**
     * Get all roles.
     *
     * @return list of roles.
     */
    List<Role> getAll();

    /**
     * Get user by name.
     *
     * @param name The given name.
     * @return Role or null.
     */
    Optional<Role> getByName(RoleTypeEnum name);
}
