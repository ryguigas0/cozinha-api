package com.espm.guilherme.cozinhaapi.itemMenuIngrediente;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espm.guilherme.cozinhaapi.ingrediente.IngredienteResponseTO;
import com.espm.guilherme.cozinhaapi.ingrediente.IngredienteService;

@Service
public class ItemMenuIngredienteService {

    @Autowired
    ItemMenuIngredienteRepository repo;

    @Autowired
    IngredienteService ingredienteService;

    public void criar(ItemMenuIngredienteRequestTO novoItemMenuIngrediente) {
        repo.save(new ItemMenuIngredienteModel(novoItemMenuIngrediente));
    }

    public List<IngredienteReferenciaResponseTO> listarIngredientesPorItemMenuID(int itemMenuId) {
        List<ItemMenuIngredienteModel> listItemMenuIngrediente = repo.listarPorItemMenuID(itemMenuId);

        List<IngredienteReferenciaResponseTO> ingredientesResponseTO = new ArrayList<>();

        for (ItemMenuIngredienteModel itemMenuIngrediente : listItemMenuIngrediente) {
            IngredienteResponseTO ingrediente = ingredienteService.buscar(itemMenuIngrediente.getIngredienteId());

            ingredientesResponseTO.add(new IngredienteReferenciaResponseTO(ingrediente.id(), ingrediente.nome(),
                    itemMenuIngrediente.getQuantidadeIngrediente()));
        }

        return ingredientesResponseTO;
    }

}
