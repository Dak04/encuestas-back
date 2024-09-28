package com.pruebatecnica._it.encuestamusical.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testSetAndGetId() {
        Integer id = 1;
        user.setId(id);
        assertEquals(id, user.getId());
    }

    @Test
    public void testSetAndGetName() {
        String name = "Test User";
        user.setName(name);
        assertEquals(name, user.getName());
    }

    @Test
    public void testSetAndGetEmail() {
        String email = "test@example.com";
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testEmptyConstructor() {
        // Test to check if a new User object initializes with null fields
        assertNull(user.getId());
        assertNull(user.getName());
        assertNull(user.getEmail());
    }
}
