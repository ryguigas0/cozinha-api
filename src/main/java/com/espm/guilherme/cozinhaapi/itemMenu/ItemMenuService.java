package com.espm.guilherme.cozinhaapi.itemMenu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espm.guilherme.cozinhaapi.itemMenuIngrediente.ItemMenuIngredienteRequestTO;
import com.espm.guilherme.cozinhaapi.itemMenuIngrediente.ItemMenuIngredienteService;

@Service
public class ItemMenuService {

    @Autowired
    ItemMenuRepository repo;

    @Autowired
    ItemMenuIngredienteService itemMenuIngredienteService;

    public ItemMenuResponseTO criar(ItemMenuRequestTO novoItemMenu) {

        ItemMenuModel modelo = repo.save(new ItemMenuModel(novoItemMenu));

        for (IngredienteReferenciaRequestTO ingredienteReferencia : novoItemMenu.ingredientes()) {
            itemMenuIngredienteService.criar(new ItemMenuIngredienteRequestTO(ingredienteReferencia.id(),
                    ingredienteReferencia.quantidade(), modelo.getId()));
        }

        return new ItemMenuResponseTO(modelo.getId(), modelo.getNome(), modelo.getDescricao(), modelo.getPreco());
    }

    public List<ItemMenuResponseTO> listar() {
        List<ItemMenuResponseTO> output = new ArrayList<>();

        repo.encontrarTodosDisponiveis().forEach(
                i -> output.add(new ItemMenuResponseTO(i.getId(), i.getNome(), i.getDescricao(), i.getPreco())));

        return output;
    }

    public ItemMenuResponseTO buscar(int id) {
        return repo.findById(id)
                .map(m -> new ItemMenuResponseTO(m.getId(), m.getNome(), m.getDescricao(), m.getPreco()))
                .orElseThrow();
    }

}
