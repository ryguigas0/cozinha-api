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
        PedidoModel pedido = repo.save(new PedidoModel(novoPedido));

        List<ItemMenuReferenciaResponseTO> itensPedido = new ArrayList<>();

        double total = 0.0;

        for (ItemMenuReferenciaRequestTO itemMenuReferencia : novoPedido.items()) {
            // adicionar o valor total

            // Associar o pedido ao item de menu
            itemPedidoService.criar(new ItemMenuPedidoRequestTO(pedido.getId(), itemMenuReferencia.id()));

            // Pega os ingredientes dos itens de menu do pedido e coloca o nome e a
            // quantidade necessária
            List<ItemMenuIngredienteReferenciaResponseTO> ingredientes = itemMenuIngredienteService
                    .listarIngredientesPorItemMenuID(itemMenuReferencia.id())
                    .stream()
                    .map(ingr -> new ItemMenuIngredienteReferenciaResponseTO(ingr.id(), ingr.nome(),
                            ingr.quantidadeNecessaria()))
                    .toList();

            // Pega o item de menu do pedido
            ItemMenuResponseTO itemMenu = itemMenuService.buscar(itemMenuReferencia.id());

            total += itemMenu.preco();

            itensPedido.add(new ItemMenuReferenciaResponseTO(itemMenu.nome(), itemMenu.preco(), ingredientes));
        }

        return new PedidoResponseTO(pedido.getId(), total, pedido.getClienteId(), pedido.getStatus(),
                itensPedido);
    }

    public List<PedidoResponseTO> listarPedidos(int situacao) {
        List<PedidoResponseTO> output = new ArrayList<>();

        repo.listarPorStatus(situacao)
                .forEach(p -> output
                        .add(new PedidoResponseTO(p.getId(), p.getClienteId(), p.getStatus(),
                                getItensMenuReferenciaResponseTOs(p))));

        return output;
    }

    public List<PedidoResponseTO> listarPedidos() {
        List<PedidoResponseTO> output = new ArrayList<>();

        repo.findAll()
                .forEach(p -> output
                        .add(new PedidoResponseTO(p.getId(), p.getClienteId(), p.getStatus(),
                                getItensMenuReferenciaResponseTOs(p))));

        return output;
    }

    public PedidoResponseTO atualizarPedido(int pedidoId, int situacaoNova) {
        repo.atualizarPedido(pedidoId, situacaoNova);

        PedidoModel pedido = repo.findById(pedidoId).get();

        return new PedidoResponseTO(pedido.getId(), pedido.getClienteId(), pedido.getStatus(),
                getItensMenuReferenciaResponseTOs(pedido));
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
                            ingredienteReferenciaResponseTO.id(),
                            ingredienteReferenciaResponseTO.nome(),
                            ingredienteReferenciaResponseTO.quantidadeNecessaria()))
                    .toList();

            ItemMenuResponseTO itemMenu = itemMenuService.buscar(itemMenuPedidoResponseTO.itemMenuId());

            output.add(new ItemMenuReferenciaResponseTO(itemMenu.nome(), itemMenu.preco(), ingredientes));
        }

        return output;
    }

    public PedidoResponseTO buscarPorPedidoId(int pedidoId) {
        return repo.findById(pedidoId)
                .map(pedido -> new PedidoResponseTO(pedido.getId(), pedido.getClienteId(), pedido.getStatus(),
                        getItensMenuReferenciaResponseTOs(pedido)))
                .orElse(new PedidoResponseTO(0, 0, 0, null));
    }

}
