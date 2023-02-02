package com.davidborza.billing.service.impl;

import com.davidborza.billing.entity.Role;
import com.davidborza.billing.enums.RoleTypeEnum;
import com.davidborza.billing.repository.RoleRepository;
import com.davidborza.billing.service.RoleService;
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
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Role save(final Role role) {
        return roleRepository.save(role);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Role> getByName(final RoleTypeEnum name) {
        return roleRepository.findByName(name);
    }
}
