package ru.mirea.dikanev.nikita.messenger.repository;

import ru.mirea.dikanev.nikita.messenger.entity.Data;

public interface DataRepository {

    void put(int userId, String data);

    Data get(int userId);

}
