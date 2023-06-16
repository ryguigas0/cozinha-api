console.log("imported cozinha.js")

const mockPedidos = [
    {
        "id": 102,
        "status": "AGUARDANDO",
        "statusNumber": 1,
        "items": [
            {
                "nome": "Batatas fritas grande",
                "ingredientes": [
                    {
                        "id": 1,
                        "nome": "oleo",
                        "quantidadeNecessaria": 700
                    },
                    {
                        "id": 2,
                        "nome": "batata",
                        "quantidadeNecessaria": 8
                    }
                ]
            },
            {
                "nome": "Batatas fritas grande",
                "ingredientes": [
                    {
                        "id": 1,
                        "nome": "oleo",
                        "quantidadeNecessaria": 700
                    },
                    {
                        "id": 2,
                        "nome": "batata",
                        "quantidadeNecessaria": 8
                    }
                ]
            }
        ]
    },
    {
        "id": 102,
        "status": "AGUARDANDO",
        "statusNumber": 1,
        "items": [
            {
                "nome": "Batatas fritas grande",
                "ingredientes": [
                    {
                        "id": 1,
                        "nome": "oleo",
                        "quantidadeNecessaria": 700
                    },
                    {
                        "id": 2,
                        "nome": "batata",
                        "quantidadeNecessaria": 8
                    }
                ]
            },
            {
                "nome": "Batatas fritas grande",
                "ingredientes": [
                    {
                        "id": 1,
                        "nome": "oleo",
                        "quantidadeNecessaria": 700
                    },
                    {
                        "id": 2,
                        "nome": "batata",
                        "quantidadeNecessaria": 8
                    }
                ]
            }
        ]
    },
    {
        "id": 102,
        "status": "AGUARDANDO",
        "statusNumber": 1,
        "items": [
            {
                "nome": "Batatas fritas grande",
                "ingredientes": [
                    {
                        "id": 1,
                        "nome": "oleo",
                        "quantidadeNecessaria": 700
                    },
                    {
                        "id": 2,
                        "nome": "batata",
                        "quantidadeNecessaria": 8
                    }
                ]
            },
            {
                "nome": "Batatas fritas grande",
                "ingredientes": [
                    {
                        "id": 1,
                        "nome": "oleo",
                        "quantidadeNecessaria": 700
                    },
                    {
                        "id": 2,
                        "nome": "batata",
                        "quantidadeNecessaria": 8
                    }
                ]
            }
        ]
    },
    {
        "id": 102,
        "status": "AGUARDANDO",
        "statusNumber": 1,
        "items": [
            {
                "nome": "Batatas fritas grande",
                "ingredientes": [
                    {
                        "id": 1,
                        "nome": "oleo",
                        "quantidadeNecessaria": 700
                    },
                    {
                        "id": 2,
                        "nome": "batata",
                        "quantidadeNecessaria": 8
                    }
                ]
            },
            {
                "nome": "Batatas fritas grande",
                "ingredientes": [
                    {
                        "id": 1,
                        "nome": "oleo",
                        "quantidadeNecessaria": 700
                    },
                    {
                        "id": 2,
                        "nome": "batata",
                        "quantidadeNecessaria": 8
                    }
                ]
            }
        ]
    },
    {
        "id": 102,
        "status": "AGUARDANDO",
        "statusNumber": 1,
        "items": [
            {
                "nome": "Batatas fritas grande",
                "ingredientes": [
                    {
                        "id": 1,
                        "nome": "oleo",
                        "quantidadeNecessaria": 700
                    },
                    {
                        "id": 2,
                        "nome": "batata",
                        "quantidadeNecessaria": 8
                    }
                ]
            },
            {
                "nome": "Batatas fritas grande",
                "ingredientes": [
                    {
                        "id": 1,
                        "nome": "oleo",
                        "quantidadeNecessaria": 700
                    },
                    {
                        "id": 2,
                        "nome": "batata",
                        "quantidadeNecessaria": 8
                    }
                ]
            }
        ]
    }
]

const pedidosContainer = document.querySelector("#pedidos")

function ingrediente2HTML(ingrediente) {
    return `<div>${ingrediente.nome} / ${ingrediente.quantidadeNecessaria}</div>`
}

function ingredientes2HTML(ingredientes) {
    let content = ""

    for (let i = 0; i < ingredientes.length; i++) {
        content += ingrediente2HTML(ingredientes[i])
    }

    return content
}

function item2HTML(item) {
    return `
    <div>
        <div>${item.nome}</div>
        <div>${ingredientes2HTML(item.ingredientes)}</div>
    </div>
    `
}

function items2HTML(items) {
    let content = ""

    for (let i = 0; i < items.length; i++) {
        content += item2HTML(items[i])
    }

    return content
}

function pedido2HTML(pedido) {
    return `
        <div class="col-6 text-center">
            <div>
                ${pedido.id} / ${pedido.status} / ${pedido.status}
            </div>
            <div>${items2HTML(pedido.items)}</div>
        </div>
    `
}

function pedidos2HTML(linhaPedidos) {
    let content = ""

    for (let i = 0; i < linhaPedidos.length; i++) {
        content += pedido2HTML(linhaPedidos[i])
    }

    return `<div class="row justify-content-center">
        ${content}
    </div>`
}

function listarPedios() {
    pedidosContainer.innerHTML = ""

    for (let i = 2; i < mockPedidos.length; i += 3) {
        pedidosContainer.innerHTML += pedidos2HTML([mockPedidos[i - 2], mockPedidos[i - 1], mockPedidos[i]]);
    }
}

listarPedios()

console.log({ mockPedidos })