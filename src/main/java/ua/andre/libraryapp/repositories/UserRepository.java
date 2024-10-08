package ua.andre.libraryapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.andre.libraryapp.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
