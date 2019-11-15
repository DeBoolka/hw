package ru.mirea.dikanev.nikita.messenger.dto.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.mirea.dikanev.nikita.messenger.entity.User.Role;

@Data
@AllArgsConstructor
public class UserDto {

    private int userId;

    private String login;

    private Role role;

}
