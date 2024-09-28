package com.pruebatecnica._it.encuestamusical.servicios;

import com.pruebatecnica._it.encuestamusical.dominio.User;

public interface UserService {

    String add(String name , String email);

    Iterable<User>getAllUsers();

    User getUserByEmail(String email);


    
}

