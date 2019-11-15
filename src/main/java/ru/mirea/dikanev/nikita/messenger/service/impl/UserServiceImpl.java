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
import ru.mirea.dikanev.nikita.messenger.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<UserDto> getUsers() {
        return usersRepository.getUsers().stream().map(UserMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    public void setRole(UserLogin userLogin, int targetUserId, Role role) {
        User user = usersRepository.get(userLogin.getLogin(), userLogin.getPassword());
        if (!Role.ADMIN.equals(user.getRole())) {
            throw new PermissionDeniedException("Permission Denied!");
        }
        usersRepository.setRole(targetUserId, role);
    }
}
