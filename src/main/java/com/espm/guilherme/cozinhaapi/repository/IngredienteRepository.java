package com.espm.guilherme.cozinhaapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.espm.guilherme.cozinhaapi.model.IngredienteModel;

@Repository
public interface IngredienteRepository extends CrudRepository<IngredienteModel, String> {
    
}
