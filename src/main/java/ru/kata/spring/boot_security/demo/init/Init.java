package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Init {

    private final UserService userService;

    @Autowired
    public Init(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initUsers() {
        if (userService.findByName("admin") == null) {
            userService.save("admin", "admin@example.com", "1");
        }
        if (userService.findByName("user") == null) {
            userService.save("user", "user@example.com", "1");
        }
    }

}
