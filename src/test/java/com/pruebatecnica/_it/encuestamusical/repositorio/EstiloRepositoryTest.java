package com.pruebatecnica._it.encuestamusical.repositorio;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.pruebatecnica._it.encuestamusical.dominio.Estilo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EstiloRepositoryTest {

    @Autowired
    private EstiloRepository estiloRepository;

    @BeforeEach
    void setUp() {
        estiloRepository.deleteAll();  // Limpiar los datos antes de cada prueba
    }

    @Test
    @Rollback(true)
    void testFindByDescripcion() {
        // Crear y guardar un estilo musical
        Estilo estilo = new Estilo();
        estilo.setDescripcion("Rock");
        estiloRepository.save(estilo);

        // Buscar el estilo por su descripción
        Estilo found = estiloRepository.findByDescripcion("Rock");

        // Verificar que el estilo fue encontrado
        assertNotNull(found);
        assertEquals("Rock", found.getDescripcion());
    }

    @Test
    @Rollback(true)
    void testGetAllStyles() {
        // Crear y guardar varios estilos musicales
        Estilo estilo1 = new Estilo();
        estilo1.setDescripcion("Pop");
        estiloRepository.save(estilo1);

        Estilo estilo2 = new Estilo();
        estilo2.setDescripcion("Jazz");
        estiloRepository.save(estilo2);

        Estilo estilo3 = new Estilo();
        estilo3.setDescripcion("Rock");
        estiloRepository.save(estilo3);

        // Obtener todos los estilos musicales usando el método personalizado
        List<String> allStyles = estiloRepository.getAllStyles();

        // Verificar que los estilos fueron obtenidos correctamente
        assertNotNull(allStyles);
        assertEquals(3, allStyles.size());
        assertTrue(allStyles.contains("Pop"));
        assertTrue(allStyles.contains("Jazz"));
        assertTrue(allStyles.contains("Rock"));
    }
}
