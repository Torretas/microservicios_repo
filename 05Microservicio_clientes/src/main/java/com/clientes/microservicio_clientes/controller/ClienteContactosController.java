package com.clientes.microservicio_clientes.controller;

import com.clientes.microservicio_clientes.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClienteContactosController {
    String urlBase="http://localhost:8080";

    //@Autowired
    //RestTemplate template;

    private final RestTemplate template;

    // Constructor para inyectar la dependencia de RestTemplate que se necesita para realizar las peticiones al servicio remoto de contactos
    public ClienteContactosController(RestTemplate template) {   //Para evitar el uso de @Autowired y hacer el objeto template inmutable
        this.template = template;
    }

    @GetMapping(value="/personas/{nombre}/{email}/{edad}", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> altaNueva(@PathVariable("nombre") String nombre,
                                   @PathVariable("email") String email,
                                   @PathVariable("edad") int edad) {

        Persona persona = new Persona(nombre,email,edad);
        //realiza dos llamadas al servicio remoto, la primera para agregar una nueva persona
        //y la segunda para recuperar las personas existentes
        template.postForLocation(urlBase+"/contactos", persona);
        Persona[] personas = template.getForObject(urlBase + "/contactos", Persona[].class);
        return Arrays.asList(personas);
    }
    @GetMapping(value="/personas/{edad1}/{edad2}", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> buscarEdades(@PathVariable("edad1") int edad1, @PathVariable("edad2") int edad2) {

        //realiza una llamada al servicio remoto para recuperar las personas existentes y filtra por edad los resultados obtenidos
        Persona[] personas=template.getForObject(urlBase+"/contactos", Persona[].class);
        return Arrays.stream(personas)
                .filter( p -> p.getEdad() >= edad1 && p.getEdad() <= edad2)
                .collect(Collectors.toList());
    }

}
