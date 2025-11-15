package fr._42.cinema.repositories;

import fr._42.cinema.models.User;
import java.util.Optional;

public interface UsersRepository {
    void save(User user);
    Optional<User> findByEmail(String email);
}