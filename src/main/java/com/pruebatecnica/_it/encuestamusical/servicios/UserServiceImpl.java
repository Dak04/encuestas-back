package com.pruebatecnica._it.encuestamusical.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebatecnica._it.encuestamusical.dominio.User;
import com.pruebatecnica._it.encuestamusical.repositorio.UserRepository;

@Service
public class UserServiceImpl implements UserService {
      
    
    @Autowired
    private UserRepository userRepository;


    @Override
    public String add(String name , String email){
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        User u = userRepository.findByEmail(email);
        if( u != null){
            return "Usuario existente";
        }else{
            userRepository.save(n);
            return "Saved";
        }

    }

    @Override
    public Iterable<User>getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    
}
