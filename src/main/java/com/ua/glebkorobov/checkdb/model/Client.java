package com.ua.glebkorobov.checkdb.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.time.LocalDate;

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

    @Past
    LocalDate dateOfBirthday;

    public Client(String phone, String name, LocalDate dateOfBirthday) {
        this.phone = phone;
        this.name = name;
        this.dateOfBirthday = dateOfBirthday;
    }

    public Client(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    public Client() {

    }

    @Override
    public String toString() {
        return  " name " + name +
                ", phone " + phone +
                ", date of birthday " + (dateOfBirthday != null ? dateOfBirthday.toString() + "." : ".");
    }
}
