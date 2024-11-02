package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

public interface RoleDao {
    Role findRoleByName(String name);

    void saveRole(Role role);
}