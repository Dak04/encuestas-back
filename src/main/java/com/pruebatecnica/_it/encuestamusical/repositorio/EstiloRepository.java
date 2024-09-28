package com.pruebatecnica._it.encuestamusical.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pruebatecnica._it.encuestamusical.dominio.Estilo;

public interface  EstiloRepository extends CrudRepository<Estilo, Integer>{
    Estilo findByDescripcion(String descripcion);

    @Query("SELECT e.descripcion FROM Estilo e")
    List<String> getAllStyles();
    
}
