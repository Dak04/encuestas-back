# Encuestas Backend

Este proyecto es un backend para gestionar encuestas musicales, desarrollado con Spring Boot y Java 17. Utiliza MySQL como base de datos, que se ejecuta en un contenedor Docker para facilitar la configuración y despliegue.

## Tabla de Contenidos
- [Características](#características)
- [Tecnologías](#tecnologías)
- [Requisitos](#requisitos)
- [Configuración del Proyecto](#configuración-del-proyecto)
- [Uso de Docker](#uso-de-docker)
- [API Endpoints](#api-endpoints)
- [Ejemplo de Solicitudes](#ejemplo-de-solicitudes)
- [Contribuciones](#contribuciones)
- [Licencia](#licencia)

## Características

- Registro de encuestas musicales.
- Consulta de resultados de encuestas agrupados por estilo musical.
- Implementación de CORS para permitir solicitudes desde aplicaciones front-end.

## Tecnologías

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [MySQL](https://www.mysql.com/)
- [Docker](https://www.docker.com/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)

## Requisitos

- [Docker](https://docs.docker.com/get-docker/) instalado en tu máquina.

## Configuración del Proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/Dak04/encuestas-back.git
   cd encuestas-back
2. Asegúrate de tener configurado el archivo application.properties con tus credenciales de base de datos y otras configuraciones necesarias.

3. Iniciar base de datos:
   ```bash
   docker-compose up

## API Endpoints

- Guardar Encuesta
   URL: /demo/guardarencuesta
   Método: PUT
   Cuerpo:
      {
         "email": "valentina@gmail.com",
         "respuesta": "Pop"
      }
- Obtener Resultados:
   URL: /demo/api/results
   Método: GET
   Respuesta:
      {
         "Pop": 1,
         "Rock": 1
      }


