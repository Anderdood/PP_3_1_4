package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    public List<User> getAllUsers();

    public User findById(Long id);

    public void delete(Long id);

    void save(String name, String email, String password);

    void update(Long id, String name, String email, String password);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}