package com.davidborza.billing.repository;

import com.davidborza.billing.entity.Role;
import com.davidborza.billing.enums.RoleTypeEnum;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on 2023. 01. 28.
 *
 * @author David
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


    /**
     * Find user by the given name.
     *
     * @param name The given name.
     * @return Role or null.
     */
    Optional<Role> findByName(RoleTypeEnum name);
}
