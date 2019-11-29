package ru.mirea.dikanev.nikita.messenger.dto.rest.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.mirea.dikanev.nikita.messenger.entity.User.Role;

@Data
@AllArgsConstructor
public class LoginDto {

    private Role role;

    private String token;

}
