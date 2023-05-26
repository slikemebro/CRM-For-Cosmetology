package com.ua.glebkorobov.checkdb.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
