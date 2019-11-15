package ru.mirea.dikanev.nikita.messenger.repository;

import java.util.List;

import ru.mirea.dikanev.nikita.messenger.entity.Data;
import ru.mirea.dikanev.nikita.messenger.entity.User;
import ru.mirea.dikanev.nikita.messenger.entity.User.Role;

public interface UsersRepository {

    void register(String login, String psw, String sold);

    User get(String login, String password);

    User get(int userId);

    List<User> getUsers();

    void setRole(int userId, Role role);
}
