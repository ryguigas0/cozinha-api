package com.espm.guilherme.cozinhaapi.cozinha;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.espm.guilherme.cozinhaapi.pedido.PedidoResponseTO;

@RestController
@RequestMapping("/cozinha/pedido")
public class CozinhaResource {

    @Autowired
    CozinhaService service;

    @GetMapping("/listar")
    public ResponseEntity<List<PedidoResponseTO>> listarPedidos(
            @RequestParam(name = "situacao", defaultValue = "-1", required = false) int situacao) {
        return new ResponseEntity<List<PedidoResponseTO>>(service.listarPedidos(situacao), HttpStatus.OK);
    }

    @GetMapping("/preparar")
    public ResponseEntity<List<PedidoResponseTO>> listarPedidosPreparar() {
        return new ResponseEntity<List<PedidoResponseTO>>(service.listarPedidos(1), HttpStatus.OK);
    }

    @PutMapping("/preparar")
    public ResponseEntity<CozinhaResponse> prepararPedido(
            @RequestParam(name = "pedidoId", required = true) int pedidoId) {
        CozinhaResponse response = service.prepararPedido(pedidoId);

        if (response.resultCode() < 0) {
            return new ResponseEntity<CozinhaResponse>(response, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<CozinhaResponse>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/servir")
    public ResponseEntity<CozinhaResponse> servirPedido(
            @RequestParam(name = "pedidoId", required = false) int pedidoId) {

        CozinhaResponse response = service.servirPedido(pedidoId);

        if (response.resultCode() < 0) {
            return new ResponseEntity<CozinhaResponse>(response, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<CozinhaResponse>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/cancelar")
    public ResponseEntity<CozinhaResponse> cancelarPedido(
            @RequestParam(name = "pedidoId", required = false) int pedidoId) {

        CozinhaResponse response = service.cancelarPedido(pedidoId);

        if (response.resultCode() < 0) {
            return new ResponseEntity<CozinhaResponse>(response, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<CozinhaResponse>(response, HttpStatus.BAD_REQUEST);
        }

    }

}
