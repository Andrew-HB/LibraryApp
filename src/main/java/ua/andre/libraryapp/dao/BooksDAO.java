package ua.andre.libraryapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.andre.libraryapp.models.Book;
import ua.andre.libraryapp.models.User;

import java.util.List;

@Component
public class BooksDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public BooksDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<Book> getAll() {
        Session session = sessionFactory.getCurrentSession();

        List<Book> list = session.createQuery("select b from Book b", Book.class).getResultList();
        //session.createQuery("select p from User p", User.class);

        return list;
    }

    @Transactional
    public Book getById(int id) {
        Session session = sessionFactory.getCurrentSession();

        Book book = session.get(Book.class, id);

        return book;
    }

    @Transactional
    public void updateBook(int id, Book book) {
        Session session = sessionFactory.getCurrentSession();

        Book bookToUpdate = session.get(Book.class, id);

        bookToUpdate.setId(book.getId());
        bookToUpdate.setName(book.getName());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setYear(book.getYear());
        bookToUpdate.setOwner(book.getOwner());
    }

    @Transactional
    public void createBook(Book book) {
        Session session = sessionFactory.getCurrentSession();

        session.save(book);
    }

    @Transactional
    public void deleteBook(int id) {
        Session session = sessionFactory.getCurrentSession();

        Book book = session.get(Book.class, id);

        session.delete(book);
    }

    @Transactional
    public void setBook(int book_id, int user_id) {
        Session session = sessionFactory.getCurrentSession();

        Book book = session.get(Book.class, book_id);
        User user = session.get(User.class, user_id);

        book.setOwner(user);

    }

    @Transactional
    public void freeBook(int book_id) {
        Session session = sessionFactory.getCurrentSession();

        Book book = session.get(Book.class, book_id);

        book.setOwner(null);

    }

    @Transactional
    public List<Book> getAllBookByUserId(int user_id) {

        Session session = sessionFactory.getCurrentSession();

        User user = session.get(User.class, user_id);
        System.out.println(user);
        List<Book> list = user.getBooks();
        //System.out.println(list);
        return list;
    }
}
