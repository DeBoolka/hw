package ru.mirea.dikanev.nikita.messenger.dto.rest.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLogin {

    private String login;
    private String password;

}
