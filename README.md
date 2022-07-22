# delivery_api
API REST com o framework Spring Boot do Java controle de delivery 

## Requisitos

- Java 8
- Banco de Dados Firebird

## Banco de Dados

- É necessario a instalação do banco de dados Firebird e mudar a url no 
arquivo application.properties no atributo spring.datasource.url e colocar o caminho do banco.
- O backup de banco pode ser encontrado: https://github.com/DarcioF/delivery_api/tree/main/banco

## Routes

A api utiliza a autenticação via token JWT. Porém as routas a seguir não necessita do token
de acesso, Exemplo:

- http://localhost:8080/login (method post)
- http://localhost:8080/api/usuario/salvar (method post)

### Routes com autenticação via token JWT

No  HEADERS da requisição é obrigatório passar key Authorization. Exemplo:
```cmd
Authorization Bearer token(obtido a partir do response da route login)
```
É obrigatório ter um espaço entre "Bearer" e o token.

Route para buscar todos os registros:

- http://localhost:8080/api/cliente (method get) -> Lista todos os clientes.
- http://localhost:8080/api/entrega (method get) -> Lista todas as entregas.
- http://localhost:8080/api/pedido (method get) -> Lista todos os pedidos.

Route para buscar por id:

- http://localhost:8080/api/cliente/1 (method get) -> cliente por id.
- http://localhost:8080/api/entrega/1 (method get) -> entrega por id.
- http://localhost:8080/api/pedido/1 (method get) ->  pedido por id.

Route para salvar ou atualizar registros:

- http://localhost:8080/api/cliente Para salvar (method post) e alterar (method put) -> salvar cliente.
Body:
```json
{
"nome":"joao",
"cpf": "000.000.000-00",
"email":"joao@gmail.com",
"telefone":"(77)99999999"

}
```
- http://localhost:8080/api/entrega Para salvar (method post) e alterar (method put) -> salvar entrega.
Body:
```json
{
  "rua":"AV. Bahia",
  "numero": 34,
  "cep":"4799000",
  "complemento":"casa",
  "taxa_entrega": 20.45,
  "cidade":"Formosa",
  "bairro":"Centro"
}
```
- http://localhost:8080/api/pedido Para salvar (method post) e alterar (method put) ->  pedido por id.
Body:
```json
{
  "descricao":"Cachorro quente",
  "valor_total": 10.78,
  "entregue":false,
  "data_pedido": "2022-05-25",
  "cliente":{
    "id": 1,
    "nome":"joao",
    "cpf": "000.000.000-00",
    "email":"joao@gmail.com",
    "telefone":"(77)99999999"

  },
  "entrega": {
    "id": 1,
    "rua":"AV. Bahia",
    "numero": 34,
    "cep":"4799000",
    "complemento":"casa",
    "taxa_entrega": 20.45,
    "cidade":"Formosa",
    "bairro":"Centro"
  }
}
```
Route para deletar registros:

- http://localhost:8080/api/cliente/1 (method delete)  ->  deletar cliente.
- http://localhost:8080/api/entrega/1 (method delete)  ->  deletar entrega.
- http://localhost:8080/api/pedido/1 (method delete)  ->  deletar pedido.

### Swagger

Todas as routes e documentação poderá ser encontradas na seguinte pagina:

- http://localhost:8080/swagger.html (method get)  ->  documentação da api.

### Atenção

Se você utiliza o postman irá encontrar um backup de todos os request: 

- https://github.com/DarcioF/delivery_api/tree/main/postman
