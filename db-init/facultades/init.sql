CREATE TABLE FACULTAD (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    siglas VARCHAR(15),
    nombre_decano VARCHAR(150),
    telefono VARCHAR(20),
    correo VARCHAR(100)
);

CREATE TABLE CARRERA (
    id SERIAL PRIMARY KEY,
    facultad_id INT NOT NULL,
    nombre VARCHAR(150) NOT NULL,
    CONSTRAINT fk_facultad 
        FOREIGN KEY (facultad_id) 
        REFERENCES FACULTAD(id) 
        ON DELETE CASCADE
);