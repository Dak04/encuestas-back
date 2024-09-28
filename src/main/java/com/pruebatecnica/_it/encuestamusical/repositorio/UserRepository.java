package com.pruebatecnica._it.encuestamusical.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.pruebatecnica._it.encuestamusical.dominio.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

}