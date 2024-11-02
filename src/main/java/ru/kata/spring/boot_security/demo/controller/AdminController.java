package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String listUsers(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userService.findUserByName(userDetails.getUsername());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User());
        return "admin";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.saveUser(user.getUsername(), user.getEmail(), user.getPassword());
        return "redirect:/admin";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PostMapping("/find")
    public String findUserById(@RequestParam Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("users", userService.getAllUsers());// надо чтобы список отображался и дальше
        // (можно и без users если нам нужен только конкретный юзер)
        model.addAttribute("foundUser", user);
        model.addAttribute("user", new User());
        return "admin";
    }
}

