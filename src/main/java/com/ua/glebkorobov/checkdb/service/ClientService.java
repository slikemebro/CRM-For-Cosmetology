package com.ua.glebkorobov.checkdb.service;

import com.ua.glebkorobov.checkdb.model.Client;
import com.ua.glebkorobov.checkdb.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {


    @Autowired
    ClientRepository clientRepository;

    public String addClient(Client client) {
        try {
            clientRepository.save(client);
            if (client.getDateOfBirthday() != null){
                return "Client " + client.getName() + " with phone " + client.getPhone() +
                         " and date of birthday " + client.getDateOfBirthday() + " added.";
            }
            return "Client " + client.getName() + " with phone " + client.getPhone() + " added.";
        } catch (DataIntegrityViolationException ex) {
            return "Client with number " + client.getPhone() + " is exists";
        }
    }

    public Client pickClientByNumber(String number){
        return clientRepository.findByNumber(number);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
