package com.pruebatecnica._it.encuestamusical.servicios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebatecnica._it.encuestamusical.dominio.Encuesta;
import com.pruebatecnica._it.encuestamusical.dominio.User;
import com.pruebatecnica._it.encuestamusical.repositorio.EncuestaRepository;

@Service
public class EncuestaServiceImpl implements EncuestaService{

    @Autowired
    private EncuestaRepository encuestaRepository;

    @Autowired
    private UserService userService;

    @Override
    public String responder(String email, String respuesta){
        User user = userService.getUserByEmail(email);

        if(user == null){
            userService.add(email, email);
        }

        Encuesta encuesta = new Encuesta();
        encuesta.setemail(email);
        encuesta.setRespuesta(respuesta);

        Encuesta e = encuestaRepository.findByEmail(email);
        if(e !=null){
            return "Correo con encuesta respondida";
        }else{
            encuestaRepository.save(encuesta);
            return "Encuesta guardada";
        }
    }

    @Override
    public Map<String, Long> obtenerResultados() {
        List<Object[]> resultados = encuestaRepository.countByRespuesta();

        Map<String, Long> mapaResultados = new HashMap<>();
        for (Object[] resultado : resultados) {
            String respuesta = (String) resultado[0];
            Long cantidad = (Long) resultado[1];
            mapaResultados.put(respuesta, cantidad);
        }
        return mapaResultados;
    }

    
}
