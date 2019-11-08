package ru.mirea.dikanev.nikita.messenger.service.impl;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import ru.mirea.dikanev.nikita.messenger.dto.rest.auth.UserLogin;
import ru.mirea.dikanev.nikita.messenger.entity.Data;
import ru.mirea.dikanev.nikita.messenger.entity.User;
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
    }

    @Override
    public void register(UserLogin login) {
        String sold = String.valueOf(System.currentTimeMillis());
        String soldPassword = DigestUtils.sha256Hex(login.getPassword() + sold);

        usersRepository.register(login.getLogin(), soldPassword, sold);
    }

    @Override
    public String login(UserLogin login) {
        User user = usersRepository.get(login.getLogin(), login.getPassword());
        return dataService.get(user.getId());
    }
}
