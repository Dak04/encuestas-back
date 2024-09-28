package com.pruebatecnica._it.encuestamusical.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EstiloTest {

    private Estilo estilo;

    @BeforeEach
    public void setUp() {
        estilo = new Estilo();
    }

    @Test
    public void testSetAndGetId() {
        Integer id = 1;
        estilo.setId(id);
        assertEquals(id, estilo.getId());
    }

    @Test
    public void testSetAndGetDescripcion() {
        String descripcion = "Rock";
        estilo.setDescripcion(descripcion);
        assertEquals(descripcion, estilo.getDescripcion());
    }

    @Test
    public void testEmptyConstructor() {
        // Test to check if a new Estilo object initializes with null fields
        assertNull(estilo.getId());
        assertNull(estilo.getDescripcion());
    }
}
