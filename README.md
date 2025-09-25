# People CRUD – Fullstack Demo (Spring Boot + Vue.js)

Projeto de estudo fullstack mostrando como construir um **CRUD simples de Pessoas**, armazenando **nome** e **data de nascimento**.

Back-end em **Java 8 + Spring Boot**, banco em **H2 (em memória)**, e front-end em **Vue.js 3 (Vite)** com **Axios**.

---

## 🏗 Arquitetura

**Camadas no back-end (Spring Boot)**:
- **Controller** → expõe API REST (`/api/people`)
- **Service** → lógica de negócio e validações
- **Repository** → persistência (Spring Data JPA)
- **Domain/Entity** → mapeia tabela `people`
- **DTOs** → objetos de entrada e saída (JSON)

**Fluxo:**
```
Vue.js (Axios) → API REST → Controller → Service → Repository → Banco (H2)
```

---

## 🚀 Como rodar

### 1) Back-end (people-api)

Requisitos: **Java 8+**, **Maven**

```bash
cd people-api
mvn spring-boot:run
```

- API disponível em: `http://localhost:8080/api/people`
- Console do H2: `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:peopledb`
  - User: `sa`
  - Password: *(em branco)*

### 2) Front-end (people-web)

Requisitos: **Node 18+**, **npm**

```bash
cd people-web
npm install
npm run dev
```

- Acesse em: `http://localhost:5173`

---

## 📡 Endpoints principais

- `GET /api/people` → lista todas as pessoas
- `POST /api/people` → cria uma nova pessoa
  ```json
  { "name": "Maria", "birthDate": "1990-05-10" }
  ```
- `GET /api/people/{id}` → busca por id
- `PUT /api/people/{id}` → atualiza uma pessoa
- `DELETE /api/people/{id}` → remove uma pessoa

---

## 🖼 Front-end

Tela com:
- Formulário de cadastro (nome, data de nascimento)
- Lista de pessoas cadastradas
- Ações de editar e excluir

---

## 🛠 Tecnologias usadas

### Back-end
- Java 8
- Spring Boot (Web, Data JPA, Validation)
- H2 Database
- Lombok

### Front-end
- Vue 3 (Vite)
- Axios
- HTML/CSS básico

---

## 🔮 Próximos passos (idéias)

- Migrar H2 para PostgreSQL ou MySQL
- Adicionar autenticação (Spring Security + JWT)
- Criar componentes separados no Vue
- Testes unitários (JUnit + Mockito)

---

## 📚 Aprendizado

Esse projeto foi feito com foco didático:
- **Back-end**: como organizar pacotes em camadas (Controller, Service, Repository, DTO, Entity).
- **Front-end**: como consumir uma API REST com Axios e reatividade do Vue.
- **Integração**: como o front se comunica com o back e como os dados são persistidos.

---

💡 Sinta-se livre para clonar, rodar e modificar!
