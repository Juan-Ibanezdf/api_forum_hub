CREATE TABLE usuarios (
                          id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          nome VARCHAR(150) NOT NULL,
                          email VARCHAR(150) NOT NULL,
                          senha VARCHAR(255) NOT NULL
);

CREATE TABLE cursos (
                        id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        nome VARCHAR(255) NOT NULL,
                        categoria VARCHAR(255) NOT NULL
);
