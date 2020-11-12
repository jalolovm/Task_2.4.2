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
    public String show(Principal principal, Model model) {  // сделать через принципал
        User user = userService.findByName(principal.getName());
        model.addAttribute("user", user);
        return "users/user";
    }

//    @GetMapping("/new")
//    public String newUser(Model model) {
//        model.addAttribute("user", new User());
//        return "admin/new";
//    }

//    @PostMapping()
//    public String create(@ModelAttribute("user") User user) {
//        userService.save(user);
//        return "redirect:/users";
//    }

//    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") int id) {
//        model.addAttribute("user", userService.show(id));
//        return "admin/edit";
//    }

//    @PatchMapping("/{id}")
//    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
//        userService.update(id, user);
//        return "redirect:/users";
//    }

//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable("id") int id){
//        userService.delete(id);
//        return "redirect:/users";
//    }
}
