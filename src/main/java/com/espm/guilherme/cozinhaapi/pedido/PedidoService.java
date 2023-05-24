package com.espm.guilherme.cozinhaapi.pedido;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espm.guilherme.cozinhaapi.itemMenu.ItemMenuResponseTO;
import com.espm.guilherme.cozinhaapi.itemMenu.ItemMenuService;
import com.espm.guilherme.cozinhaapi.itemMenuIngrediente.ItemMenuIngredienteService;
import com.espm.guilherme.cozinhaapi.itemMenuPedido.ItemMenuPedidoRequestTO;
import com.espm.guilherme.cozinhaapi.itemMenuPedido.ItemMenuPedidoResponseTO;
import com.espm.guilherme.cozinhaapi.itemMenuPedido.ItemMenuPedidoService;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository repo;

    @Autowired
    ItemMenuPedidoService itemPedidoService;

    @Autowired
    ItemMenuIngredienteService itemMenuIngredienteService;

    @Autowired
    ItemMenuService itemMenuService;

    public PedidoResponseTO criarPedido(PedidoRequestTO novoPedido) {
        PedidoModel pedido = repo.save(new PedidoModel());

        List<ItemMenuReferenciaResponseTO> itensPedido = new ArrayList<>();

        for (ItemMenuReferenciaRequestTO itemMenuReferencia : novoPedido.items()) {
            // Associar o pedido ao item de menu
            itemPedidoService.criar(new ItemMenuPedidoRequestTO(pedido.getId(), itemMenuReferencia.id()));

            // Pega os ingredientes dos itens de menu do pedido e coloca o nome e a
            // quantidade necessárai
            List<ItemMenuIngredienteReferenciaResponseTO> ingredientes = itemMenuIngredienteService
                    .listarIngredientesPorItemMenuID(itemMenuReferencia.id())
                    .stream()
                    .map(ingr -> new ItemMenuIngredienteReferenciaResponseTO(ingr.nome(), ingr.quantidadeNecessaria()))
                    .toList();

            // Pega o item de menu do pedido
            ItemMenuResponseTO itemMenu = itemMenuService.buscar(itemMenuReferencia.id());

            itensPedido.add(new ItemMenuReferenciaResponseTO(itemMenu.nome(), ingredientes));
        }

        return new PedidoResponseTO(pedido.getId(), pedido.getStatus(), itensPedido);
    }

    public List<PedidoResponseTO> listarPedidos(int situacao) {
        List<PedidoResponseTO> output = new ArrayList<>();

        if (situacao == 0) {
            repo.findAll()
                    .forEach(p -> output
                            .add(new PedidoResponseTO(p.getId(), p.getStatus(), getItensMenuReferenciaResponseTOs(p))));
        } else {
            repo.listarPorStatus(situacao)
                    .forEach(p -> output
                            .add(new PedidoResponseTO(p.getId(), p.getStatus(), getItensMenuReferenciaResponseTOs(p))));
        }

        return output;
    }

    public PedidoResponseTO atualizarPedido(int pedidoId, int situacaoNova) {
        int i = repo.atualizarPedido(pedidoId, situacaoNova);

        System.out.println("Update result " + i);

        PedidoModel pedido = repo.findById(pedidoId).get();

        return new PedidoResponseTO(pedido.getId(), pedido.getStatus(), getItensMenuReferenciaResponseTOs(pedido));
    }

    private List<ItemMenuReferenciaResponseTO> getItensMenuReferenciaResponseTOs(PedidoModel pedido) {
        List<ItemMenuReferenciaResponseTO> output = new ArrayList<>();

        List<ItemMenuPedidoResponseTO> itensMenuPedido = itemPedidoService.listarItemMenuPorPedidoID(pedido.getId());

        for (ItemMenuPedidoResponseTO itemMenuPedidoResponseTO : itensMenuPedido) {

            System.out.println(itemMenuPedidoResponseTO);

            List<ItemMenuIngredienteReferenciaResponseTO> ingredientes = itemMenuIngredienteService
                    .listarIngredientesPorItemMenuID(itemMenuPedidoResponseTO.itemMenuId())
                    .stream()
                    .map(ingredienteReferenciaResponseTO -> new ItemMenuIngredienteReferenciaResponseTO(
                            ingredienteReferenciaResponseTO.nome(),
                            ingredienteReferenciaResponseTO.quantidadeNecessaria()))
                    .toList();

            String nomeItemMenu = itemMenuService.buscar(itemMenuPedidoResponseTO.itemMenuId()).nome();

            output.add(new ItemMenuReferenciaResponseTO(nomeItemMenu, ingredientes));
        }

        return output;
    }

}
