package com.pruebatecnica._it.encuestamusical.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebatecnica._it.encuestamusical.dominio.Estilo;
import com.pruebatecnica._it.encuestamusical.repositorio.EstiloRepository;

@Service
public class EstiloServiceImpl implements EstiloService{

    @Autowired
    private EstiloRepository estiloRepository;

    @Override
    public String add(String descripcion){
        Estilo estilo = new Estilo();
        estilo.setDescripcion(descripcion);

        Estilo e = estiloRepository.findByDescripcion(descripcion);
        if(e != null){
            return "Estilo repetido";
        }else{
            estiloRepository.save(estilo);
            return "Estilo guardado";
        }
    }

    @Override
    public List<String> getMusicStyles(){
        return estiloRepository.getAllStyles();
    }
    
}
