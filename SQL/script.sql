create database Camy; /* trocar pela sua database, por favor*/
use Camy; /* trocar pela sua database, por favor*/
drop database Camy; /* trocar pela sua database, por favor*/

CREATE TABLE Usuario (
    id INT AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha CHAR (100) NOT NULL,
    ativo BOOLEAN NOT NULL,     
    PRIMARY KEY (id)
);


CREATE TABLE UsuarioAtributo (
    id INT PRIMARY KEY,
    id_usuario INT,
    nome_atributo VARCHAR(255),
    valor_atributo VARCHAR(255),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id),
    UNIQUE KEY unique_usuario_atributo (id_usuario, nome_atributo)
);

CREATE TABLE Categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id) ON DELETE CASCADE
);

CREATE TABLE Periodicidade (
	id INT AUTO_INCREMENT ,
    id_usuario INT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id)
    );

CREATE TABLE ContasDinheiro (
    id INT AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    saldo_inicial DECIMAL(10,2),
    data_saldo_inicial DATE,
    PRIMARY KEY (id),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id)
);

CREATE TABLE Lancamento (
    id INT AUTO_INCREMENT,
    id_categoria INT NOT NULL,
    id_conta INT NOT NULL,
    id_periodicidade INT NOT NULL,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(500),
    valor DECIMAL(10, 2) NOT NULL,
    tipo VARCHAR(100) NOT NULL,
    numero_parcelas INT,
    data_vencimento DATE,
    pago BOOLEAN NOT NULL,
    data_pagamento DATE,
    PRIMARY KEY(id),
    FOREIGN KEY (id_categoria) REFERENCES Categoria(id),
    FOREIGN KEY (id_conta) REFERENCES ContasDinheiro(id),
    FOREIGN KEY (id_periodicidade) REFERENCES Periodicidade(id)
);


CREATE TABLE ProjetoCofrinho (
    id INT AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    nome VARCHAR(255),
    descricao VARCHAR(255),
    prazo DATE,
    data_criacao DATE,
    meta_quantia DECIMAL(10,2),
    ativo BOOLEAN,
    PRIMARY KEY (id),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id)
);

CREATE TABLE RelatorioPc(
     id INT AUTO_INCREMENT,
     id_cofrinho  INT NOT NULL,
     id_conta INT NOT NULL,
     valor DECIMAL(10,2),
     data_insercao DATE,
     PRIMARY KEY (id),
     FOREIGN KEY (id_cofrinho) REFERENCES ProjetoCofrinho (id),
     FOREIGN KEY (id_conta) REFERENCES ContasDinheiro (id)
);

CREATE TABLE HistoricoSaldos (
	id INT AUTO_INCREMENT,
	id_conta INT NOT NULL,
	data_registro DATE NOT NULL,
	saldo DECIMAL(10, 2) NOT NULL,
    ativo BOOLEAN NOT NULL,
    PRIMARY KEY (id),
	FOREIGN KEY (id_conta) REFERENCES ContasDinheiro(id)
);


INSERT INTO Categoria (id_usuario, nome) VALUES 
(1, 'Alimentação'),
(1, 'Receita Principal'),
(1, 'Salário'),
(1, 'Moradia'),
(1, 'Presentes'),
(1, 'Carro'),
(1, 'Bonus'),
(1, 'Coleção Sylvanian Families');

