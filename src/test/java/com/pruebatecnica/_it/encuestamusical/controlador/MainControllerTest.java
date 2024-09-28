package com.pruebatecnica._it.encuestamusical.controlador;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.pruebatecnica._it.encuestamusical.dominio.User;
import com.pruebatecnica._it.encuestamusical.servicios.EncuestaService;
import com.pruebatecnica._it.encuestamusical.servicios.EstiloService;
import com.pruebatecnica._it.encuestamusical.servicios.UserService;

@WebMvcTest(MainController.class)
@Rollback
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private EncuestaService encuestaService;

    @MockBean
    private EstiloService estiloService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1);
        user.setName("Test User");
        user.setEmail("test@example.com");
    }

    @Test
    public void testAddNewUser() throws Exception {
        when(userService.add("Test User", "test@example.com")).thenReturn("Saved");

        mockMvc.perform(post("/api/add")
                .param("name", "Test User")
                .param("email", "test@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Saved")));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user));

        mockMvc.perform(get("/api/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Test User")))
                .andExpect(jsonPath("$[0].email", is("test@example.com")));
    }

    @Test
    public void testGuardarRespuesta() throws Exception {
        when(encuestaService.responder("test@example.com", "Pop")).thenReturn("Encuesta guardada");

        mockMvc.perform(post("/api/guardarencuesta")
                .param("email", "test@example.com")
                .param("respuesta", "Pop"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Encuesta guardada")));
    }

    @Test
    public void testGuardarEstilo() throws Exception {
        when(estiloService.add("Rock")).thenReturn("Estilo guardado");

        mockMvc.perform(post("/api/guardarestilo")
                .param("descripcion", "Rock"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("Estilo guardado")));
    }

    @Test
    public void testObtenerResultadosEncuesta() throws Exception {
        Map<String, Long> resultados = new HashMap<>();
        resultados.put("Pop", 2L);
        resultados.put("Rock", 3L);

        when(encuestaService.obtenerResultados()).thenReturn(resultados);

        mockMvc.perform(get("/api/results"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Pop", is(2)))
                .andExpect(jsonPath("$.Rock", is(3)));
    }

    @Test
    public void testGetMusicStyles() throws Exception {
        when(estiloService.getMusicStyles()).thenReturn(Arrays.asList("Pop", "Rock"));

        mockMvc.perform(get("/api/music-styles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]", is("Pop")))
                .andExpect(jsonPath("$[1]", is("Rock")));
    }
}
