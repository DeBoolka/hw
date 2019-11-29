package ru.mirea.dikanev.nikita.messenger.service;

import ru.mirea.dikanev.nikita.messenger.dto.rest.auth.LoginDto;
import ru.mirea.dikanev.nikita.messenger.dto.rest.auth.UserLogin;
import ru.mirea.dikanev.nikita.messenger.entity.User;

public interface AuthService {

    LoginDto login(UserLogin login);

    void register(UserLogin login);

    User auth(String token);

}
