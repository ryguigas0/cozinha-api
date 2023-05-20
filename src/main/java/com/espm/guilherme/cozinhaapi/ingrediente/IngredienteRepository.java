package com.espm.guilherme.cozinhaapi.ingrediente;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends CrudRepository<IngredienteModel, String> {
    
}
