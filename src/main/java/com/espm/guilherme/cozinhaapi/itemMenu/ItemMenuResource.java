package com.espm.guilherme.cozinhaapi.itemMenu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class ItemMenuResource {

    @Autowired
    ItemMenuService service;

    @PostMapping("/criar")
    public ResponseEntity<ItemMenuResponseTO> criarItemMenu(@RequestBody ItemMenuRequestTO novoItemMenu){
        return new ResponseEntity<ItemMenuResponseTO>(service.criar(novoItemMenu), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ItemMenuResponseTO>> listarItensMenu(){
        return new ResponseEntity<List<ItemMenuResponseTO>>(service.listar(), HttpStatus.OK);
    }
}
