package ru.mirea.dikanev.nikita.messenger.repository.impl;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.mirea.dikanev.nikita.messenger.entity.User;

interface JpaUsersRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByLogin(String login);

    Optional<User> findByLoginAndPassword(String login, String password);
}