package ru.mirea.dikanev.nikita.messenger.service;

import java.util.List;

import ru.mirea.dikanev.nikita.messenger.dto.rest.UserDto;
import ru.mirea.dikanev.nikita.messenger.dto.rest.auth.UserLogin;
import ru.mirea.dikanev.nikita.messenger.entity.User.Role;

public interface UserService {

    List<UserDto> getUsers();

    List<UserDto> getUsers(String token);

    void setRole(String token, int targetUserId, Role role);

    Role getRole(String token, int userId);

}
