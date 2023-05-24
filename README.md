# Cozinha API - Prova 1 de Programação Orientada a Objetos

## Como rodar

1. Clone o projeto
2. `mvn clean install` ou `./mvnw clean install`
3. `java -jar target/cozinha-api-0.0.1-SNAPSHOT.jar`

## Rotas

- Adicionar ingrediente: `GET /ingrediente/adcionar`
- Listar ingredientes: `GET /ingrediente/listar`
- Criar item de menu: `POST /menu/criar`
- Listar itens de menu: `POST /menu/listar`
- Criar pedido: `POST /pedido/criar`
- Listar pedidos: `GET /pedido/listar`
- Atualizar pedido: `PUT /pedido/atualizar`

Coleção de requisições e formato disponível na coleção do [Insomnia](https://insomnia.rest/): `insomnia_collection`
