package com.codegym.rate.service;

import com.codegym.rate.model.Role;

public interface RoleService {
    Role findRoleByName(String roleName);

    Iterable<Role> findAll();

    void save(Role role);
}
