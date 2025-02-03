package com.clientesb.microservicio_clientesb.controller;

import com.clientesb.microservicio_clientesb.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*Controlador que realiza dos operaciones principales
*   1. Enviar una nueva persona mediante una petición POST
*   2. Recupear una lista de personas mediante una petición GET*/

@RestController
public class PersonasController {
    String urlBase="http://localhost:8080";

    @Autowired
    WebClient webClient;

    @GetMapping(value="/personas/{nombre}/{email}/{edad}", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> altaNueva(@PathVariable("nombre") String nombre,
                                   @PathVariable("email") String email,
                                   @PathVariable("edad") int edad) {

        Persona persona = new Persona(nombre, email, edad);

        //WebClient
        webClient
                .post()
                .uri(urlBase+"/contactos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(persona)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        Persona[] personas=webClient
                .get()
                .uri(urlBase + "/contactos")
                .retrieve()
                .bodyToMono(Persona[].class)
                .block();

        return Arrays.asList(personas);
    }
}
