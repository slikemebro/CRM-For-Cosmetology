package com.ua.glebkorobov.checkdb.service;

import com.ua.glebkorobov.checkdb.repository.VisitProceduresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitProceduresService {

    @Autowired
    VisitProceduresRepository visitProceduresRepository;


}
