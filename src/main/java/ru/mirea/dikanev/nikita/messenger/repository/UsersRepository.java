package ru.mirea.dikanev.nikita.messenger.repository;

import ru.mirea.dikanev.nikita.messenger.entity.Data;
import ru.mirea.dikanev.nikita.messenger.entity.User;

public interface UsersRepository {

    void register(String login, String psw, String sold);

    User get(String login, String password);

    User get(int userId);
}
