package ru.mirea.dikanev.nikita.messenger.controller;

import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mirea.dikanev.nikita.messenger.dto.rest.auth.UserLogin;
import ru.mirea.dikanev.nikita.messenger.service.AuthService;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public void register(@RequestBody UserLogin login) {
        authService.register(login);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLogin login) {
        return authService.login(login);
    }

}
