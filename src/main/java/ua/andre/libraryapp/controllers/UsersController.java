package ua.andre.libraryapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.andre.libraryapp.dao.UsersDAO;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UsersDAO userDAO;

    @Autowired
    public UsersController(UsersDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String allUsers(Model model) {
        model.addAttribute("users", userDAO.getAll());
        return "users/users";
    }

    @GetMapping("/{id}")
    public String usersById (@PathVariable("id") int id, Model model) {
        return null;
    }
}
