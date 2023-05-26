package com.ua.glebkorobov.checkdb.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Data
@Entity
@Table(name = "procedures")
public class Procedures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Length(min = 1, max = 100)
    @Column(unique = true, nullable = false)
    String name;

    @Positive
    @Column(nullable = false)
    long price;

    public Procedures(String name, long price) {
        this.name = name;
        this.price = price;
    }

    public Procedures() {
    }
}
