package ru.mirea.dikanev.nikita.messenger.service.impl;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import ru.mirea.dikanev.nikita.messenger.dto.rest.auth.UserLogin;
import ru.mirea.dikanev.nikita.messenger.entity.Data;
import ru.mirea.dikanev.nikita.messenger.entity.User;
import ru.mirea.dikanev.nikita.messenger.entity.User.Role;
import ru.mirea.dikanev.nikita.messenger.exception.PermissionDeniedException;
import ru.mirea.dikanev.nikita.messenger.repository.UsersRepository;
import ru.mirea.dikanev.nikita.messenger.service.AuthService;
import ru.mirea.dikanev.nikita.messenger.service.DataService;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private DataService dataService;

    private UsersRepository usersRepository;

    public AuthServiceImpl(DataService dataService, UsersRepository usersRepository) {
        this.dataService = dataService;
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
    public String login(UserLogin login) {
        log.info("Login user: {}", login);
        User user = usersRepository.get(login.getLogin(), login.getPassword());
        if (!Role.NOT_CONFIRMED.equals(user.getRole())) {
            return dataService.get(user.getId());
        } else {
            throw new PermissionDeniedException("Permission Denied!");
        }
    }

    private String getHash(String password, String sold) {
        return DigestUtils.sha256Hex(password + sold);
    }

    private void initRootUser() {
        UserLogin userLogin = new UserLogin("root", "root");
        register(userLogin);

        User user = usersRepository.get(userLogin.getLogin(), userLogin.getPassword());
        usersRepository.setRole(user.getId(), Role.ADMIN);
    }
}
