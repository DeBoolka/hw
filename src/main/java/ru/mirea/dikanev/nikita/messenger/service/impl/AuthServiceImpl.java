package ru.mirea.dikanev.nikita.messenger.service.impl;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import ru.mirea.dikanev.nikita.messenger.dto.rest.auth.LoginDto;
import ru.mirea.dikanev.nikita.messenger.dto.rest.auth.UserLogin;
import ru.mirea.dikanev.nikita.messenger.entity.User;
import ru.mirea.dikanev.nikita.messenger.entity.User.Role;
import ru.mirea.dikanev.nikita.messenger.exception.NotFoundException;
import ru.mirea.dikanev.nikita.messenger.repository.UsersRepository;
import ru.mirea.dikanev.nikita.messenger.service.AuthService;
import ru.mirea.dikanev.nikita.messenger.service.DataService;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private Map<String, Integer> authUsers = new HashMap<>();

    private UsersRepository usersRepository;

    public AuthServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;

        initRootUser();
    }

    @Override
    public void register(UserLogin login) {
        log.info("Register user: {}", login);
        String sold = String.valueOf(System.currentTimeMillis());
        String soldPassword = getHash(login.getPassword(), sold);

        usersRepository.register(login.getLogin(), soldPassword, sold);
    }

    @Override
    public User auth(String token) {
        Integer userId = authUsers.get(token);
        if (userId == null) {
            throw new NotFoundException(("Invalid token"));
        }

        return usersRepository.get(userId);
    }

    @Override
    public LoginDto login(UserLogin login) {
        log.info("Login user: {}", login);
        User user = usersRepository.get(login.getLogin(), login.getPassword());
        return new LoginDto(user.getRole(), createToken(user.getId()));
    }

    private String createToken(int userId) {
        String token = DigestUtils.sha256Hex(String.valueOf(System.currentTimeMillis()));
        authUsers.put(token, userId);
        return token;
    }

    private String getHash(String password, String sold) {
        return DigestUtils.sha256Hex(password + sold);
    }

    private void initRootUser() {
        UserLogin userLogin = new UserLogin("root", "RomanNarkoman228");
        register(userLogin);

        User user = usersRepository.get(userLogin.getLogin(), userLogin.getPassword());
        usersRepository.setRole(user.getId(), Role.ADMIN);
    }
}
