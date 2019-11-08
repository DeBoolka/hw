package ru.mirea.dikanev.nikita.messenger.repository.impl;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import ru.mirea.dikanev.nikita.messenger.entity.Data;

public interface JpaDataRepository extends CrudRepository<Data, Integer> {

    Optional<Data> findDataByUserId(int userId);

}
