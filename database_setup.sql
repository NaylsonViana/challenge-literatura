-- Script de configuração do banco de dados LiterAlura
-- Execute este script no PostgreSQL para criar o banco de dados

-- Criar o banco de dados (se não existir)
-- Descomente a linha abaixo se precisar criar o banco
-- CREATE DATABASE literalura;

-- Conectar ao banco literalura
-- \c literalura;

-- As tabelas serão criadas automaticamente pelo Hibernate
-- quando a aplicação for executada pela primeira vez

-- Verificar se as tabelas foram criadas (execute após rodar a aplicação)
-- SELECT table_name FROM information_schema.tables WHERE table_schema = 'public';

-- Exemplo de consultas úteis para verificar os dados:

-- Listar todos os autores
-- SELECT * FROM autores;

-- Listar todos os livros com seus autores
-- SELECT l.titulo, l.idioma, l.numero_downloads, a.nome as autor 
-- FROM livros l 
-- JOIN autores a ON l.autor_id = a.id;

-- Contar total de livros por idioma
-- SELECT idioma, COUNT(*) as total 
-- FROM livros 
-- GROUP BY idioma 
-- ORDER BY total DESC;

-- Top 5 livros mais baixados
-- SELECT titulo, numero_downloads 
-- FROM livros 
-- ORDER BY numero_downloads DESC 
-- LIMIT 5; 