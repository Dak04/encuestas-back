package com.pruebatecnica._it.encuestamusical.servicios;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.pruebatecnica._it.encuestamusical.dominio.Estilo;
import com.pruebatecnica._it.encuestamusical.repositorio.EstiloRepository;

class EstiloServiceImplTest {

    @Mock
    private EstiloRepository estiloRepository;

    @InjectMocks
    private EstiloServiceImpl estiloServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void testAdd_NewStyle_Successful() {
        // Dado que no existe un estilo con la descripción
        String descripcion = "Rock";

        when(estiloRepository.findByDescripcion(descripcion)).thenReturn(null); // Mock vacío
        when(estiloRepository.save(any(Estilo.class))).thenReturn(null); // Mock del save

        // Llamada al método
        String result = estiloServiceImpl.add(descripcion);

        // Verificaciones
        assertEquals("Estilo guardado", result);
        verify(estiloRepository, times(1)).save(any(Estilo.class)); // Aseguramos que el save fue llamado
    }

    @Test
    void testAdd_ExistingStyle() {
        // Dado que ya existe un estilo con la descripción
        String descripcion = "Pop";
        Estilo existingEstilo = new Estilo();
        existingEstilo.setDescripcion(descripcion);

        when(estiloRepository.findByDescripcion(descripcion)).thenReturn(existingEstilo); // Estilo encontrado

        // Llamada al método
        String result = estiloServiceImpl.add(descripcion);

        // Verificaciones
        assertEquals("Estilo repetido", result);
        verify(estiloRepository, never()).save(any(Estilo.class)); // Aseguramos que no se guarda de nuevo
    }

    @Test
    void testGetMusicStyles() {
        // Dado que la base de datos tiene varios estilos
        List<String> estilos = Arrays.asList("Rock", "Pop", "Jazz");

        when(estiloRepository.getAllStyles()).thenReturn(estilos); // Mock de la lista de estilos

        // Llamada al método
        List<String> result = estiloServiceImpl.getMusicStyles();

        // Verificaciones
        assertNotNull(result);
        assertEquals(estilos.size(), result.size());
        assertIterableEquals(estilos, result);
        verify(estiloRepository, times(1)).getAllStyles();
    }
}
