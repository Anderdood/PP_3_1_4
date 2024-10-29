package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    RoleRepository roleRepository;


    @Autowired
    public UserServiceImpl(UserDao userDao, RoleRepository roleRepository) {
        this.userDao = userDao;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional

    public User findById(Long id) {
        return userDao.findById(id);
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
        user.setPassword("{noop}" + password);
        user.setRoles(Set.of(roleRepository.findByName("ROLE_USER")));
            userDao.save(user);
        }

        @Transactional
    @Override
    public void update(Long id, String name, String email, String password) {
        User user = findById(id);
        if (user != null) {
            user.setUsername(name);
            user.setEmail(email);
            user.setPassword(password);
            userDao.update(user);
        }
    }

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsernameWithRoles(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
