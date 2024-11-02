package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    public List<User> getAllUsers();

    public User findUserById(Long id);

    User findUserByName (String username);

    public void deleteUser(Long id);

    void saveUser(String name, String email, String password);

    void updateUser(Long id, String name, String email, String password);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}