package com.eureka.contactos.controller;

import java.util.Arrays;
import java.util.List;

import com.eureka.contactos.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PersonasController {
    @Autowired
    RestTemplate template;

    String url="http://microservicio-contactos-eureka";
    @GetMapping(value="/personas/{nombre}/{email}/{edad}", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> altaPersona(@PathVariable("nombre") String nombre,
                                     @PathVariable("email") String email,
                                     @PathVariable("edad") int edad){
        Persona persona=new Persona(nombre,email,edad);
        template.postForLocation(url+"/contactos", persona);
        Persona[] personas=template.getForObject(url+"/contactos", Persona[].class);

        return Arrays.asList(personas);
    }
}
