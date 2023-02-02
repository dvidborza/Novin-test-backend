package com.davidborza.billing.service;

import com.davidborza.billing.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created on 2023. 01. 28.
 *
 * @author David
 */
public interface UserService {

    /**
     * Save the given user.
     *
     * @param user The given user.
     * @return Saved User.
     */
    User save(User user);

    /**
     * Get all users.
     *
     * @return List of users.
     */
    List<User> getAll();

    /**
     * Get user by username.
     *
     * @param username The given email username.
     * @return User or null.
     */
    Optional<User> getByUsername(String username);

    /**
     * Update the given User.
     *
     * @param id The given ID.
     * @param user The given User object.
     * @return The updated User object.
     */
    User update(Long id, User user);

    /**
     * Update the lastLogIn date in the given User.
     *
     * @param username The given username.
     * @param newDate  The given new LocalDateTime.
     */
    void updateLastLogInDate(String username, LocalDateTime newDate);

    /**
     * Delete User by ID.
     *
     * @param id The given ID.
     */
    void deleteById(Long id);
}
