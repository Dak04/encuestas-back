package com.pruebatecnica._it.encuestamusical.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Encuesta {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String email;

    private String respuesta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email){
        this.email = email;
    }

    public String getRespuesta(){ 
        return respuesta;
    }

    public void setRespuesta(String respuesta){
        this.respuesta = respuesta;
    }

    
}
