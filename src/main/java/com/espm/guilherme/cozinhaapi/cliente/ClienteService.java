package com.espm.guilherme.cozinhaapi.cliente;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository repo;

    public ClienteResponseTO cadastrarCliente(ClienteRequestTO cliente) {
        ClienteModel model = new ClienteModel(cliente);

        model = repo.save(model);

        return new ClienteResponseTO(model.getId(), model.getNome(), model.getSobrenome(), model.getTelefone());
    }

    public List<ClienteResponseTO> listarClientes() {
        List<ClienteResponseTO> output = new ArrayList<>();

        repo.findAll().forEach(
                m -> output.add(new ClienteResponseTO(m.getId(), m.getNome(), m.getSobrenome(), m.getTelefone())));

        return output;
    }

}
