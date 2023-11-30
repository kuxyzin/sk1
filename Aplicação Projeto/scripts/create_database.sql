CREATE DATABASE IF NOT EXISTS barbearia;
USE barbearia;

CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    servico VARCHAR(255) NOT NULL,
    horario VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    telefone VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS agendamentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_cliente VARCHAR(255) NOT NULL,
    servico VARCHAR(255) NOT NULL,
    horario VARCHAR(255) NOT NULL,
    FOREIGN KEY (nome_cliente) REFERENCES clientes(nome)
);
