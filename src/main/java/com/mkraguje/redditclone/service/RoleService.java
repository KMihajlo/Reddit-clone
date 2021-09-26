package com.mkraguje.redditclone.service;

import com.mkraguje.redditclone.model.Role;
import com.mkraguje.redditclone.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role save(Role role){
        return roleRepository.save(role);
    }
}
