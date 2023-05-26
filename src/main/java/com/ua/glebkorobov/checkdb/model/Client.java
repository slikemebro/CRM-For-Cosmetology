package com.ua.glebkorobov.checkdb.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Data
@Table(name = "clients")
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(unique = true, nullable = false)
    @Length(min = 10, max = 10)
    String phone;

    @Column(nullable = false)
    @Length(min = 1, max = 100)
    String name;

    public Client(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    public Client() {

    }
}
