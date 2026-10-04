# API de Clientes

Uma API RESTful desenvolvida com Spring Boot para gerenciamento de clientes. Esta aplicação fornece endpoints completos para operações CRUD (Create, Read, Update, Delete) em um banco de dados PostgreSQL.

## 🚀 Funcionalidades

- **Gerenciamento de Clientes**: Operações completas de CRUD para cadastro de clientes
- **Validação de Dados**: Validação automática de campos obrigatórios e formato de email
- **Tratamento de Exceções**: Tratamento global de erros com mensagens claras
- **Email Único**: Garantia de unicidade de email no cadastro de clientes
- **DTOs**: Separação clara entre entidades de banco e objetos de transferência de dados
- **Testes Unitários**: Cobertura de testes para a camada de serviço

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 4.0.7**
- **Spring Data JPA**
- **Spring Web MVC**
- **Spring Validation**
- **PostgreSQL**
- **Lombok**
- **Maven**

## 📋 Pré-requisitos

- Java 17 ou superior instalado
- Maven 3.6+ instalado
- PostgreSQL 12+ instalado e rodando
- IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code, etc.)

## 🔧 Instalação e Configuração

### 1. Clone o repositório

```bash
git clone https://github.com/FlavioAlexandreRibeiro/java-api-clientes
cd api_clientes
```

### 2. Configure o Banco de Dados

Crie um banco de dados PostgreSQL:

```sql
CREATE DATABASE api_clientes;
```

### 3. Configure as Credenciais

Edite o arquivo `src/main/resources/application.properties` ou configure as variáveis de ambiente:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/api_clientes
spring.datasource.username=postgres
spring.datasource.password=sua_senha
```

Ou usando variáveis de ambiente:

```bash
export DB_URL=jdbc:postgresql://localhost:5432/api_clientes
export DB_USERNAME=postgres
export DB_PASSWORD=sua_senha
```

### 4. Compile o Projeto

```bash
./mvnw clean install
```

Ou usando Maven diretamente:

```bash
mvn clean install
```

## ▶️ Executando a Aplicação

### Via Maven

```bash
./mvnw spring-boot:run
```

### Via JAR

```bash
java -jar target/API-de-Clientes-0.0.1-SNAPSHOT.jar
```

A aplicação estará disponível em: `http://localhost:8080`

## 📡 Endpoints da API

### Criar Cliente

**POST** `/clientes`

```json
{
  "nome": "João Silva",
  "email": "joao.silva@email.com",
  "telefone": "11999999999"
}
```

**Response**: `201 Created`

```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao.silva@email.com",
  "telefone": "11999999999"
}
```

### Listar Todos os Clientes

**GET** `/clientes`

**Response**: `200 OK`

```json
[
  {
    "id": 1,
    "nome": "João Silva",
    "email": "joao.silva@email.com",
    "telefone": "11999999999"
  }
]
```

### Buscar Cliente por ID

**GET** `/clientes/{id}`

**Response**: `200 OK`

```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao.silva@email.com",
  "telefone": "11999999999"
}
```

### Atualizar Cliente

**PUT** `/clientes/{id}`

```json
{
  "nome": "João Silva Jr.",
  "email": "joao.jr@email.com",
  "telefone": "11988888888"
}
```

**Response**: `200 OK`

```json
{
  "id": 1,
  "nome": "João Silva Jr.",
  "email": "joao.jr@email.com",
  "telefone": "11988888888"
}
```

### Deletar Cliente

**DELETE** `/clientes/{id}`

**Response**: `204 No Content`

## 🧪 Testes

Para executar os testes unitários:

```bash
./mvnw test
```

Ou usando Maven:

```bash
mvn test
```

## 📄 Estrutura do Projeto

```
api_clientes/
├── src/
│   ├── main/
│   │   ├── java/com/API_de_Clientes/
│   │   │   ├── controller/     # Controladores REST
│   │   │   ├── dto/            # Data Transfer Objects
│   │   │   ├── entity/         # Entidades JPA
│   │   │   ├── exception/      # Tratamento de exceções
│   │   │   ├── repository/     # Repositórios JPA
│   │   │   └── service/        # Lógica de negócio
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/API_de_Clientes/
│           └── service/        # Testes unitários
├── pom.xml
└── README.md
```

## 🔍 Validações

- **nome**: Obrigatório, máximo 100 caracteres
- **email**: Obrigatório, único, máximo 150 caracteres
- **telefone**: Obrigatório, máximo 20 caracteres

## ⚠️ Tratamento de Erros

A API retorna códigos de status HTTP apropriados:

- `201 Created`: Cliente criado com sucesso
- `200 OK`: Operação realizada com sucesso
- `204 No Content`: Cliente deletado com sucesso
- `400 Bad Request`: Dados inválidos
- `404 Not Found`: Cliente não encontrado
- `409 Conflict`: Email já cadastrado
- `500 Internal Server Error`: Erro interno no servidor

## 📝 Coleção Postman

Uma coleção Postman está disponível no arquivo `postman_collection.json` para facilitar os testes dos endpoints.

## 🤝 Contribuindo

Contribuições são bem-vindas! Por favor:

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

## 👤 Autor
### Flavio Alexandre Ribeiro
Desenvolvido como parte do projeto de API de Clientes.

## 📧 Contato
GitHub:https://github.com/FlavioAlexandreRibeiro
LinkedIn:https://www.linkedin.com/in/flavioalexandreribeiro1986/?isSelfProfile=true

Para dúvidas ou sugestões, abra uma issue no repositório.
