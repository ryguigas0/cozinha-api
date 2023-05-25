package com.espm.guilherme.cozinhaapi.ingrediente;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface IngredienteRepository extends CrudRepository<IngredienteModel, Integer> {

    @Modifying
    @Query(value = "update ingrediente set quantidade = :quantidade where id = :id", nativeQuery = true)
    int atualizarQuantidade(@Param("id") int id, @Param("quantidade") int quantidadeNova);
    
}
