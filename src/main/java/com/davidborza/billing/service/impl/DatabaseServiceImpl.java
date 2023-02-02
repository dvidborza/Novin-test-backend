package com.davidborza.billing.service.impl;

import com.davidborza.billing.entity.Role;
import com.davidborza.billing.entity.User;
import com.davidborza.billing.enums.RoleTypeEnum;
import com.davidborza.billing.repository.RoleRepository;
import com.davidborza.billing.repository.UserRepository;
import com.davidborza.billing.service.DatabaseService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.HashSet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("checkstyle:IllegalCatch")
    public void init() {
        final EntityManager entityManager = this.entityManagerFactory.createEntityManager();

        if (roleRepository.count() == 0 && userRepository.count() == 0) {
            try {
                entityManager.getTransaction().begin();
                this.initRoles(entityManager);
                entityManager.getTransaction().commit();
                this.initUsers();
            } catch (final Exception e) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    private void initRoles(final EntityManager entityManager) {
        if (this.roleRepository.count() == 0) {
            final String query = """
                    INSERT INTO roles(id, name, description) VALUES
                        (1, 'ROLE_ADMINISTRATOR', 'Role Administrator.'),
                        (2, 'ROLE_ACCOUNTANT', 'Role Accountant.'),
                        (3, 'ROLE_USER', 'Role User.');
                    """;
            entityManager.createNativeQuery(query).executeUpdate();
        }
    }

    private void initUsers() {
        if (userRepository.count() == 0) {
            final User user1 = new User();
            user1.setUsername("admin");
            user1.setName("Admin User");
            user1.setPassword("$2a$10$NIxICnmB1lhzuE6KHps0UeSwSCeSA1WaQHZwSGSO78/A9Z9Qr3Yc.");
            user1.setRoles(new HashSet<Role>(List.of(roleRepository.findByName(RoleTypeEnum.ROLE_ADMINISTRATOR).get())));

            final User user2 = new User();
            user2.setUsername("accountant");
            user2.setName("Accountant User");
            user2.setPassword("$2a$10$NIxICnmB1lhzuE6KHps0UeSwSCeSA1WaQHZwSGSO78/A9Z9Qr3Yc.");
            user2.setRoles(new HashSet<Role>(List.of(roleRepository.findByName(RoleTypeEnum.ROLE_ACCOUNTANT).get())));

            final User user3 = new User();
            user3.setUsername("user");
            user3.setName("User User");
            user3.setPassword("$2a$10$NIxICnmB1lhzuE6KHps0UeSwSCeSA1WaQHZwSGSO78/A9Z9Qr3Yc.");
            user3.setRoles(new HashSet<Role>(List.of(roleRepository.findByName(RoleTypeEnum.ROLE_USER).get())));

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
        }
    }
}
