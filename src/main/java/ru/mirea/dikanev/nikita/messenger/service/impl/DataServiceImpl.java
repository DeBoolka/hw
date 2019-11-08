package ru.mirea.dikanev.nikita.messenger.service.impl;

import org.springframework.stereotype.Service;
import ru.mirea.dikanev.nikita.messenger.dto.rest.DataDto;
import ru.mirea.dikanev.nikita.messenger.repository.DataRepository;
import ru.mirea.dikanev.nikita.messenger.repository.UsersRepository;
import ru.mirea.dikanev.nikita.messenger.service.DataService;

@Service
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
        int userId = usersRepository.get(data.getLogin(), data.getPassword()).getId();
        dataRepository.put(userId, data.getData());
    }
}
