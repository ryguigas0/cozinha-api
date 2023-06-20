console.log("imported cozinha.js")

const pedidosContainer = document.querySelector("#pedidos")

// Templating

function ingrediente2HTML(ingrediente) {
    return `
    <div class="pt-2">
        <div>Nome: ${ingrediente.nome}</div>
        <div>Quantidade: ${ingrediente.quantidadeNecessaria}</div>
    </div>`
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
    <div class="mx-2">
        <h5>${item.nome}</h5>
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
    let prioridade = ""

    switch (pedido.statusNumber) {
        case 0:
            prioridade = "dark"
            break;
        case 1:
            prioridade = "danger"
            break;
        case 2:
            prioridade = "warning"
            break;
        case 3:
            prioridade = "info"
            break;
        case 4:
            prioridade = "light"
            break;
        case 5:
            prioridade = "dark"
            break;

        default:
            prioridade = "dark"
            break;
    }

    let actions = Number.parseInt(pedido.statusNumber) >= 4 ? "" : `<button class="btn btn-primary" data-pedido-id="${pedido.id}" data-pedido-status-number="${pedido.statusNumber}">Avançar</button>
    <button class="btn btn-danger" data-pedido-id="${pedido.id}" data-pedido-status-number="${pedido.statusNumber}">Cancelar</button>`

    return `
        <div class="card border-dark">
            <div class="card-body">
                <h4 class="card-title">Pedido ${pedido.id}</h4>
                <h5 class="card-substitle alert-${prioridade}">
                    Situação: ${pedido.status}
                </h5>
                <div class="card-text">
                    <h5> Itens do pedido </h5>
                    ${items2HTML(pedido.items)}
                </div>
                ${actions}
            </div>
        </div>
    `
}

function listarPedios(pedidos) {
    let content = ""

    for (let i = 0; i < pedidos.length; i++) {
        if (Number.parseInt(pedidos[i].statusNumber) != 0 && Number.parseInt(pedidos[i].statusNumber) != 5) {
            content += pedido2HTML(pedidos[i])
        }
    }

    pedidosContainer.innerHTML = `
        <div class="row justify-content-start w-75">
            ${content}
        </div>`
}

// API

async function fetchPedidos() {
    try {
        const response = await fetch(`/cozinha/pedido/listar`, {
            method: "get",
            headers: {
                "Content-Type": "application/json"
            }
        });

        const listaPedidos = await response.json();

        console.log("Sucesso:", listaPedidos);

        return listaPedidos.sort(function(a, b) {return a.statusNumber - b.statusNumber})

    } catch (error) {
        console.error("List error:", error);
    }
}

async function renderPedidos() {
    let pedidos = await fetchPedidos()

    listarPedios(pedidos)
}

await renderPedidos()

export async function prepararPedido(pedidoId) {
    console.log("preparar Pedido")

    try {
        const response = await fetch(`/cozinha/pedido/preparar?pedidoId=${pedidoId}`, {
            method: "put",
            headers: {
                "Content-Type": "application/json",
            }
        });

        if (response.status == 202) {
            location.reload()
        } else {
            let body = await response.json()

            console.error(response.status, body)

            alert(body.message)
        }
    } catch (error) {
        console.log("Preparar error: ", error)
    }
}

export async function servirPedido(pedidoId) {
    console.log("servir Pedido")

    try {
        const response = await fetch(`/cozinha/pedido/servir?pedidoId=${pedidoId}`, {
            method: "put",
            headers: {
                "Content-Type": "application/json",
            }
        });

        if (response.status == 202) {
            location.reload()
        } else {
            console.error(response.status, await response.json())
        }
    } catch (error) {
        let body = await response.json()

        console.error(response.status, body)

        alert(body.message)
    }
}

export async function cancelarPedido(pedidoId) {
    console.log("cancelar Pedido")

    try {
        const response = await fetch(`/cozinha/pedido/cancelar?pedidoId=${pedidoId}`, {
            method: "put",
            headers: {
                "Content-Type": "application/json",
            }
        });

        if (response.status == 202) {
            location.reload()
        } else {
            console.error(response.status, await response.json())
        }
    } catch (error) {
        let body = await response.json()

        console.error(response.status, body)

        alert(body.message)
    }
}