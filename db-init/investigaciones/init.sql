CREATE TABLE INVESTIGACION (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    fecha_realizacion DATE,
    duracion VARCHAR(50),
    facultad_id INT,
    investigador_responsable_id INT 
    );

CREATE TABLE EQUIPO_TRABAJO (
    id SERIAL PRIMARY KEY,
    investigacion_id INT NOT NULL,
    investigador_asociado_id INT NOT NULL,
    CONSTRAINT fk_investigacion 
        FOREIGN KEY (investigacion_id) 
        REFERENCES INVESTIGACION(id) 
        ON DELETE CASCADE
        );