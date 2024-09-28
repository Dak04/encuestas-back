package com.pruebatecnica._it.encuestamusical.controlador;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pruebatecnica._it.encuestamusical.dominio.User;
import com.pruebatecnica._it.encuestamusical.servicios.EncuestaService;
import com.pruebatecnica._it.encuestamusical.servicios.EstiloService;
import com.pruebatecnica._it.encuestamusical.servicios.UserService;

@RestController 
@RequestMapping(path="/api")
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private EncuestaService encuestaService;

    @Autowired
    private EstiloService estiloService;

    @PostMapping(path="/add") 
    public @ResponseBody String addNewUser (@RequestParam String name
        , @RequestParam String email) {

            return userService.add(name, email);
    }

    @GetMapping(path="/all")
    public @ResponseBody  Iterable<User>getAllUsers() {
        return userService.getAllUsers();
    }

    @CrossOrigin(origins = "http://localhost:4200") 
    @PostMapping(path="/guardarencuesta")
    public @ResponseBody String guardarRespuesta (@RequestParam String respuesta
        , @RequestParam String email) {
            return encuestaService.responder(email, respuesta);
    }

    @PostMapping(path="/guardarestilo") 
    public @ResponseBody String guardarEstilo (@RequestParam String descripcion) {

            return estiloService.add(descripcion);
    }

    @CrossOrigin(origins = "http://localhost:4200") 
    @GetMapping("/results")
    public Map<String, Long> obtenerResultadosEncuesta() {
        return encuestaService.obtenerResultados();
    }

    @CrossOrigin(origins = "http://localhost:4200") 
    @GetMapping("/music-styles")
    public List<String> getMusicStyles(){
    
        return estiloService.getMusicStyles();
    }
}