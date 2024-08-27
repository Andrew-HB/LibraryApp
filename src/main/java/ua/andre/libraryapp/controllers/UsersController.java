package ua.andre.libraryapp.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.andre.libraryapp.dao.BooksDAO;
import ua.andre.libraryapp.dao.UsersDAO;
import ua.andre.libraryapp.models.User;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UsersDAO usersDAO;
    private final BooksDAO booksDAO;

    @Autowired
    public UsersController(UsersDAO usersDAO, BooksDAO booksDAO) {
        this.usersDAO = usersDAO;
        this.booksDAO = booksDAO;
    }

    @GetMapping()
    public String allUsers(Model model) {
        model.addAttribute("users", usersDAO.getAll());
        return "users/users";
    }

    @GetMapping("/{id}")
    public String usersById (@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersDAO.getById(id));

        model.addAttribute("allUserBooks", booksDAO.getAllBookByUserId(id));

        return "users/user";
    }

    @GetMapping("/{id}/edit")
    public String editUser (@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersDAO.getById(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser (@PathVariable("id") int id, @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "users/edit";

        usersDAO.updateUser(id, user);
        return "redirect:/users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());

        return "users/new";
    }

    @PostMapping()
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {

            return "users/new";
        }

        usersDAO.createUser(user);

        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        usersDAO.deleteUser(id);

        return "redirect:/users";
    }
}
