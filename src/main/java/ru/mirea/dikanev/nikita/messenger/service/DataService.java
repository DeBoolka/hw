package ru.mirea.dikanev.nikita.messenger.service;

import ru.mirea.dikanev.nikita.messenger.dto.rest.DataDto;

public interface DataService {

    String get(int userId);

    void put(DataDto data);
}
