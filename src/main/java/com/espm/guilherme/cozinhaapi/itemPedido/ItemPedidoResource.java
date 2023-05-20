package com.espm.guilherme.cozinhaapi.itemPedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido/item")
public class ItemPedidoResource {
    
    @Autowired
    ItemPedidoService service;

    @PostMapping("criar")
    public ResponseEntity<ItemPedidoResponseTO> criarItemPedido(@RequestBody ItemPedidoRequestTO newItemPedido){
        return new ResponseEntity<ItemPedidoResponseTO>(service.criar(newItemPedido), HttpStatus.CREATED);
    }
}
