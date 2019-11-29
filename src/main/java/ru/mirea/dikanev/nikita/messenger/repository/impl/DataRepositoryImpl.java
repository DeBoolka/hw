package ru.mirea.dikanev.nikita.messenger.repository.impl;

import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.mirea.dikanev.nikita.messenger.entity.Data;
import ru.mirea.dikanev.nikita.messenger.exception.NotFoundException;
import ru.mirea.dikanev.nikita.messenger.repository.DataRepository;

@Repository
@Slf4j
public class DataRepositoryImpl implements DataRepository {

    private JpaDataRepository jpaDataRepository;
    private JpaUsersRepository jpaUsersRepository;

    public DataRepositoryImpl(JpaDataRepository jpaDataRepository, JpaUsersRepository jpaUsersRepository) {
        this.jpaDataRepository = jpaDataRepository;
        this.jpaUsersRepository = jpaUsersRepository;
    }

    @Override
    public void put(int userId, String data) {
//        log.info("Put data for userId: {}\nData: {}", userId, data);
        jpaDataRepository.findDataByUserId(userId).ifPresent(d -> jpaDataRepository.deleteById(d.getId()));
        jpaDataRepository.save(new Data(jpaUsersRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found!")), data));
    }

    @Override
    public Data get(int userId) {
//        log.info("Get data with user_Id: {}", userId);
        return jpaDataRepository.findDataByUserId(userId).orElseGet(() -> {
            put(userId, "");
            return jpaDataRepository.findDataByUserId(userId)
                    .orElseThrow(() -> new InternalException("Failed to add data!"));
        });
    }
}
