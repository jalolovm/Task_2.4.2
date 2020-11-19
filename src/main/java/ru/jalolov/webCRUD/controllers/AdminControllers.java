package ru.jalolov.webCRUD.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.jalolov.webCRUD.models.Role;
import ru.jalolov.webCRUD.models.User;
import ru.jalolov.webCRUD.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminControllers {
    private final UserService userService;

    @Autowired
    public AdminControllers(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.index());
        return "admin/user-list";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "admin/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(value = "roles_arr", required = false) String[] roles) throws Exception {

        List<String> rolesList = Arrays.asList(roles);
        if (rolesList.contains("ROLE_ADMIN")){
            user.setRoles(Set.of(new Role(2L, "ROLE_ADMIN"),
                                 new Role(1L, "ROLE_USER")));
        } else if ((rolesList.contains("ROLE_USER"))){
            user.setRoles(Set.of(new Role(1L, "ROLE_USER")));
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.show(id));
        return "admin/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id,
                         @RequestParam(value = "roles_arr", defaultValue = "ROLE_USER") String[] roles) {

        List<String> rolesList = Arrays.asList(roles);
        if (rolesList.contains("ROLE_ADMIN")){
            user.setRoles(Set.of(new Role(2L, "ROLE_ADMIN"),
                    new Role(1L, "ROLE_USER")));
        } else if ((rolesList.contains("ROLE_USER"))){
            user.setRoles(Set.of(new Role(1L, "ROLE_USER")));
        }

        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") int id, Model model){
        User user = userService.show(id);
        model.addAttribute("user", user);
        return "users/user";
    }
}
