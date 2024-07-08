drop table if exists objeto_voador;

CREATE TABLE objeto_voador (
    id VARCHAR(255) primary KEY,
    data date,
    nome VARCHAR(255),
    diametroMinKm double,
    diametroMaxKm double,
    risco VARCHAR(255),
    dataDeAproximacao VARCHAR(255),
    velocidadeAproxKm double,
    distancia double

);