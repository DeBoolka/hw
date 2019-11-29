package ru.mirea.dikanev.nikita.messenger.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import ru.mirea.dikanev.nikita.messenger.dto.rest.UserDto;
import ru.mirea.dikanev.nikita.messenger.dto.rest.auth.UserLogin;
import ru.mirea.dikanev.nikita.messenger.entity.User;
import ru.mirea.dikanev.nikita.messenger.entity.User.Role;
import ru.mirea.dikanev.nikita.messenger.exception.PermissionDeniedException;
import ru.mirea.dikanev.nikita.messenger.mapper.UserMapper;
import ru.mirea.dikanev.nikita.messenger.repository.UsersRepository;
import ru.mirea.dikanev.nikita.messenger.service.AuthService;
import ru.mirea.dikanev.nikita.messenger.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private AuthService authService;

    private UsersRepository usersRepository;

    public UserServiceImpl(AuthService authService, UsersRepository usersRepository) {
        this.authService = authService;
        this.usersRepository = usersRepository;
    }

    @Override
    public List<UserDto> getUsers() {
        return usersRepository.getUsers().stream().map(UserMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsers(String token) {
        User user = authService.auth(token);
        if (!Role.ADMIN.equals(user.getRole())) {
            throw new PermissionDeniedException("Permission Denied!");
        }

        //Next piece is the Roma shit
        return usersRepository.getUsers().stream().map(UserMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    public void setRole(String token, int targetUserId, Role role) {
        User user = authService.auth(token);
        if (!Role.ADMIN.equals(user.getRole())) {
            throw new PermissionDeniedException("Permission Denied!");
        }
        usersRepository.setRole(targetUserId, role);
    }

    @Override
    public Role getRole(String token, int userId) {
        User user = authService.auth(token);
        if (!Role.ADMIN.equals(user.getRole())) {
            throw new PermissionDeniedException("Permission Denied!");
        }

        return usersRepository.get(userId).getRole();
    }
}
