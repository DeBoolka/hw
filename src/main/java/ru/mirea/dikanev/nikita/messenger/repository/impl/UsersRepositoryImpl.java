package ru.mirea.dikanev.nikita.messenger.repository.impl;

import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Repository;
import ru.mirea.dikanev.nikita.messenger.entity.User;
import ru.mirea.dikanev.nikita.messenger.exception.NotFoundException;
import ru.mirea.dikanev.nikita.messenger.repository.UsersRepository;

@Repository
public class UsersRepositoryImpl implements UsersRepository {

    private JpaUsersRepository jpaUsersRepository;

    public UsersRepositoryImpl(JpaUsersRepository jpaUsersRepository) {
        this.jpaUsersRepository = jpaUsersRepository;
    }

    @Override
    public void register(String login, String psw, String sold) {
        jpaUsersRepository.findByLoginAndPassword(login, psw).ifPresent(user -> {
            throw new InternalException("User already exists");
        });

        jpaUsersRepository.save(new User(login, psw, sold));
    }

    @Override
    public User get(String login, String password) {
        String sold = jpaUsersRepository.findUserByLogin(login)
                .orElseThrow(() -> new NotFoundException("Invalid login or password")).getSold();

        return jpaUsersRepository.findByLoginAndPassword(login, DigestUtils.sha256Hex(password + sold))
                .orElseThrow(() -> new NotFoundException("Invalid login or password"));
    }

    @Override
    public User get(int userId) {
        return jpaUsersRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Invalid login or password"));
    }

}
