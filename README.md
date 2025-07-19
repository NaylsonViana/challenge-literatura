# 📚 LiterAlura - Catálogo de Livros

O **LiterAlura** é um sistema de catálogo de livros que consome a API gratuita **Gutendex** para buscar informações de mais de 70 mil livros e persiste os dados em um banco PostgreSQL.

## 🎯 Funcionalidades

### Funcionalidades Principais

1. **Buscar livro pelo título** - Consulta a API Gutendex e adiciona ao banco de dados
2. **Listar livros registrados** - Mostra todos os livros salvos no catálogo
3. **Listar autores** - Exibe todos os autores com seus livros
4. **Listar autores vivos em determinado ano** - Busca autores que estavam vivos em um ano específico
5. **Listar livros em determinado idioma** - Filtra livros por idioma (PT, EN, ES, FR)

### Funcionalidades Extras

6. **Buscar livros por autor** - Encontra todos os livros de um autor específico
7. **Top 10 livros mais baixados** - Ranking dos livros mais populares
8. **Estatísticas do catálogo** - Resumo com métricas do sistema

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5.3**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**
- **API Gutendex** (https://gutendex.com/)

## 📋 Pré-requisitos

1. **Java 17** ou superior
2. **PostgreSQL** instalado e rodando
3. **Maven** (opcional, o projeto inclui o wrapper)

## 🚀 Como Executar

### 1. Configurar o Banco de Dados

1. Instale o PostgreSQL em sua máquina
2. Crie um banco de dados chamado `literalura`:
   ```sql
   CREATE DATABASE literalura;
   ```

### 2. Configurar as Credenciais

O projeto usa variáveis de ambiente para as credenciais do banco de dados por segurança.

#### Opção 1: Variável de Ambiente (Recomendado)

Configure a variável de ambiente `DB_PASSWORD`:

```bash
# Windows (PowerShell)
$env:DB_PASSWORD="sua_senha_aqui"

# Windows (CMD)
set DB_PASSWORD=sua_senha_aqui

# Linux/Mac
export DB_PASSWORD=sua_senha_aqui
```

#### Opção 2: Editar application.properties

Se preferir, edite o arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.password=sua_senha_aqui
```

**⚠️ IMPORTANTE**: Nunca commite senhas no repositório Git!

### 3. Executar o Projeto

#### Opção 1: Usando Maven Wrapper (Recomendado)

```bash
./mvnw spring-boot:run
```

#### Opção 2: Usando Maven

```bash
mvn spring-boot:run
```

#### Opção 3: Usando IDE

Execute a classe `DemoApplication.java` diretamente na sua IDE.

### 4. Usar o Sistema

Após a execução, o sistema apresentará um menu interativo no terminal com as seguintes opções:

```
==================================================
🎯 LITERALURA - CATÁLOGO DE LIVROS 🎯
==================================================
1 - Buscar livro pelo título
2 - Listar livros registrados
3 - Listar autores
4 - Listar autores vivos em determinado ano
5 - Listar livros em determinado idioma
6 - Buscar livros por autor
7 - Top 10 livros mais baixados
8 - Estatísticas do catálogo
0 - Sair
==================================================
```

## 📖 Exemplos de Uso

### Buscar um Livro

1. Escolha a opção **1**
2. Digite o título: `Dom Casmurro`
3. O sistema buscará na API e salvará no banco

### Listar Autores Vivos em 1800

1. Escolha a opção **4**
2. Digite o ano: `1800`
3. Verá autores como Jane Austen

### Buscar Livros em Português

1. Escolha a opção **5**
2. Digite o código: `PT`
3. Verá livros como "Dom Casmurro"

## 🗂️ Estrutura do Projeto

```
src/main/java/literatura/demo/
├── controller/
│   └── LiteraluraController.java    # Interface de linha de comando
├── dto/
│   ├── GutendexResponse.java        # Resposta da API
│   ├── GutendexBook.java           # DTO do livro da API
│   └── GutendexAuthor.java         # DTO do autor da API
├── model/
│   ├── Autor.java                  # Entidade Autor
│   └── Livro.java                  # Entidade Livro
├── repository/
│   ├── AutorRepository.java        # Repositório de Autores
│   └── LivroRepository.java        # Repositório de Livros
├── service/
│   ├── GutendexService.java        # Serviço da API externa
│   └── LiteraluraService.java      # Serviço principal
└── DemoApplication.java            # Classe principal
```

## 🔧 Configurações

### application.properties

```properties
# Configuração do PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD:postgres}

# Configuração JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=false

# API Gutendex
api.gutendex.base-url=https://gutendex.com/books
```

## 📊 Banco de Dados

O sistema cria automaticamente as seguintes tabelas:

- **autores**: Armazena informações dos autores
- **livros**: Armazena informações dos livros com referência aos autores

## 🌟 Funcionalidades Extras Implementadas

- **Busca por autor**: Encontre todos os livros de um autor específico
- **Top 10 downloads**: Ranking dos livros mais baixados
- **Estatísticas**: Métricas completas do catálogo
- **Interface amigável**: Menu colorido com emojis
- **Tratamento de erros**: Mensagens claras para o usuário

## 🤝 Contribuição

Este projeto foi desenvolvido como parte do desafio LiterAlura. Sinta-se à vontade para:

1. Fazer um fork do projeto
2. Criar uma branch para sua feature
3. Commit suas mudanças
4. Push para a branch
5. Abrir um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

## 🆘 Suporte

Se encontrar algum problema:

1. Verifique se o PostgreSQL está rodando
2. Confirme se as credenciais estão corretas
3. Verifique se a conexão com a internet está funcionando (para a API)
4. Consulte os logs do Spring Boot para mais detalhes

---

**Desenvolvido com ❤️ para o desafio LiterAlura**
