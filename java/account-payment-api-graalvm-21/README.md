# account-payment-api
Aplicação responsável pelo controle de contas a pagar

## Tecnologias Usadas
- Java 21
- Spring Boot 3.3.0
- Spring Security 6
- springdoc-openapi-starter-webmvc-ui
- Spring Data
- Flyway (migração de banco de dados)
- PostgreSQL
- MapStruct
- Docker (Dockerfile e docker-compose.yml)

## Como Subir a API para Testes

1. **Compilar o Projeto**

    Pela IDE:
    ```sh
    clean:clean package
    ```

    Pelo terminal dentro do diretório `app`, execute o comando:
    ```sh
    $ mvn package
    ```

2. **Build da Imagem Docker**

    Dentro do diretório `environment`, siga os passos abaixo:

    Caso esteja usando Linux, execute o comando:
    ```sh
    $ sh build_docker.sh
    ```

    Caso esteja no Windows:
    1. Acesse o caminho `app/target`, copie o JAR com o nome `account-payment-api-0.0.1-SNAPSHOT.jar` e cole dentro do diretório `environment` com o nome `account-payment-api.jar`.
    2. Em seguida, execute o comando:
    ```sh
    $ docker build -t account-payment-api .
    ```

3. **Subir os Contêineres**

    Ainda dentro do diretório `environment`, execute o comando:
    ```sh
    $ docker-compose up
    ```

    Para confirmação, use o comando abaixo para validar se houve sucesso na subida dos contêineres:
    ```sh
    $ docker ps
    ```

## Endpoints

### Autenticação
Configure uma requisição POST no seu app de preferência (Postman, Insomnia, etc.) com a URL abaixo:

http://localhost:8080/v1/login


Body:
```json
{
    "username": "validacao",
    "password": "validar@123"
}
```

Nota: Este usuário é criado pelo Flyway como usuário padrão para testes.

Recebendo o token devolvido pela requisição acima, poderá ser usado os endpoints abaixo passando o header:

Headers
Authorization: Bearer {token}

### Criar Conta a Pagar (POST)
http://localhost:8080/v1/account-payment

Body: 
```json
{
    "expirationDate": "2024-10-29",
    "paymentDate": "2024-12-30",
    "paymentAmount": 30,
    "description": "teste do testes",
    "situation": "AWAITING_RECEIPT"
}
```

### Atualizar Conta a Pagar (PUT)
http://localhost:8080/v1/account-payment

Body:
```json
{
    "id": 1,
    "expirationDate": "2024-12-31",
    "paymentDate": "2024-12-30",
    "paymentAmount": 20.50,
    "description": "descrição do contas 1",
    "situation": "AWAITING_PAYMENT"
}
```

### Atualizar Parcialmente Conta a Pagar (PATCH)
http://localhost:8080/v1/account-payment

body:
```json
{
    "id": 1,
    "expirationDate": "2024-12-31",
    "paymentDate": "2024-12-30",
    "paymentAmount": 20.50,
    "description": "descrição do contas 1",
    "situation": "PAYMENT_MADE"
}
```

### Buscar Conta por ID (GET)
http://localhost:8080/v1/account-payment/4

### Buscar Todas as Contas com Filtros (GET)
http://localhost:8080/v1/account-payment/all?page=0&size=20&expirationDateStart=2024-04-30&description=teste

### Deletar Conta por ID (DELETE)
http://localhost:8080/v1/account-payment/3

### Importar Contas via CSV (POST)
http://localhost:8080/v1/account-payment/import-csv

Body exemplo (use application/text):

ZXhwaXJhdGlvbkRhdGU7cGF5bWVudERhdGU7cGF5bWVudEFtb3VudDtkZXNjcmlwdGlvbjtzaXR1YXRpb24KMjAyNC0xMi0zMTsyMDI0LTEyLTMwOzEwLjUwO2Rlc2NyacOnw6NvIGRvIGNvbnRhcyAxO0FXQUlUSU5HX1BBWU1FTlQKMjAyNS0wMS0wMTsyMDI0LTEyLTMxOzEwLjUxO2Rlc2NyacOnw6NvIGRvIGNvbnRhcyAyO1BBWU1FTlRfTUFERQoyMDI1LTAxLTAyOzIwMjUtMDEtMDE7MTAuNTI7ZGVzY3Jpw6fDo28gZG8gY29udGFzIDM7QVdBSVRJTkdfUkVDRUlQVAoyMDI1LTAxLTAzOzIwMjUtMDEtMDI7MTAuNTM7ZGVzY3Jpw6fDo28gZG8gY29udGFzIDQ7QVdBSVRJTkdfUkVDRUlQVAoyMDI1LTAxLTA0OzIwMjUtMDEtMDM7MTAuNTQ7ZGVzY3Jpw6fDo28gZG8gY29udGFzIDU7QVdBSVRJTkdfUEFZTUVOVAoyMDI1LTAxLTA1OzIwMjUtMDEtMDQ7MTAuNTU7ZGVzY3Jpw6fDo28gZG8gY29udGFzIDY7UEFZTUVOVF9NQURFCjIwMjUtMDEtMDY7MjAyNS0wMS0wNTsxMC41NjtkZXNjcmnDp8OjbyBkbyBjb250YXMgNztBV0FJVElOR19SRUNFSVBUCjIwMjUtMDEtMDc7MjAyNS0wMS0wNjsxMC41NztkZXNjcmnDp8OjbyBkbyBjb250YXMgODtBV0FJVElOR19SRUNFSVBUCjIwMjUtMDEtMDg7MjAyNS0wMS0wNzsxMC41ODtkZXNjcmnDp8OjbyBkbyBjb250YXMgOTtBV0FJVElOR19QQVlNRU5UCjIwMjUtMDEtMDk7MjAyNS0wMS0wODsxMC41OTtkZXNjcmnDp8OjbyBkbyBjb250YXMgMTA7UEFZTUVOVF9NQURFCjIwMjUtMDEtMTA7MjAyNS0wMS0wOTsxMC42MDtkZXNjcmnDp8OjbyBkbyBjb250YXMgMTE7QVdBSVRJTkdfUkVDRUlQVAoyMDI1LTAxLTExOzIwMjUtMDEtMTA7MTAuNjE7ZGVzY3Jpw6fDo28gZG8gY29udGFzIDEyO0FXQUlUSU5HX1JFQ0VJUFQKMjAyNS0wMS0xMjsyMDI1LTAxLTExOzEwLjYyO2Rlc2NyacOnw6NvIGRvIGNvbnRhcyAxMztBV0FJVElOR19QQVlNRU5UCjIwMjUtMDEtMTM7MjAyNS0wMS0xMjsxMC42MztkZXNjcmnDp8OjbyBkbyBjb250YXMgMTQ7UEFZTUVOVF9NQURFCjIwMjUtMDEtMTQ7MjAyNS0wMS0xMzsxMC42NDtkZXNjcmnDp8OjbyBkbyBjb250YXMgMTU7QVdBSVRJTkdfUkVDRUlQVAoyMDI1LTAxLTE1OzIwMjUtMDEtMTQ7MTAuNjU7ZGVzY3Jpw6fDo28gZG8gY29udGFzIDE2O0FXQUlUSU5HX1JFQ0VJUFQKMjAyNS0wMS0xNjsyMDI1LTAxLTE1OzEwLjY2O2Rlc2NyacOnw6NvIGRvIGNvbnRhcyAxNztBV0FJVElOR19QQVlNRU5UCjIwMjUtMDEtMTc7MjAyNS0wMS0xNjsxMC42NztkZXNjcmnDp8OjbyBkbyBjb250YXMgMTg7UEFZTUVOVF9NQURFCjIwMjUtMDEtMTg7MjAyNS0wMS0xNzsxMC42ODtkZXNjcmnDp8OjbyBkbyBjb250YXMgMTk7QVdBSVRJTkdfUkVDRUlQVA==

Nota: Este exemplo acima está em Base64, ao decodificar verá o modelo.