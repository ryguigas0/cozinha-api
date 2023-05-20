package com.espm.guilherme.cozinhaapi.ingrediente;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteService {
    
    @Autowired
    private IngredienteRepository repo;

    public void criarIngrediente(IngredienteRequestTO novoIngrediente) {
        repo.save(new IngredienteModel(novoIngrediente));
    }

    public List<IngredienteResponseTO> listarIngredientes() {

        List<IngredienteResponseTO> output = new ArrayList<>();

        repo.findAll().forEach(ingr -> output.add(new IngredienteResponseTO(ingr.getId(), ingr.getNome(), ingr.getQuantidade(), ingr.getUnidade())));

        return output;
    }
}
