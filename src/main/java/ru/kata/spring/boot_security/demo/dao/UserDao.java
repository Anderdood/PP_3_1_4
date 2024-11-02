package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    public void saveUser(User user);

    public void deleteUser(Long id);

    public void updateUser(User user);

    public User findUserById(Long Id);

    User findUserByName(String username);

    public List<User> getAllUsers();

    User findUserWithRolesByUsername(String username);

}
