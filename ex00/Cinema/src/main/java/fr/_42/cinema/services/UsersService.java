package fr._42.cinema.services;

import fr._42.cinema.models.User;

public interface UsersService {
    void signUp(User user);
    User signIn(String email, String password);
}