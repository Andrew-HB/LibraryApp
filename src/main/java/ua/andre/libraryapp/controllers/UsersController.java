package ua.andre.libraryapp.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.andre.libraryapp.models.User;
import ua.andre.libraryapp.services.BookService;
import ua.andre.libraryapp.services.UserService;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public UsersController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String allUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/users";
    }

    @GetMapping("/{id}")
    public String usersById (@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.findById(id));

        model.addAttribute("allUserBooks", bookService.findAllBookByUserId(id));

        return "users/user";
    }

    @GetMapping("/{id}/edit")
    public String editUser (@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser (@PathVariable("id") int id, @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "users/edit";

        userService.update(id, user);
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

        userService.save(user);

        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);

        return "redirect:/users";
    }
}
