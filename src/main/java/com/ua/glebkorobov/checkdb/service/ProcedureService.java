package com.ua.glebkorobov.checkdb.service;

import com.ua.glebkorobov.checkdb.model.Procedures;
import com.ua.glebkorobov.checkdb.repository.ProceduresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ProcedureService {

    @Autowired
    private ProceduresRepository proceduresRepository;

    public String addProcedure(Procedures procedures){
        try {
            proceduresRepository.save(procedures);
            return "Procedure " + procedures.getName() + " with price " + procedures.getPrice() + " added.";
        } catch (DataIntegrityViolationException ex) {
            return "Procedure with name " + procedures.getName() + " is exists";
        }
    }
}
