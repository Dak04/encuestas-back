package com.pruebatecnica._it.encuestamusical.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EncuestaTest {

    private Encuesta encuesta;

    @BeforeEach
    public void setUp() {
        encuesta = new Encuesta();
    }

    @Test
    public void testSetAndGetId() {
        Integer id = 1;
        encuesta.setId(id);
        assertEquals(id, encuesta.getId());
    }

    @Test
    public void testSetAndGetEmail() {
        String email = "test@example.com";
        encuesta.setemail(email);
        assertEquals(email, encuesta.getemail());
    }

    @Test
    public void testSetAndGetRespuesta() {
        String respuesta = "Pop";
        encuesta.setRespuesta(respuesta);
        assertEquals(respuesta, encuesta.getRespuesta());
    }

    @Test
    public void testEmptyConstructor() {
        // Test to check if a new Encuesta object initializes with null fields
        assertNull(encuesta.getId());
        assertNull(encuesta.getemail());
        assertNull(encuesta.getRespuesta());
    }
}
