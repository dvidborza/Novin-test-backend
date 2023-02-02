package com.davidborza.billing.repository;

import com.davidborza.billing.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on 2023. 01. 28.
 *
 * @author David
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by the given username.
     *
     * @param username The given username.
     * @return User or null.
     */
    Optional<User> findByUsername(String username);

    /**
     * User is exists by username.
     *
     * @param username The given username.
     * @return If exist is true else false.
     */
    Boolean existsByUsername(String username);
}
