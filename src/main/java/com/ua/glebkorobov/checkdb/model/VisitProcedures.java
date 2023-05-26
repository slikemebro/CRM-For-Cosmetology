package com.ua.glebkorobov.checkdb.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "visit_procedures")
public class VisitProcedures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "procedure_id", nullable = false)
    private Procedures procedures;

    @Column(nullable = false)
    private long count;

    @Column(nullable = false)
    private long money;

    public VisitProcedures(long count, long money) {
        this.count = count;
        this.money = money;
    }

    public VisitProcedures() {
    }
}
