package fr._42.cinema.repositories;

import fr._42.cinema.models.AuthenticationHistory;
import fr._42.cinema.models.Image;
import fr._42.cinema.models.User;
import java.util.List;
import java.util.Optional;

public interface UsersRepository {
    void save(User user);
    Optional<User> findByEmail(String email);
    
    // Authentication history methods
    void saveAuthenticationHistory(AuthenticationHistory auth);
    List<AuthenticationHistory> findAuthHistoryByUserId(Long userId);
    
    // Image methods
    void saveImage(Image image);
    List<Image> findImagesByUserId(Long userId);
}