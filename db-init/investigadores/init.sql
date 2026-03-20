CREATE TABLE INVESTIGADOR (
    id SERIAL PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    celular VARCHAR(20),
    correo VARCHAR(100),
    direccion TEXT,
    municipio_nacimiento VARCHAR(100),
    departamento_nacimiento VARCHAR(100),
    fecha_nacimiento DATE,
    cui VARCHAR(13) UNIQUE, 
    pasaporte VARCHAR(20),
    area_cientifica VARCHAR(150),
    facultad_id INT 
);

CREATE TABLE TITULO (
    id SERIAL PRIMARY KEY,
    investigador_id INT NOT NULL,
    nivel_academico VARCHAR(100),
    nombre_titulo VARCHAR(200),
    CONSTRAINT fk_investigador 
        FOREIGN KEY (investigador_id) 
        REFERENCES INVESTIGADOR(id) 
        ON DELETE CASCADE
);