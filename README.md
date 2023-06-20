# Cozinha API - Prova 1 de Programação Orientada a Objetos

## Como instalar e rodar o projeto

1. Clone o projeto
   1. Git + HTTPS: `git clone https://github.com/ryguigas0/cozinha-api.git`
   2. Git + SSH: `git clone git@github.com:ryguigas0/cozinha-api.git`
   3. Github CLI: `gh repo clone ryguigas0/cozinha-api`
2. `sudo mysql -u root -p < src/main/resources/ddl.sql` (cria o banco de dados e tabelas do projeto)
3. `mvn clean install` ou `./mvnw clean install`
4. `java -jar target/cozinha-api-0.0.1-SNAPSHOT.jar`

## Painel de pedidos

1. Use as rotas para criar ingredientes, pratos, clientes e pedidos
2. Acesse `/backoffice/cozinha`

## Rotas

- Adicionar cliente: `POST /cliente/cadastrar`

```json
//Exemplo de request
{
 "nome": "Roberto",
 "sobrenome": "Camarão",
 "telefone": "12 91234567"
}

// Exemplo de response
{
 "id": 1,
 "nome": "Roberto",
 "sobrenome": "Camarão",
 "telefone": "12 91234567"
}
```

- Listar clientes: `GET /cliente/listar`

```json
// Exemplo de response
[
 {
  "id": 1,
  "nome": "Roberto",
  "sobrenome": "Camarão",
  "telefone": "12 91234567"
 }
]
```

- Adicionar ingrediente: `POST /ingrediente/adcionar`

```json
// Exemplo de request
{
 "nome": "batata",
 "quantidade": 30,
 "unidade": "unidade"
}

// Exemplo de response
{
 "id": 2,
 "nome": "batata",
 "quantidade": 30,
 "unidade": "unidade"
}
```

- Listar ingredientes: `GET /ingrediente/listar`

```json
// Exemplo de response
[
 {
  "id": 1,
  "nome": "oleo",
  "quantidade": 0,
  "unidade": "mL"
 },
 {
  "id": 2,
  "nome": "batata",
  "quantidade": 0,
  "unidade": "unidade"
 }
]
```

- Criar item de menu: `POST /menu/criar`

```json
// Exemplo de request
{
 "nome": "Batatas fritas grande",
 "descricao": "Batatas fritas onduladinhas feitas na hora!",
 "preco": 27.95,
 "ingredientes": [
  {
   "id": 1,
   "quantidade": 700
  },
  {
   "id": 2,
   "quantidade": 8
  }
 ]
}

// Exemplo de response
{
 "id": 1,
 "nome": "Batatas fritas grande",
 "descricao": "Batatas fritas onduladinhas feitas na hora!",
 "preco": 27.95
}
```

- Listar itens de menu: `POST /menu/listar`

```json
// Exemplo de response
[
 {
  "id": 1,
  "nome": "Batatas fritas grande",
  "descricao": "Batatas fritas onduladinhas feitas na hora!",
  "preco": 27.95
 }
]
```

- Criar pedido: `POST /pedido/criar`, recebe os IDs dos itens do menu

```json
// Exemplo de request
{
 "clienteId": 1, // ID do cliente que pediu
    "items": [
        {
            "id": 1 // ID do item do menu
        },
        {
            "id": 2 // ID do item do menu
        }
    ]
}

// Exemplo de response
{
 "id": 102,
 "total": 56.0,
 "clienteId": 1,
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
```

## Rotas cozinha

- Listar pedidos para preparar: `GET /pedido/preparar`, lista todos pedidos com `status` igual à `AGUARDANDO` ou `statusNumber` igual à `1` do pedido mais velho ao mais recente

```json
// Exemplo de resposta
[
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
```

- Listar pedidos: `GET /cozinha/pedido/listar`, ordenado do pedido mais velho ao mais recente e aceita o parâmetro `situacao` para filtrar pedidos por situacao, se não for fornecido lista todos os pedidos
  - `?situacao=0` corresponde a `CANCELADO`
  - `?situacao=1` corresponde a `AGUARDANDO`
  - `?situacao=2` corresponde a `PREPARANDO`
  - `?situacao=3` corresponde a `PRONTO`
  - `?situacao=4` corresponde a `SERVIDO`

```json
// Exemplo de resposta
[
 {
  "id": 102,
  "status": "PRONTO",
  "statusNumber": 3,
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
```

- Preparar pedido: `PUT /cozinha/pedido/preparar`, necessita do parâmetro `pedidoId` contendo o ID do pedido que o `status` esteja em `AGUARDANDO` ou `statusNumber` igual a `1`

```json
// Exemplo de request
// /cozinha/pedido/preparar?pedidoId=102

// Exemplo de response de operação com sucesso
{
 "message": "OPERAÇÃO COM SUCESSO",
 "resultCode": 0
}

// Resposta caso não haja ingredientes o suficiente
{
 "message": "QUANTIDADE DE INGREDIENTES INSUFICIENTE",
 "resultCode": -1
}

// Resposta caso não seja um pedido que não existe ou com status diferente do esperado
{
 "message": "PEDIDO INVÁLIDO",
 "resultCode": -2
}
```

- Servir o pedido: `PUT /cozinha/pedido/servir`, necessita do parâmetro `pedidoId` contendo o ID do pedido que o `status` esteja em `SERVIDO` ou `statusNumber` igual a `4`

```json
// Exemplo de request
// /cozinha/pedido/servir?pedidoId=102

// Exemplo de response de operação com sucesso
{
 "message": "OPERAÇÃO COM SUCESSO",
 "resultCode": 0
}

// Resposta caso não haja ingredientes o suficiente
{
 "message": "QUANTIDADE DE INGREDIENTES INSUFICIENTE",
 "resultCode": -1
}

// Resposta caso não seja um pedido que não existe ou com status diferente do esperado
{
 "message": "PEDIDO INVÁLIDO",
 "resultCode": -2
}
```

- Cancelar o pedido: `PUT /cozinha/pedido/cancelar`, necessita do parâmetro `pedidoId` contendo o ID do pedido que o `status` não esteja em `CANCELADO` (`statusNumber` igual a `0`) ou `PRONTO` (`statusNumber` igual a `3`)

```json
// Exemplo de request
// /cozinha/pedido/cancelar?pedidoId=102

// Exemplo de response de operação com sucesso
{
 "message": "OPERAÇÃO COM SUCESSO",
 "resultCode": 0
}

// Resposta caso não haja ingredientes o suficiente
{
 "message": "QUANTIDADE DE INGREDIENTES INSUFICIENTE",
 "resultCode": -1
}

// Resposta caso não seja um pedido que não existe ou com status diferente do esperado
{
 "message": "PEDIDO INVÁLIDO",
 "resultCode": -2
}
```

- Listar pedido para fechar: `GET /caixa/pedidos`

```json
// Exemplo de response
[
 {
  "id": 102,
  "total": 56.0,
  "clienteId": 1,
  "status": "SERVIDO",
  "statusNumber": 4,
  "items": [
   {
    "nome": "Batatas fritas grande",
    "preco": 28.0,
    "ingredientes": [
     {
      "id": 2,
      "nome": "oleo",
      "quantidadeNecessaria": 700
     },
     {
      "id": 1,
      "nome": "batata",
      "quantidadeNecessaria": 8
     }
    ]
   },
   {
    "nome": "Batatas fritas grande",
    "preco": 28.0,
    "ingredientes": [
     {
      "id": 2,
      "nome": "oleo",
      "quantidadeNecessaria": 700
     },
     {
      "id": 1,
      "nome": "batata",
      "quantidadeNecessaria": 8
     }
    ]
   }
  ]
 }
]
```

- Fechar pedido: `PUT /caixa/fechar`, necessita do parâmetro `pedidoId` contendo o ID do pedido que o `status` esteja em `SERVIDO` (`statusNumber` igual a `4`)

```json
// Exemplo de request
// /cozinha/pedido/fechar?pedidoId=102

// Exemplo de response de operação com sucesso
{
 "message": "OPERAÇÃO COM SUCESSO",
 "resultCode": 0
}

// Resposta caso não seja um pedido que não existe ou com status diferente do esperado
{
 "message": "PEDIDO INVÁLIDO",
 "resultCode": -1
}
```

## Coleção de requisições e formato disponível na coleção do [Insomnia](https://insomnia.rest/): `insomnia_collection`
