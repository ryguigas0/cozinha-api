package com.espm.guilherme.cozinhaapi.itemMenuIngrediente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu/item/ingrediente")
public class ItemMenuIngredienteResource {

    @Autowired
    ItemMenuIngredienteService service;


    @PostMapping("/criar")
    public ResponseEntity<?> criarItemMenuIngrediente(@RequestBody ItemMenuIngredienteRequestTO novoItemMenuIngrediente) {
        service.criar(novoItemMenuIngrediente);

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
