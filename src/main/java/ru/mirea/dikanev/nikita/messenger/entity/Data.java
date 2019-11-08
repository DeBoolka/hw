package ru.mirea.dikanev.nikita.messenger.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Data.DATA_TABLE)
public class Data {

    public static final String DATA_TABLE = "data";

    public Data(User user, String data) {
        this.user = user;
        this.data = data;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "data", columnDefinition = "TEXT")
    private String data;

}
