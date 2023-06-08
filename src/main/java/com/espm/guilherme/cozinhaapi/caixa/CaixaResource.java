package com.espm.guilherme.cozinhaapi.caixa;

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
@RequestMapping("/caixa")
public class CaixaResource {

    @Autowired
    CaixaService service;

    @GetMapping("/pedidos")
    public ResponseEntity<?> listarPedidosFechar() {
        List<PedidoResponseTO> pedidos = service.listarPedidosFechar();

        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @PutMapping("/fechar")
    public ResponseEntity<?> fecharPedido(@RequestParam(name = "pedidoId") int pedidoId) {
        CaixaResponse response = service.fecharPedido(pedidoId);

        if (response.resultCode() < 0) {
            return new ResponseEntity<CaixaResponse>(response, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<CaixaResponse>(response, HttpStatus.BAD_REQUEST);
        }

    }

}
