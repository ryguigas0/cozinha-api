package com.espm.guilherme.cozinhaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espm.guilherme.cozinhaapi.repository.IngredienteRepository;

@Service
public class IngredienteService {
    
    @Autowired
    private IngredienteRepository repo;
}
