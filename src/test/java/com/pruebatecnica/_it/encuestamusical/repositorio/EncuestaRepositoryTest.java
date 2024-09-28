package com.pruebatecnica._it.encuestamusical.repositorio;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.pruebatecnica._it.encuestamusical.dominio.Encuesta;

@DataJpaTest // Utiliza una base de datos en memoria para los tests
class EncuestaRepositoryTest {

    @Autowired
    private EncuestaRepository encuestaRepository;

    @Test
    void testFindByEmail() {
        // Crear y guardar una encuesta
        Encuesta encuesta = new Encuesta();
        encuesta.setemail("test@example.com");
        encuesta.setRespuesta("Rock");
        encuestaRepository.save(encuesta);

        // Buscar la encuesta por email
        Encuesta found = encuestaRepository.findByEmail("test@example.com");

        // Verificar que se ha encontrado correctamente
        assertNotNull(found);
        assertEquals("test@example.com", found.getemail());
        assertEquals("Rock", found.getRespuesta());
    }

    @Test
    @Sql("/test-data.sql") // Opcional: carga datos desde un archivo SQL si es necesario
    void testCountByRespuesta() {
        // Crear y guardar varias encuestas
        Encuesta encuesta1 = new Encuesta();
        encuesta1.setemail("user1@example.com");
        encuesta1.setRespuesta("Rock");
        encuestaRepository.save(encuesta1);

        Encuesta encuesta2 = new Encuesta();
        encuesta2.setemail("user2@example.com");
        encuesta2.setRespuesta("Pop");
        encuestaRepository.save(encuesta2);

        Encuesta encuesta3 = new Encuesta();
        encuesta3.setemail("user3@example.com");
        encuesta3.setRespuesta("Rock");
        encuestaRepository.save(encuesta3);

        // Obtener los resultados agrupados por respuesta
        List<Object[]> results = encuestaRepository.countByRespuesta();

        // Verificar que los resultados sean correctos
        assertNotNull(results);
        assertEquals(2, results.size());

        for (Object[] result : results) {
            String respuesta = (String) result[0];
            Long count = (Long) result[1];

            if ("Rock".equals(respuesta)) {
                assertEquals(2L, count); // 2 respuestas de "Rock"
            } else if ("Pop".equals(respuesta)) {
                assertEquals(1L, count); // 1 respuesta de "Pop"
            } else {
                fail("Respuesta inesperada: " + respuesta);
            }
        }
    }
}
