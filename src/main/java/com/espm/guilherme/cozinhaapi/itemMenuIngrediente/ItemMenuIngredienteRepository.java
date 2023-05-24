package com.espm.guilherme.cozinhaapi.itemMenuIngrediente;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMenuIngredienteRepository extends CrudRepository<ItemMenuIngredienteModel, Integer> {

    @Query(value = "select imi.* from item_menu_ingrediente imi where imi.item_menu_id = :id", nativeQuery = true)
    List<ItemMenuIngredienteModel> listarPorItemMenuID(@Param("id") int itemMenuID);
}
