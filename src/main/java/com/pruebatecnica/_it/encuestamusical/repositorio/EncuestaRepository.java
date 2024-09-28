package com.pruebatecnica._it.encuestamusical.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pruebatecnica._it.encuestamusical.dominio.Encuesta;

public interface  EncuestaRepository extends CrudRepository<Encuesta, Integer>{

    Encuesta findByEmail(String email);

        // Consulta para agrupar por 'respuesta' y contar la cantidad
    @Query("SELECT e.respuesta, COUNT(e.respuesta) FROM Encuesta e GROUP BY e.respuesta")
    List<Object[]> countByRespuesta();
    
}
