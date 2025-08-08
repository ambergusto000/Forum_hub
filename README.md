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



makefile
Copy
Edit
Authorization: Bearer seu_token_jwt
