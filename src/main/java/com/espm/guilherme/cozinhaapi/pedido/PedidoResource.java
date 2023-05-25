package com.espm.guilherme.cozinhaapi.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoResource {

    @Autowired
    PedidoService service;

    @PostMapping("/criar")
    public ResponseEntity<PedidoResponseTO> criarPedido(@RequestBody PedidoRequestTO novoPedido) {
        return new ResponseEntity<PedidoResponseTO>(service.criarPedido(novoPedido), HttpStatus.CREATED);
    }

    // @GetMapping("/listar")
    // public ResponseEntity<List<PedidoResponseTO>> listarPedidos(
    //         @RequestParam(name = "situacao", required = false, defaultValue = "0") int situacao) {
    //     return new ResponseEntity<List<PedidoResponseTO>>(service.listarPedidos(situacao), HttpStatus.OK);
    // }

    // @PutMapping("/atualizar")
    // public ResponseEntity<PedidoResponseTO> atualizarPedido(
    //         @RequestParam(name = "situacao", required = true) int situacaoNova,
    //         @RequestParam(name = "pedidoId", required = true) int pedidoId) {
    //     return new ResponseEntity<PedidoResponseTO>(service.atualizarPedido(pedidoId, situacaoNova), HttpStatus.OK);
    // }

}
