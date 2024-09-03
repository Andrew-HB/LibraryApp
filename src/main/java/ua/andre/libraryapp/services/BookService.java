package ua.andre.libraryapp.services;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.andre.libraryapp.models.Book;
import ua.andre.libraryapp.models.User;
import ua.andre.libraryapp.repositories.BookRepository;
import ua.andre.libraryapp.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(int id) {
        Optional<Book> foundBook = bookRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void setBookToUser(int book_id, int user_id) {

        Book book = bookRepository.findById(book_id).orElse(null);
        User user = userRepository.findById(user_id).orElse(null);

        book.setOwner(user);
    }

    @Transactional
    public void freeBook(int book_id) {
        Book book = bookRepository.findById(book_id).orElse(null);

        book.setOwner(null);
    }

    @Transactional
    public List<Book> findAllBookByUserId(int user_id) {
        User user = userRepository.findById(user_id).orElse(null);
        List<Book> list = user.getBooks();

        return list;
    }
}
