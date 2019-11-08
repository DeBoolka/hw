package ru.mirea.dikanev.nikita.messenger.dto.rest.auth;

import lombok.Data;

@Data
public class UserLogin {

    private String login;
    private String password;

}
