package com.pruebatecnica._it.encuestamusical.servicios;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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

import com.pruebatecnica._it.encuestamusical.dominio.User;
import com.pruebatecnica._it.encuestamusical.repositorio.UserRepository;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void testAddUser_Successful() {
        // Dado que no existe un usuario con el email
        String name = "John Doe";
        String email = "john.doe@example.com";

        when(userRepository.findByEmail(email)).thenReturn(null); // Mock vacío
        when(userRepository.save(any(User.class))).thenReturn(null); // Mock del save

        // Llamada al método
        String result = userServiceImpl.add(name, email);

        // Verificaciones
        assertEquals("Saved", result);
        verify(userRepository, times(1)).save(any(User.class)); // Aseguramos que el save fue llamado
    }

    @Test
    void testAddUser_ExistingUser() {
        // Dado que ya existe un usuario con el email
        String name = "Jane Doe";
        String email = "jane.doe@example.com";
        User existingUser = new User();
        existingUser.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(existingUser); // Usuario encontrado

        // Llamada al método
        String result = userServiceImpl.add(name, email);

        // Verificaciones
        assertEquals("Usuario existente", result);
        verify(userRepository, never()).save(any(User.class)); // Aseguramos que no se guarda de nuevo
    }

    @Test
    void testGetAllUsers() {
        // Dado que la base de datos tiene 2 usuarios
        User user1 = new User();
        user1.setName("John Doe");
        user1.setEmail("john.doe@example.com");

        User user2 = new User();
        user2.setName("Jane Doe");
        user2.setEmail("jane.doe@example.com");

        List<User> userList = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(userList); // Mock de la lista

        // Llamada al método
        Iterable<User> result = userServiceImpl.getAllUsers();

        // Verificaciones
        assertNotNull(result);
        assertIterableEquals(userList, result);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserByEmail_Found() {
        // Dado que existe un usuario con el email dado
        String email = "john.doe@example.com";
        User user = new User();
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(user); // Mock encontrado

        // Llamada al método
        User result = userServiceImpl.getUserByEmail(email);

        // Verificaciones
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void testGetUserByEmail_NotFound() {
        // Dado que no existe un usuario con el email dado
        String email = "unknown@example.com";

        when(userRepository.findByEmail(email)).thenReturn(null); // Mock no encontrado

        // Llamada al método
        User result = userServiceImpl.getUserByEmail(email);

        // Verificaciones
        assertNull(result);
        verify(userRepository, times(1)).findByEmail(email);
    }
}
