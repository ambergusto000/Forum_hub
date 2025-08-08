# 📚 ForumHub API

API REST desenvolvida com **Java** e **Spring Boot** para gerenciamento de tópicos, respostas, usuários e cursos em um fórum acadêmico.  
Inclui autenticação via **JWT**, documentação com **Swagger**, e versionamento de banco de dados com **Flyway**.

---

## 🚀 Tecnologias Utilizadas
- Java 21
- Spring Boot 3+
- Spring Web
- Spring Security (JWT)
- Spring Data JPA
- MySQL
- Flyway (controle de versões do banco)
- Swagger/OpenAPI
- Maven

---

## ⚙️ Funcionalidades
- Cadastro e autenticação de usuários
- Criação, listagem, atualização e exclusão de tópicos
- Respostas em tópicos
- Proteção de rotas com **JWT**
- Controle de acesso por roles (`ROLE_USER`, `ROLE_ADMIN`)
- Documentação interativa da API com Swagger
- Migrations automáticas com Flyway

---

## 🗃️ Estrutura do Banco de Dados

**Tabelas principais:**
- **cursos** `(id, nome)`
- **usuarios** `(id, nome, email, senha, role)`
- **topicos** `(id, titulo, mensagem, data_inc, status, autor_id, curso_id)`
- **respostas** `(id, topico_id, data_inc, autor_id, mensagem)`

As migrations estão localizadas em:
src/main/resources/db/migration/

yaml
Copy
Edit

---

## 🛠️ Como Executar o Projeto Localmente

### ✅ Pré-requisitos
- Java 21+
- MySQL (porta padrão `3306`)
- Maven

### 📥 Passos

1. **Clone o repositório**
```bash
git clone https://github.com/ambergusto000/Forum_hub.git
cd Forum_hub
Configure o banco de dados
Crie um banco no MySQL chamado forum_hub e ajuste o arquivo:

css
Copy
Edit
src/main/resources/application.properties
Exemplo:

properties
Copy
Edit
spring.datasource.url=jdbc:mysql://localhost:3306/forum_hub
spring.datasource.username=root
spring.datasource.password=sua_senha
api.security.token.secret=sua_chave_secreta
Execute a aplicação

bash
Copy
Edit
mvn spring-boot:run


🔐 Autenticação com JWT
Endpoint de login: POST /auth/login

Corpo da requisição:

json
Copy
Edit
{
  "login": "usuario",
  "senha": "senha"
}
Resposta:

json
Copy
Edit
{
  "token": "seu_token_jwt"
}
Uso do token nas requisições protegidas:

makefile
Copy
Edit
Authorization: Bearer seu_token_jwt
