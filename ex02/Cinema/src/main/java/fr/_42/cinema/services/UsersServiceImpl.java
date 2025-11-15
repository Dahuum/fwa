package fr._42.cinema.services;

import fr._42.cinema.models.AuthenticationHistory;
import fr._42.cinema.models.Image;
import java.util.List;
import fr._42.cinema.models.User;
import fr._42.cinema.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import fr._42.cinema.models.AuthenticationHistory;
import java.util.Optional;

public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(User user) {
        // Check if email already exists
        Optional<User> existingUser = usersRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent())
            throw new IllegalArgumentException("Email already exists");

        // Encrypt password before saving
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        usersRepository.save(user);
    }
    @Override
    public User signIn(String email, String password) {
        Optional<User> userOptional = usersRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Check if password matches
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
    
    
    @Override
    public void saveAuthenticationHistory(Long userId, String ipAddress) {
        AuthenticationHistory auth = new AuthenticationHistory(userId, ipAddress);
        usersRepository.saveAuthenticationHistory(auth);
    }
    
    @Override
    public List<AuthenticationHistory> getAuthenticationHistory(Long userId) {
        return usersRepository.findAuthHistoryByUserId(userId);
    }
    
    
    @Override
    public void saveImage(Image image) {
        usersRepository.saveImage(image);
    }
    
    @Override
    public List<Image> getUserImages(Long userId) {
        return usersRepository.findImagesByUserId(userId);
    }
}
