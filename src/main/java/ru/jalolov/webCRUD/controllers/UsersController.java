package ru.jalolov.webCRUD.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.jalolov.webCRUD.dao.UserDAOimpl;
import ru.jalolov.webCRUD.models.User;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserDAOimpl userDAOimpl;

    @Autowired
    public UsersController(UserDAOimpl userDAOimpl) {
        this.userDAOimpl = userDAOimpl;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userDAOimpl.index());

        return "users/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAOimpl.show(id));
        // получим одного человека по его id из dao и передадим этого человека на представление
        return "users/show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userDAOimpl.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDAOimpl.show(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userDAOimpl.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        userDAOimpl.delete(id);
        return "redirect:/users";
    }
}
