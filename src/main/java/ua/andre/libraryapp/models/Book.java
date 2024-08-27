package ua.andre.libraryapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

//CREATE TABLE book(
//        id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
//        user_id int REFERENCES customer(id) ON DELETE SET NULL,
//        "name" varchar NOT NULL CHECK (LENGTH("name") BETWEEN 2 AND 30),
//        author varchar NOT NULL CHECK (LENGTH("author") BETWEEN 2 AND 30),
//        "year" varchar NOT NULL CHECK (LENGTH("author") BETWEEN 1 AND 30)
//        )

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;

    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters!")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Author name should not be empty!")
    @Size(min = 2, max = 30, message = "Author name should be between 2 and 30 characters!")
    @Column(name = "author")
    private String author;

    @NotEmpty(message = "Year should not be empty!")
    @Size(min = 2, max = 30, message = "Year should be between 1 and 30 characters!")
    @Column(name = "year")
    private String year;

    public Book() {
    }

    public Book(User owner, String name, String author, String year) {
        this.owner = owner;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User user) {
        this.owner = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}

//    INSERT INTO book (user_id, "name", author, "year") VALUES (1, 'The Great Gatsby', 'F. Scott Fitzgerald', 1925);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (1, 'To Kill a Mockingbird', 'Harper Lee', 1960);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (1, '1984', 'George Orwell', 1949);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (1, 'The Catcher in the Rye', 'J.D. Salinger', 1951);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (1, 'Pride and Prejudice', 'Jane Austen', 1813);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (1, 'To the Lighthouse', 'Virginia Woolf', 1927);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (1, 'Moby-Dick', 'Herman Melville', 1851);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (1, 'The Hobbit', 'J.R.R. Tolkien', 1937);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (1, 'Jane Eyre', 'Charlotte Bronte', 1847);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (2, 'Brave New World', 'Aldous Huxley', 1932);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (2, 'The Lord of the Rings', 'J.R.R. Tolkien', 1954);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (2, 'The Odyssey', 'Homer', '8th century BC');
//    INSERT INTO book (user_id, "name", author, "year") VALUES (2, 'Crime and Punishment', 'Fyodor Dostoevsky', 1866);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (2, 'The Great Expectations', 'Charles Dickens', 1861);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (2, 'The Picture of Dorian Gray', 'Oscar Wilde', 1890);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (3, 'Gone with the Wind', 'Margaret Mitchell', 1936);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (3, 'Don Quixote', 'Miguel de Cervantes', 1605);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (3, 'One Hundred Years of Solitude', 'Gabriel Garcia Marquez', 1967);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (4, 'The Alchemist', 'Paulo Coelho', 1988);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (5, 'War and Peace', 'Leo Tolstoy', 1869);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (6, 'The Chronicles of Narnia', 'C.S. Lewis', 1950);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (6, 'Frankenstein', 'Mary Shelley', 1818);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (6, 'The Sun Also Rises', 'Ernest Hemingway', 1926);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (null, 'Anna Karenina', 'Leo Tolstoy', 1877);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (null, 'The Brothers Karamazov', 'Fyodor Dostoevsky', 1880);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (null, 'The Count of Monte Cristo', 'Alexandre Dumas', 1844);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (10, 'The Scarlet Letter', 'Nathaniel Hawthorne', 1850);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (10, 'The Divine Comedy', 'Dante Alighieri', 1320);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (10, 'The Grapes of Wrath', 'John Steinbeck', 1939);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (10, 'Wuthering Heights', 'Emily Bronte', 1847);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (null, 'The Old Man and the Sea', 'Ernest Hemingway', 1952);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (13, 'Slaughterhouse-Five', 'Kurt Vonnegut', 1969);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (13, 'The Little Prince', 'Antoine de Saint-Exupery', 1943);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (13, 'The Adventures of Tom Sawyer', 'Mark Twain', 1876);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (13, 'The Canterbury Tales', 'Geoffrey Chaucer', '14th century');
//    INSERT INTO book (user_id, "name", author, "year") VALUES (13, 'The Hound of the Baskervilles', 'Arthur Conan Doyle', 1902);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (13, 'The Grapes of Wrath', 'John Steinbeck', 1939);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (15, 'The Shining', 'Stephen King', 1977);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (null, 'The War of the Worlds', 'H.G. Wells', 1898);
//    INSERT INTO book (user_id, "name", author, "year") VALUES (null, 'Lord of the Flies', 'William Golding', 1954);