package com.example.Airport.role;

import com.example.Airport.role.exceptions.RoleNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role getById(Long id) {
        Role role = repository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));
        return role;
    }

    public Role assignDefaultRole() {
        return this.getById(1L);
    }
}
