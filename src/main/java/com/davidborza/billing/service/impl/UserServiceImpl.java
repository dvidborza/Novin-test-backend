package com.davidborza.billing.service.impl;

import com.davidborza.billing.entity.User;
import com.davidborza.billing.repository.UserRepository;
import com.davidborza.billing.service.UserService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created on 2023. 01. 28.
 *
 * @author David
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public User save(final User user) {
        return userRepository.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> getByUsername(final String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User update(final Long id, final User user) {
        final Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isPresent()) {
            user.setId(existingUser.get().getId());
            user.setPassword(existingUser.get().getPassword());
            user.setLastLogIn(existingUser.get().getLastLogIn());
            return userRepository.save(user);
        } else {
            throw new NullPointerException("User is not found");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateLastLogInDate(final String username, final LocalDateTime newDate) {
        final User user = userRepository.findByUsername(username).get();
        user.setLastLogIn(newDate);

        userRepository.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(final Long id) {
        userRepository.deleteById(id);
    }
}
