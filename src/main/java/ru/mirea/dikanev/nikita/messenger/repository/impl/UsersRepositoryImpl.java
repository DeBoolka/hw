package ru.mirea.dikanev.nikita.messenger.repository.impl;

import java.util.List;

import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Repository;
import ru.mirea.dikanev.nikita.messenger.entity.User;
import ru.mirea.dikanev.nikita.messenger.entity.User.Role;
import ru.mirea.dikanev.nikita.messenger.exception.NotFoundException;
import ru.mirea.dikanev.nikita.messenger.repository.UsersRepository;

@Repository
@Slf4j
public class UsersRepositoryImpl implements UsersRepository {

    private JpaUsersRepository jpaUsersRepository;

    public UsersRepositoryImpl(JpaUsersRepository jpaUsersRepository) {
        this.jpaUsersRepository = jpaUsersRepository;
    }

    @Override
    public void register(String login, String psw, String sold) {
        jpaUsersRepository.findByLogin(login).ifPresent(user -> {
            throw new InternalException("User already exists");
        });

//        log.info("Save user in register with login: {}", login);
        jpaUsersRepository.save(new User(login, psw, sold));
    }

    @Override
    public User get(String login, String password) {
//        log.info("Get user with login: {}", login);
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

    @Override
    public List<User> getUsers() {
        return jpaUsersRepository.findAll();
    }

    @Override
    public void setRole(int userId, Role role) {
        User user = get(userId);
        user.setRole(role);

        jpaUsersRepository.save(user);
    }

}
