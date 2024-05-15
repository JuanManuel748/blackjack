CREATE DATABASE blackJack;
USE blackJack;
DROP TABLE IF EXISTS jugadores;

CREATE TABLE jugadores (
	nombre VARCHAR(100) PRIMARY KEY,
    dinero INT(10),
    
    CONSTRAINT dinero_ch CHECK (dinero > 0)
);
