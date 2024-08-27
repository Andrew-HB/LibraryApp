package ua.andre.libraryapp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.andre.libraryapp.models.User;

import java.sql.*;
import java.util.List;

@Component
public class UsersDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UsersDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<User> getAll() {
        Session session = sessionFactory.getCurrentSession();

        List<User> list = session.createQuery("select p from User p", User.class).getResultList();

        return list;
    }

    @Transactional
    public User getById(int id) {
        Session session = sessionFactory.getCurrentSession();

        User user = session.get(User.class, id);

        return user;
    }

    @Transactional
    public void updateUser(int id, User user) {
        Session session = sessionFactory.getCurrentSession();

        User userToUpdate = session.get(User.class, id);

        userToUpdate.setName(user.getName());
        userToUpdate.setSurname(user.getSurname());
        userToUpdate.setAge(user.getAge());
    }

    @Transactional
    public void createUser(User user) {
        Session session = sessionFactory.getCurrentSession();

        session.save(user);
    }

    @Transactional
    public void deleteUser(int id) {
        Session session = sessionFactory.getCurrentSession();

        User user = session.get(User.class, id);

        session.delete(user);
    }
}
