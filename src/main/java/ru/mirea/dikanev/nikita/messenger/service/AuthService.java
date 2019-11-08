package ru.mirea.dikanev.nikita.messenger.service;

import ru.mirea.dikanev.nikita.messenger.dto.rest.auth.UserLogin;

public interface AuthService {

    String login(UserLogin login);

    void register(UserLogin login);
}
