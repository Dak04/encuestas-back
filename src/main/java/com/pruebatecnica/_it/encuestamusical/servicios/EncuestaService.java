package com.pruebatecnica._it.encuestamusical.servicios;

import java.util.Map;

public interface  EncuestaService {

    String responder(String email, String respuesta);

    Map<String, Long> obtenerResultados();
    
}
