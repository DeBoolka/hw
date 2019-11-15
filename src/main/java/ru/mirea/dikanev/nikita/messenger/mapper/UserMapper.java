package ru.mirea.dikanev.nikita.messenger.mapper;

import ru.mirea.dikanev.nikita.messenger.dto.rest.UserDto;
import ru.mirea.dikanev.nikita.messenger.entity.User;

public class UserMapper {

    public static UserDto toUserDto(User user){
        return new UserDto(user.getId(), user.getLogin(), user.getRole());
    }

}
