package com.ua.glebkorobov.checkdb.repository;

import com.ua.glebkorobov.checkdb.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "SELECT * FROM clients WHERE phone=?",nativeQuery = true)
    public Client findByNumber(String clientNumber);
}
