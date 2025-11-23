DROP
DATABASE IF EXISTS clinicaveterinariadb;

CREATE
DATABASE clinicaveterinariadb;


DROP TABLE IF EXISTS Consulta;
DROP TABLE IF EXISTS Animal;
DROP TABLE IF EXISTS Veterinario;
DROP TABLE IF EXISTS Proprietario;


CREATE TABLE Proprietario
(
    cpf      VARCHAR(11) PRIMARY KEY,
    nome     VARCHAR(100) NOT NULL,
    telefone VARCHAR(15)  NOT NULL,
    endereco VARCHAR(200) NOT NULL,
    email    VARCHAR(100) NOT NULL
);

CREATE TABLE Animal
(
    id_animal        SERIAL PRIMARY KEY,
    nome             VARCHAR(100)  NOT NULL,
    especie          VARCHAR(50)   NOT NULL,
    raca             VARCHAR(50),
    data_nascimento  DATE          NOT NULL,
    peso             DECIMAL(6, 2) NOT NULL,
    cpf_proprietario VARCHAR(11)   NOT NULL,
    CONSTRAINT fk_proprietario
        FOREIGN KEY (cpf_proprietario)
            REFERENCES Proprietario (cpf)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

CREATE TABLE Veterinario
(
    crmv          VARCHAR(10) PRIMARY KEY,
    nome          VARCHAR(100) NOT NULL,
    especialidade VARCHAR(100),
    telefone      VARCHAR(15)  NOT NULL
);

CREATE TABLE Consulta
(
    id_consulta      SERIAL PRIMARY KEY,
    data_hora        TIMESTAMP      NOT NULL,
    id_animal        INTEGER        NOT NULL,
    crmv_veterinario VARCHAR(10)    NOT NULL,
    diagnostico      TEXT,
    valor            DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_animal
        FOREIGN KEY (id_animal)
            REFERENCES Animal (id_animal)
            ON DELETE RESTRICT
            ON UPDATE CASCADE,
    CONSTRAINT fk_veterinario
        FOREIGN KEY (crmv_veterinario)
            REFERENCES Veterinario (crmv)
            ON DELETE RESTRICT
            ON UPDATE CASCADE
);