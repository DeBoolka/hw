package ru.mirea.dikanev.nikita.messenger.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mirea.dikanev.nikita.messenger.entity.auth.Token;
import ru.mirea.dikanev.nikita.messenger.entity.auth.UserLogin;
import ru.mirea.dikanev.nikita.messenger.service.AuthService;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public Token login(UserLogin login) {
        return new Token();
    }
}
