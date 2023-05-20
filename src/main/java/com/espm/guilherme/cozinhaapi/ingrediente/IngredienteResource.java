package com.espm.guilherme.cozinhaapi.ingrediente;

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
@RequestMapping("/ingrediente")
public class IngredienteResource {

    @Autowired
    public IngredienteService service;

    @PostMapping("/adicionar")
    public ResponseEntity<IngredienteResponseTO> criarIngrediente(@RequestBody IngredienteRequestTO novoIngrediente) {
        service.criarIngrediente(novoIngrediente);

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<IngredienteResponseTO>> listarIngredientes() {
        return new ResponseEntity<List<IngredienteResponseTO>>(service.listarIngredientes(), HttpStatus.OK);
    }
}
