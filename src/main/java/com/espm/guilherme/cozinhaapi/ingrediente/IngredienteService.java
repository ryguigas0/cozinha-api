package com.espm.guilherme.cozinhaapi.ingrediente;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository repo;

    public IngredienteResponseTO criarIngrediente(IngredienteRequestTO novoIngrediente) {
        IngredienteModel model = repo.save(new IngredienteModel(novoIngrediente));

        return new IngredienteResponseTO(model.getId(), model.getNome(), model.getQuantidade(), model.getUnidade());
    }

    public List<IngredienteResponseTO> listarIngredientes() {

        List<IngredienteResponseTO> output = new ArrayList<>();

        repo.findAll().forEach(ingr -> output
                .add(new IngredienteResponseTO(ingr.getId(), ingr.getNome(), ingr.getQuantidade(), ingr.getUnidade())));

        return output;
    }

    public IngredienteResponseTO buscar(int ingredienteId) {
        return repo.findById(ingredienteId)
                .map(m -> new IngredienteResponseTO(m.getId(), m.getNome(), m.getQuantidade(), m.getUnidade()))
                .orElseThrow();
    }

    public boolean quantidadeSuficiente(int ingredienteId, int quantidadeNecessaria) {
        try {
            IngredienteResponseTO ingrediente = buscar(ingredienteId);

            return ingrediente.quantidade() >= quantidadeNecessaria;
        } catch (Exception e) {
            return false;
        }

    }

    public int reduzirQuantidade(int ingredienteId, int quantidadeReduzir) {
        if (quantidadeReduzir < 0) {
            throw new RuntimeException("NÃO PODE REDUZIR QUANTIDADE DE INGREDIENTE NEGATIVA");
        }

        return repo.findById(ingredienteId)
                .map(ingr -> repo.atualizarQuantidade(ingr.getId(), ingr.getQuantidade() - quantidadeReduzir))
                .stream().reduce(0, (acc, elem) -> acc + elem);

    }
}
