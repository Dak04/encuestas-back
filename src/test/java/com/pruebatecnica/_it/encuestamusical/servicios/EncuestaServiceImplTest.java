package com.pruebatecnica._it.encuestamusical.servicios;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.pruebatecnica._it.encuestamusical.dominio.Encuesta;
import com.pruebatecnica._it.encuestamusical.dominio.User;
import com.pruebatecnica._it.encuestamusical.repositorio.EncuestaRepository;

class EncuestaServiceImplTest {

    @Mock
    private EncuestaRepository encuestaRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private EncuestaServiceImpl encuestaServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void testResponder_NewUser_Successful() {
        String email = "user@example.com";
        String respuesta = "Rock";

        // Simula que el usuario no existe
        when(userService.getUserByEmail(email)).thenReturn(null);
        when(encuestaRepository.findByEmail(email)).thenReturn(null);

        // Simula la creación del usuario y el guardado de la encuesta
        doNothing().when(userService).add(email, email);
        when(encuestaRepository.save(any(Encuesta.class))).thenReturn(null);

        // Llama al método `responder`
        String result = encuestaServiceImpl.responder(email, respuesta);

        // Verificaciones
        assertEquals("Encuesta guardada", result);
        verify(userService, times(1)).add(email, email); // Verifica que el usuario fue agregado
        verify(encuestaRepository, times(1)).save(any(Encuesta.class)); // Verifica que la encuesta fue guardada
    }

    @Test
    void testResponder_ExistingUserAndSurvey() {
        String email = "user@example.com";
        String respuesta = "Pop";

        // Simula que el usuario ya existe y tiene una encuesta respondida
        User existingUser = new User();
        existingUser.setEmail(email);
        when(userService.getUserByEmail(email)).thenReturn(existingUser);

        Encuesta existingEncuesta = new Encuesta();
        existingEncuesta.setemail(email);
        when(encuestaRepository.findByEmail(email)).thenReturn(existingEncuesta);

        // Llama al método `responder`
        String result = encuestaServiceImpl.responder(email, respuesta);

        // Verificaciones
        assertEquals("Correo con encuesta respondida", result);
        verify(encuestaRepository, never()).save(any(Encuesta.class)); // No debe guardar la encuesta de nuevo
    }

    @Test
    void testObtenerResultados() {
        // Simula los resultados de la encuesta
        List<Object[]> mockResultados = Arrays.asList(
                new Object[]{"Rock", 3L},
                new Object[]{"Pop", 5L}
        );

        when(encuestaRepository.countByRespuesta()).thenReturn(mockResultados);

        // Llama al método `obtenerResultados`
        Map<String, Long> result = encuestaServiceImpl.obtenerResultados();

        // Verificaciones
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(3L, result.get("Rock"));
        assertEquals(5L, result.get("Pop"));

        verify(encuestaRepository, times(1)).countByRespuesta();
    }
}
