package com.davidborza.billing.service.impl;

import com.davidborza.billing.repository.RoleRepository;
import com.davidborza.billing.repository.UserRepository;
import com.davidborza.billing.service.DatabaseService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created on 2023. 02. 02.
 *
 * @author David
 */
@Service
@RequiredArgsConstructor
public class DatabaseServiceImpl implements DatabaseService {

    private final EntityManagerFactory entityManagerFactory;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    @PostConstruct
    @SuppressWarnings("checkstyle:IllegalCatch")
    public void init() {
        final EntityManager entityManager = this.entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            final boolean rolesWasEmpty = this.initRoles(entityManager);
            final boolean usersWasEmpty = this.initUsers(entityManager);
            this.initUserRoles(entityManager, rolesWasEmpty, usersWasEmpty);
            entityManager.getTransaction().commit();
        } catch (final Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    private boolean initRoles(final EntityManager entityManager) {
        final boolean isEmpty = this.roleRepository.count() == 0;

        if (isEmpty) {
            final String query = """
                    INSERT INTO roles(id, name, description) VALUES
                        (1, 'ROLE_ADMINISTRATOR', 'Role Administrator.'),
                        (2, 'ROLE_ACCOUNTANT', 'Role Accountant.'),
                        (3, 'ROLE_USER', 'Role User.');
                    """;
            entityManager.createNativeQuery(query).executeUpdate();
        }

        return isEmpty;
    }

    private boolean initUsers(final EntityManager entityManager) {
        final boolean isEmpty = this.userRepository.count() == 0;

        if (isEmpty) {
            final String query = """
                    INSERT INTO users(id, name, username, password, last_log_in) VALUES
                        (1, 'user1', 'user','$2a$10$NIxICnmB1lhzuE6KHps0UeSwSCeSA1WaQHZwSGSO78/A9Z9Qr3Yc.', null),
                        (2, 'user1', 'accountant','$2a$10$NIxICnmB1lhzuE6KHps0UeSwSCeSA1WaQHZwSGSO78/A9Z9Qr3Yc.', null),
                        (3, 'user1', 'admin','$2a$10$NIxICnmB1lhzuE6KHps0UeSwSCeSA1WaQHZwSGSO78/A9Z9Qr3Yc.', null);
                    """;
            entityManager.createNativeQuery(query).executeUpdate();
        }

        return isEmpty;
    }

    private void initUserRoles(final EntityManager entityManager, final boolean rolesWasEmpty, final boolean usersWasEmpty) {
        if (rolesWasEmpty && usersWasEmpty) {
            final String query = """
                    INSERT INTO user_roles(user_id, role_id) VALUES
                        (1, 3),
                        (2, 2),
                        (2, 3),
                        (3, 1);
                    """;
            entityManager.createNativeQuery(query).executeUpdate();
        }
    }
}
