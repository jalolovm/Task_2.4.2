package ru.jalolov.webCRUD.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.jalolov.webCRUD.models.User;
import ru.jalolov.webCRUD.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String show(Principal principal, Model model) {
        User user = userService.findByName(principal.getName());
        model.addAttribute("user", user);
        return "users/user";
    }
}
