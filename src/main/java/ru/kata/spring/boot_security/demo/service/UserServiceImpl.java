package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleRepository, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByName(String username) {
        return userDao.findByName(username);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    @Transactional
    public void save(String name, String email, String password) {
        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        if (name.equals("admin")) {
            user.setRoles(Set.of(roleRepository.findByName("ROLE_ADMIN")));
        } else {
            user.setRoles(Set.of(roleRepository.findByName("ROLE_USER")));
        }
        userDao.save(user);
    }

    @Override
    @Transactional
    public void update(Long id, String name, String email, String password) {
        User user = findById(id);
        if (user != null) {
            user.setUsername(name);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            userDao.update(user);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsernameWithRoles(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
