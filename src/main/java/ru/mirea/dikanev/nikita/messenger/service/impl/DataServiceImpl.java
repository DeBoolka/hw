package ru.mirea.dikanev.nikita.messenger.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mirea.dikanev.nikita.messenger.entity.User;
import ru.mirea.dikanev.nikita.messenger.entity.User.Role;
import ru.mirea.dikanev.nikita.messenger.exception.PermissionDeniedException;
import ru.mirea.dikanev.nikita.messenger.repository.DataRepository;
import ru.mirea.dikanev.nikita.messenger.repository.UsersRepository;
import ru.mirea.dikanev.nikita.messenger.service.AuthService;
import ru.mirea.dikanev.nikita.messenger.service.DataService;

@Service
@Slf4j
public class DataServiceImpl implements DataService {

    private AuthService authService;

    private DataRepository dataRepository;
    private UsersRepository usersRepository;

    public DataServiceImpl(AuthService authService, DataRepository dataRepository, UsersRepository usersRepository) {
        this.authService = authService;
        this.dataRepository = dataRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public String get(String token) {
        User user = authService.auth(token);
        if (!Role.NOT_CONFIRMED.equals(user.getRole())) {
            return dataRepository.get(user.getId()).getData();
        } else {
            throw new PermissionDeniedException("Permission Denied!");
        }
    }

    @Override
    public void put(String token, String data) {
        log.info("Put data: {}", data);
        User user = authService.auth(token);
        if (Role.NOT_CONFIRMED.equals(user.getRole())) {
            throw new PermissionDeniedException("Permission Denied!");
        }

        dataRepository.put(user.getId(), data);
    }
}
