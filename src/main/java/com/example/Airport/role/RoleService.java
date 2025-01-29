package com.example.Airport.role;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role getById(Long id) {
        Optional<Role> role = repository.findById(id);
                // TODO .orElseThrow( () -> new RoleNotFoundException("Role not found"));
        return role.get();
    }

    public Role assignDefaultRole() {
        return this.getById(1L);
    }
}
