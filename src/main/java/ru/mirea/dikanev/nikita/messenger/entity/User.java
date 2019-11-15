package ru.mirea.dikanev.nikita.messenger.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = User.USERS_TABLE)
@NoArgsConstructor
public class User {

    public static final String USERS_TABLE = "users";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "login", columnDefinition = "TEXT")
    private String login;

    @Column(name = "password", columnDefinition = "TEXT")
    private String password;

    @Column(name = "sold", columnDefinition = "TEXT")
    private String sold;

    @Enumerated(EnumType.STRING)
//    @Column(name = "role", columnDefinition = "ENUM")
    private Role role = Role.NOT_CONFIRMED;

    public User(String login, String password, String sold) {
        this.login = login;
        this.password = password;
        this.sold = sold;
    }

    public User(String login, String password, String sold, Role role) {
        this.login = login;
        this.password = password;
        this.sold = sold;
        this.role = role;
    }

    public enum Role {
        NOT_CONFIRMED,
        USER,
        ADMIN
    }
}
