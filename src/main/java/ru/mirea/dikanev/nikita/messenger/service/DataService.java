package ru.mirea.dikanev.nikita.messenger.service;

public interface DataService {

    String get(String token);

    void put(String token, String data);
}
