package com.pruebatecnica._it.encuestamusical.repositorio;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.pruebatecnica._it.encuestamusical.dominio.User;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Rollback(true)
    public void testSaveUser() {
        User user = new User();
        user.setName("Test User");
        user.setEmail("testuser@example.com");

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
        assertEquals(user.getEmail(), savedUser.getEmail());
    }

    @Test
    public void testFindByEmail() {
        // Insert a user in the database first
        User user = new User();
        user.setName("Existing User");
        user.setEmail("existinguser@example.com");
        userRepository.save(user);

        // Now, try to find the user by email
        User foundUser = userRepository.findByEmail("existinguser@example.com");

        assertNotNull(foundUser);
        assertEquals(user.getEmail(), foundUser.getEmail());
    }

    @Test
    public void testFindById() {
        // Insert a user in the database first
        User user = new User();
        user.setName("Another User");
        user.setEmail("anotheruser@example.com");
        User savedUser = userRepository.save(user);

        // Now, try to find the user by ID
        Optional<User> foundUser = userRepository.findById(savedUser.getId());

        assertNotNull(foundUser);
        assertEquals(savedUser.getId(), foundUser.get().getId());
    }
}
