CREATE DATABASE sistemavotacao;

CREATE TYPE status_enum AS ENUM ('ativa', 'encerrada');

-- Tabela de usuários
CREATE TABLE usuario (
                         id_usuario SERIAL PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         email VARCHAR(100) UNIQUE
);

-- Tabela de enquetes
CREATE TABLE enquete (
                         id_enquete SERIAL PRIMARY KEY,
                         titulo VARCHAR(200) NOT NULL,
                         descricao TEXT,
                         data_inicio TIMESTAMP,
                         data_fim TIMESTAMP,
                         status status_enum DEFAULT 'ativa'
);

-- Tabela de opções de voto
CREATE TABLE opcao (
                       id_opcao SERIAL PRIMARY KEY,
                       id_enquete INT NOT NULL,
                       descricao_opcao VARCHAR(200) NOT NULL,
                       FOREIGN KEY (id_enquete) REFERENCES enquete(id_enquete)
                           ON DELETE CASCADE
);

-- Tabela de votos
CREATE TABLE voto (
                      id_voto SERIAL PRIMARY KEY,
                      id_usuario INT NOT NULL,
                      id_enquete INT NOT NULL,
                      id_opcao INT NOT NULL,
                      data_voto TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
                          ON DELETE CASCADE,
                      FOREIGN KEY (id_enquete) REFERENCES enquete(id_enquete)
                          ON DELETE CASCADE,
                      FOREIGN KEY (id_opcao) REFERENCES opcao(id_opcao)
                          ON DELETE CASCADE,
                      UNIQUE (id_usuario, id_enquete)
);
