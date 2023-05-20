package com.espm.guilherme.cozinhaapi.itemMenu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemMenuService {

    @Autowired
    ItemMenuRepository repo;

    public ItemMenuResponseTO criar(ItemMenuRequestTO novoItemMenu) {

        ItemMenuModel modelo = repo.save(new ItemMenuModel(novoItemMenu));

        return new ItemMenuResponseTO(modelo.getId(), modelo.getNome(), modelo.getDescricao(), modelo.getPreco());
    }

    public List<ItemMenuResponseTO> listar() {
        List<ItemMenuResponseTO> output = new ArrayList<>();

        repo.encontrarTodosDisponiveis().forEach(i -> output.add(new ItemMenuResponseTO(i.getId(), i.getNome(), i.getDescricao(), i.getPreco())));

        return output;
    }

}
