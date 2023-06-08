package com.espm.guilherme.cozinhaapi.cliente;

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
@RequestMapping("/cliente")
public class ClienteResource {

    @Autowired
    ClienteService clienteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarCliente(@RequestBody ClienteRequestTO cliente) {
        ClienteResponseTO novoCliente = clienteService.cadastrarCliente(cliente);

        return new ResponseEntity<ClienteResponseTO>(novoCliente, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ClienteResponseTO>> listarClientes() {
        List<ClienteResponseTO> listaClientes = clienteService.listarClientes();

        return new ResponseEntity<>(listaClientes, HttpStatus.OK);
    }
}
