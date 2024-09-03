package ua.andre.libraryapp.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.andre.libraryapp.models.Book;
import ua.andre.libraryapp.models.User;
import ua.andre.libraryapp.services.BookService;
import ua.andre.libraryapp.services.UserService;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public BooksController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping()
    public String allBooks(Model model) {
        model.addAttribute("books", bookService.findAll());

        return "books/books";
    }

    @GetMapping("/{id}")
    public String bookById(@PathVariable("id") int id, Model model, @ModelAttribute("bookUser") User bookUser) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("users", userService.findAll());

        return "books/book";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.findById(id));

        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int id, @Valid @ModelAttribute("book") Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "books/edit";

        bookService.update(id, book);

        return "redirect:/books";
    }

    @PatchMapping("/{id}/set")
    public String setBook(@PathVariable("id") int book_id, @ModelAttribute("bookUser") User bookUser) {
        bookService.setBookToUser(book_id, bookUser.getId());

        return "redirect:/books";
    }

    @PatchMapping("/{id}/free")
    public String freeBook(@PathVariable("id") int book_id) {
        bookService.freeBook(book_id);

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

        bookService.save(book);

        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookService.delete(id);

        return "redirect:/books";
    }
}