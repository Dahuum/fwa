package fr._42.cinema.services;

import fr._42.cinema.models.AuthenticationHistory;
import fr._42.cinema.models.Image;
import fr._42.cinema.models.User;
import java.util.List;

public interface UsersService {
    void signUp(User user);
    User signIn(String email, String password);
    void saveAuthenticationHistory(Long userId, String ipAddress);
    List<AuthenticationHistory> getAuthenticationHistory(Long userId);
    
    // Image methods
    void saveImage(Image image);
    List<Image> getUserImages(Long userId);
}