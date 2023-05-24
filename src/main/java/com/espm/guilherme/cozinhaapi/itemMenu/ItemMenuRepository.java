package com.espm.guilherme.cozinhaapi.itemMenu;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMenuRepository extends CrudRepository<ItemMenuModel, Integer> {

    @Query(value = "SELECT distinct im.* FROM item_menu im INNER JOIN item_menu_ingrediente imi ON imi.item_menu_id = im.id INNER JOIN ingrediente i ON imi.ingrediente_id = i.id WHERE imi.quantidade_ingrediente <= i.quantidade;", nativeQuery = true)
    List<ItemMenuModel> encontrarTodosDisponiveis();

}
