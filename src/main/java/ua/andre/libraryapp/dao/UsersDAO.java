package ua.andre.libraryapp.dao;

import org.springframework.stereotype.Component;
import ua.andre.libraryapp.models.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsersDAO {

    private static final String URL = "jdbc:postgres://localhost:5432/library_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private int ID;
    private List<User> db;

    {
        db = new ArrayList<>();

        db.add(new User(++ID, "Andrew", "Deez", 21));
        db.add(new User(++ID, "Bob", "Deez", 32));
        db.add(new User(++ID, "Tom", "Deez", 33));
        db.add(new User(++ID, "Sam", "Deez", 67));
    }

    public List<User> getAll() {
        return db;
    }

    public User getById(int id) {
        return db.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }
}
