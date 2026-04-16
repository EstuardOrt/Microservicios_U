# Manual de Usuario: Sistema de Gestión Universitaria (API)

El presente documento establece las directrices técnicas para la administración de la información institucional a través de los servicios de **Facultades**, **Carreras**, **Investigadores** e **Investigaciones**. El sistema emplea una arquitectura de microservicios cuya exposición de servicios es gestionada mediante un servidor Nginx.

---

### Índice
1. [Gestión de Facultades y Carreras (ms-facultades)](#gestión-de-facultades-y-carreras-ms-facultades)
2. [Gestión de Investigadores (ms-investigadores)](#gestión-de-investigadores-ms-investigadores)
3. [Gestión de Investigaciones (ms-investigaciones)](#gestión-de-investigaciones-ms-investigaciones)
4. [Recomendaciones de Uso](#recomendaciones-de-uso)
5. [Nota sobre Entornos de Producción](#nota-sobre-entornos-de-producción)

---

## Gestión de Facultades y Carreras (ms-facultades)

Este módulo centraliza la administración de las unidades académicas superiores y sus respectivos programas de estudio.

### Operaciones de Facultades

#### Visualización de todas las facultades
Permite obtener el catálogo completo de las facultades registradas.
* **Postman:** Configurar el método `GET` con la dirección `http://localhost/facultades`.
* **cURL:**
    ```bash
    curl -X GET http://localhost/facultades
    ```

#### Registro de una nueva facultad
Incorpora una entidad académica al sistema proporcionando sus datos generales.
* **Postman:** Método `POST` a `http://localhost/facultades`. En la sección **Body -> raw -> JSON**:
    ```json
    {
      "nombre": "Facultad de Ingeniería",
      "siglas": "FI",
      "nombreDecano": "Ing. Pedro Paredes",
      "telefono": "12345678",
      "correo": "ingenieria@universidad.edu"
    }
    ```
* **cURL:**
    ```bash
    curl -X POST http://localhost/facultades \
    -H "Content-Type: application/json" \
    -d '{"nombre": "Facultad de Ingeniería", "siglas": "FI", "nombreDecano": "Ing. Pedro Paredes", "telefono": "12345678", "correo": "ingenieria@universidad.edu"}'
    ```

#### Consulta de carreras por facultad
Recupera los programas académicos vinculados a una facultad específica mediante su identificador.
* **Postman:** Método `GET` a la dirección `http://localhost/facultades/1/carreras`.
* **cURL:**
    ```bash
    curl -X GET http://localhost/facultades/1/carreras
    ```

### Operaciones de Carreras

#### Registro de carrera profesional
Añade un programa de estudio vinculándolo a una facultad existente.
* **Postman:** Método `POST` a `http://localhost/carreras`. **Body (JSON)**:
    ```json
    {
      "nombre": "Ingeniería en Sistemas",
      "duracionAnios": 5,
      "facultad": {"id": 1}
    }
    ```
* **cURL:**
    ```bash
    curl -X POST http://localhost/carreras \
    -H "Content-Type: application/json" \
    -d '{"nombre": "Ingeniería en Sistemas", "duracionAnios": 5, "facultad": {"id": 1}}'
    ```

---

## Gestión de Investigadores (ms-investigadores)

Módulo encargado de la administración del personal científico y sus acreditaciones académicas.

#### Creación de perfil de investigador
Registra a un nuevo investigador permitiendo la inclusión simultánea de sus títulos iniciales.
* **Postman:** Método `POST` a `http://localhost/investigadores`. **Body (JSON)**:
    ```json
    {
      "nombres": "Pedro",
      "apellidos": "Paredes",
      "cui": "1000200030101",
      "correo": "dParedes@investigacion.edu",
      "facultadId": 1,
      "areaCientifica": "Blockchain",
      "telefono": "22223333",
      "celular": "44445555",
      "direccion": "San José Pinula",
      "titulos": [
        {
          "nombre": "Ingeniero en Sistemas",
          "universidad": "UDV"
        }
      ]
    }
    ```
* **cURL:**
    ```bash
    curl -X POST http://localhost/investigadores \
    -H "Content-Type: application/json" \
    -d '{"nombres": "Pedro", "apellidos": "Paredes", "cui": "1000200030101", "correo": "dParedes@investigacion.edu", "facultadId": 1, "areaCientifica": "Blockchain", "telefono": "22223333", "celular": "44445555", "direccion": "San José Pinula", "titulos": [{"nombre": "Ingeniero en Sistemas", "universidad": "UDV"}]}'
    ```

#### Búsqueda de investigador por CUI
Localiza la información detallada de un investigador mediante su Código Único de Identificación.
* **Postman:** Método `GET` a `http://localhost/investigadores/cui/1000200030101`.
* **cURL:**
    ```bash
    curl -X GET http://localhost/investigadores/cui/1000200030101
    ```

#### Incorporación de títulos adicionales
Permite registrar nuevas acreditaciones académicas a un investigador existente.
* **Postman:** Método `POST` a `http://localhost/investigadores/cui/1000200030101/titulos`. **Body (JSON)**:
    ```json
    {
      "nombre": "Maestría en Seguridad Informática",
      "universidad": "Universidad de San Carlos"
    }
    ```
* **cURL:**
    ```bash
    curl -X POST http://localhost/investigadores/cui/1000200030101/titulos \
    -H "Content-Type: application/json" \
    -d '{"nombre": "Maestría en Seguridad Informática", "universidad": "Universidad de San Carlos"}'
    ```

---

## Gestión de Investigaciones (ms-investigaciones)

Este servicio supervisa el ciclo de vida de los proyectos científicos y la conformación de los equipos de trabajo.

#### Registro de proyecto de investigación
Inicia un nuevo proyecto definiendo al responsable y al equipo de colaboradores.
* **Postman:** Método `POST` a `http://localhost/investigaciones`. **Body (JSON)**:
    ```json
    {
      "titulo": "Arquitectura Blockchain en Guatemala",
      "fechaRealizacion": "2026-04-16",
      "duracion": "18 meses",
      "facultadId": 1,
      "investigadorResponsableId": 1,
      "equipoTrabajo": [
        {
          "investigadorAsociadoId": 2,
          "rol": "Analista Técnico"
        }
      ]
    }
    ```
* **cURL:**
    ```bash
    curl -X POST http://localhost/investigaciones \
    -H "Content-Type: application/json" \
    -d '{"titulo": "Arquitectura Blockchain en Guatemala", "fechaRealizacion": "2026-04-16", "duracion": "18 meses", "facultadId": 1, "investigadorResponsableId": 1, "equipoTrabajo": [{"investigadorAsociadoId": 2, "rol": "Analista Técnico"}]}'
    ```

#### Búsqueda filtrada de proyectos
Permite localizar investigaciones mediante el título o el identificador del investigador.
* **Postman:** Método `GET` con parámetros de consulta:
    * Por título: `http://localhost/investigaciones?titulo=Blockchain`
    * Por investigador: `http://localhost/investigaciones?investigadorId=1`
* **cURL:**
    ```bash
    curl -X GET "http://localhost/investigaciones?titulo=Blockchain"
    ```

#### Adición de miembros al equipo de trabajo
Integra nuevos colaboradores a un proyecto de investigación vigente.
* **Postman:** Método `POST` a `http://localhost/investigaciones/1/equipo`. **Body (JSON)**:
    ```json
    {
      "investigadorAsociadoId": 3,
      "rol": "Asistente de Investigación"
    }
    ```
* **cURL:**
    ```bash
    curl -X POST http://localhost/investigaciones/1/equipo \
    -H "Content-Type: application/json" \
    -d '{"investigadorAsociadoId": 3, "rol": "Asistente de Investigación"}'
    ```

---

## Recomendaciones de Uso

1.  **Protocolo:** Se debe verificar que la acción HTTP (GET, POST, PUT, DELETE) sea la correcta según la operación requerida.
2.  **Formato de Datos:** El sistema requiere obligatoriamente el encabezado `Content-Type: application/json` para cualquier envío de información en el cuerpo de la petición.
3.  **Identificadores:** Los valores representados como `/1` en los ejemplos deben sustituirse por el ID real del registro que se desea afectar.

---

## Nota sobre Entornos de Producción

Los enlaces y direcciones presentados en este manual utilizan `localhost` bajo la premisa de que el tráfico es redirigido internamente por un servidor. Es importante considerar que estas direcciones deberán ser reemplazadas por el nombre de dominio oficial, la dirección URL institucional o la dirección IP pública correspondiente al momento de realizar el despliegue en un entorno de producción.