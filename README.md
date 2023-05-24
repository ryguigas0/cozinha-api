# Cozinha API - Prova 1 de Programação Orientada a Objetos

## Como rodar

1. Clone o projeto
2. `mvn clean install` ou `./mvnw clean install`
3. `java -jar target/cozinha-api-0.0.1-SNAPSHOT.jar`

## Rotas

- Adicionar ingrediente: `GET /ingrediente/adcionar`

```json
{
    "nome": "oleo",
    "quantidade": 5000,
    "unidade": "mL"
}
```

- Listar ingredientes: `GET /ingrediente/listar`
- Criar item de menu: `POST /menu/criar`, recebe os IDs dos ingredientes e a quantidade que usa

```json
{
    "nome": "Mega prato",
    "descricao": "É um super prato mesmo",
    "preco": 22.55,
    "ingredientes": [
        {
            "id": 1,
            "quantidade": 1
        },
        {
            "id": 2,
            "quantidade": 300
        }
    ]
}
```

- Listar itens de menu: `POST /menu/listar`
- Criar pedido: `POST /pedido/criar`, recebe os IDs dos itens do menu

```json
{
    "items": [
        {
            "id": 1
        },
        {
            "id": 2
        }
    ]
}
```

- Listar pedidos: `GET /pedido/listar`
- Atualizar pedido: `PUT /pedido/atualizar?pedidoId=X&situacao=Y`, onde X é o ID do pedido e Y é o número que representa a situação do pedido que segue a representação abaixo
  - `1` corresponde a `AGUARDANDO`
  - `2` corresponde a `PREPARANDO`
  - `3` corresponde a `PRONTO`
  - `4` corresponde a `SERVIDO`

Coleção de requisições e formato disponível na coleção do [Insomnia](https://insomnia.rest/): `insomnia_collection`
