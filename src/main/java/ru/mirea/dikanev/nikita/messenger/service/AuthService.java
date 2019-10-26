package ru.mirea.dikanev.nikita.messenger.service;

import ru.mirea.dikanev.nikita.messenger.entity.auth.Token;
import ru.mirea.dikanev.nikita.messenger.entity.auth.UserLogin;

public interface AuthService {

    Token login(UserLogin login);

}
