package ua.andre.libraryapp.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.andre.libraryapp.dao.BooksDAO;
import ua.andre.libraryapp.dao.UsersDAO;
import ua.andre.libraryapp.models.Book;
import ua.andre.libraryapp.models.User;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksDAO booksDAO;
    private final UsersDAO usersDAO;

    @Autowired
    public BooksController(BooksDAO booksDAO, UsersDAO usersDAO) {
        this.booksDAO = booksDAO;
        this.usersDAO = usersDAO;
    }

    @GetMapping()
    public String allBooks(Model model) {
        model.addAttribute("books", booksDAO.getAll());

        return "books/books";
    }

    @GetMapping("/{id}")
    public String bookById(@PathVariable("id") int id, Model model, @ModelAttribute("bookUser") User bookUser) {
        model.addAttribute("book", booksDAO.getById(id));
        model.addAttribute("users", usersDAO.getAll());

        return "books/book";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksDAO.getById(id));

        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int id, @Valid @ModelAttribute("book") Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "books/edit";

        booksDAO.updateBook(id, book);

        return "redirect:/books";
    }

    @PatchMapping("/{id}/set")
    public String setBook(@PathVariable("id") int book_id, @ModelAttribute("bookUser") User bookUser) {
        booksDAO.setBook(book_id, bookUser.getId());

        return "redirect:/books";
    }

    @PatchMapping("/{id}/free")
    public String freeBook(@PathVariable("id") int book_id) {
        booksDAO.freeBook(book_id);

        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());

        return "books/new";
    }

    @PostMapping()
    public String createBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "books/new";

        booksDAO.createBook(book);

        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        booksDAO.deleteBook(id);

        return "redirect:/books";
    }
}