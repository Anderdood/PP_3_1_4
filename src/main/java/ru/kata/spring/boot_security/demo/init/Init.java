package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class Init {

    private final UserService userService;
    private final RoleDao roleRepository;


    @Autowired
    public Init(UserService userService, RoleDao roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void initUsers() {
        if (roleRepository.findRoleByName("ROLE_ADMIN") == null) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.saveRole(adminRole);
        }
        if (roleRepository.findRoleByName("ROLE_USER") == null) {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.saveRole(userRole);
        }

        if (userService.findUserByName("admin") == null) {
            userService.saveUser("admin", "admin@example.com", "1");
        }
        if (userService.findUserByName("user") == null) {
            userService.saveUser("user", "user@example.com", "1");
        }
    }


}
