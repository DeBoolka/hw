package ru.mirea.dikanev.nikita.messenger.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mirea.dikanev.nikita.messenger.entity.auth.Token;
import ru.mirea.dikanev.nikita.messenger.entity.auth.UserLogin;
import ru.mirea.dikanev.nikita.messenger.service.AuthService;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Token login(@RequestBody UserLogin login) {
        return authService.login(login);
    }

}
