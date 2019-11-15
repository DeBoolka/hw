package ru.mirea.dikanev.nikita.messenger.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.mirea.dikanev.nikita.messenger.dto.rest.DataDto;
import ru.mirea.dikanev.nikita.messenger.entity.User;
import ru.mirea.dikanev.nikita.messenger.entity.User.Role;
import ru.mirea.dikanev.nikita.messenger.exception.PermissionDeniedException;
import ru.mirea.dikanev.nikita.messenger.repository.DataRepository;
import ru.mirea.dikanev.nikita.messenger.repository.UsersRepository;
import ru.mirea.dikanev.nikita.messenger.service.DataService;

@Service
@Slf4j
public class DataServiceImpl implements DataService {

    private DataRepository dataRepository;
    private UsersRepository usersRepository;

    public DataServiceImpl(DataRepository dataRepository, UsersRepository usersRepository) {
        this.dataRepository = dataRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public String get(int userId) {
        return dataRepository.get(userId).getData();
    }

    @Override
    public void put(DataDto data) {
        log.info("Put data: {}", data);
        User user = usersRepository.get(data.getLogin(), data.getPassword());
        if (Role.NOT_CONFIRMED.equals(user.getRole())) {
            throw new PermissionDeniedException("Permission Denied!");
        }

        dataRepository.put(user.getId(), data.getData());
    }
}
