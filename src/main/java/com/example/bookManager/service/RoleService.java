package com.example.bookManager.service;

import com.example.bookManager.domain.RoleDetail;
import com.example.bookManager.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService
{
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleDetail getRoleById(int id)
    {
        return roleRepository.findById(id).get();
    }
}
