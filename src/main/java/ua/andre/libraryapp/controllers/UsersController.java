package ua.andre.libraryapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.andre.libraryapp.dao.UsersDAO;
import ua.andre.libraryapp.models.User;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UsersDAO usersDAO;

    @Autowired
    public UsersController(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @GetMapping()
    public String allUsers(Model model) {
        model.addAttribute("users", usersDAO.getAll());
        return "users/users";
    }

    @GetMapping("/{id}")
    public String usersById (@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersDAO.getById(id));
        return "users/user";
    }

    @GetMapping("/{id}/edit")
    public String editUser (@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersDAO.getById(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser (@PathVariable("id") int id, @ModelAttribute("user") User user) {
        usersDAO.updateUser(id, user);
        return "redirect:/users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());

        return "users/new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        usersDAO.createUser(user);

        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        usersDAO.deleteUser(id);

        return "redirect:/users";
    }
}
