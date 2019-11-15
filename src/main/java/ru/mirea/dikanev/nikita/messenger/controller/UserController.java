package ru.mirea.dikanev.nikita.messenger.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mirea.dikanev.nikita.messenger.dto.rest.UserDto;
import ru.mirea.dikanev.nikita.messenger.dto.rest.auth.UserLogin;
import ru.mirea.dikanev.nikita.messenger.entity.User.Role;
import ru.mirea.dikanev.nikita.messenger.service.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/{userId}/role/{role}")
    public void setRole(@RequestBody UserLogin userLogin, @PathVariable int userId, @PathVariable Role role) {
        userService.setRole(userLogin, userId, role);
    }

}
