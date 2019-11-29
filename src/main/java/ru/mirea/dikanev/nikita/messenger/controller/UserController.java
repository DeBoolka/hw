package ru.mirea.dikanev.nikita.messenger.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mirea.dikanev.nikita.messenger.dto.rest.UserDto;
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
    public UserWrapper getUsers(@RequestHeader("Authentication") String token) {
        return new UserWrapper(userService.getUsers(token));
    }

    @GetMapping("/roles")
    public Role getRole(@RequestHeader("Authentication") String token, @RequestBody UserIdWrapper userIdWrapper) {
        return userService.getRole(token, userIdWrapper.getUserId());
    }

    @PostMapping("/roles")
    public void setRole(@RequestHeader("Authentication") String token, @RequestBody RoleWrapper roleWrapper) {
        userService.setRole(token, roleWrapper.getUserId(), roleWrapper.getRole());
    }

    @Data
    @AllArgsConstructor
    private static class UserWrapper{
        private List<UserDto> users;
    }

    //Next piece is the Roma shit

    @Data
    private static class RoleWrapper{
        private int userId;
        private Role role;
    }

    @Data
    private static class UserIdWrapper{
        private int userId;
    }

}
