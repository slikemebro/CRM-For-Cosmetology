package com.ua.glebkorobov.checkdb.repository;

import com.ua.glebkorobov.checkdb.model.Procedures;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProceduresRepository extends JpaRepository<Procedures, Long> {
}
