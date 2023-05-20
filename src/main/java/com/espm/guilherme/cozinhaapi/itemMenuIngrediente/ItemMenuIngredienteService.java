package com.espm.guilherme.cozinhaapi.itemMenuIngrediente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemMenuIngredienteService {

    @Autowired
    ItemMenuIngredienteRepository repo;

    public void criar(ItemMenuIngredienteRequestTO novoItemMenuIngrediente) {
        repo.save(new ItemMenuIngredienteModel(novoItemMenuIngrediente));
    }

}
