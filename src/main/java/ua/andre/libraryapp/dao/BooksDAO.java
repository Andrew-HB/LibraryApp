package ua.andre.libraryapp.dao;

import org.springframework.stereotype.Component;
import ua.andre.libraryapp.models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class BooksDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/library_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAll() {
        List<Book> list = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM book;");

            while(resultSet.next()) {
                list.add(new Book(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("name"),
                        resultSet.getString("author"),
                        resultSet.getString("year")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Book getById(int id) {
        Book book = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM book WHERE id=?;"
            );

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            book = new Book(
                    resultSet.getInt("id"),
                    resultSet.getInt("user_id"),
                    resultSet.getString("name"),
                    resultSet.getString("author"),
                    resultSet.getString("year")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    public void updateBook(int id, Book book) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE book SET \"name\"=?, author=?, \"year\"=? WHERE id=?;"
            );

            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getYear());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createBook(Book book) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO book(\"name\", author, \"year\") VALUES (?, ?, ?);"
            );

            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getYear());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM book WHERE id=?;"
            );

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setBook(int book_id, int user_id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE book SET user_id=? WHERE id=?;"
            );

            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, book_id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeBook(int book_id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE book SET user_id=null WHERE id=?;"
            );

            preparedStatement.setInt(1, book_id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBookByUserId(int user_id) {
        List<Book> list = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM book WHERE user_id=?;"
            );

            preparedStatement.setInt(1, user_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(new Book(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("name"),
                        resultSet.getString("author"),
                        resultSet.getString("year")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
