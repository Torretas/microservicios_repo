package com.clientes.microservicio_clientes_asincrono.controller;


import com.clientes.microservicio_clientes_asincrono.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.clientes.microservicio_clientes_asincrono.service.AccesoService;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class PersonasController {
    @Autowired
    AccesoService service;

    @GetMapping(value="/personas/{nombre}/{email}/{edad}", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> altaPersona(@PathVariable("nombre") String nombre,
                                     @PathVariable("email") String email,
                                     @PathVariable("edad") int edad){
        Persona persona=new Persona(nombre,email,edad);
        CompletableFuture<List<Persona>> resultado=service.llamadaServicio(persona);
        for(int i=1;i<50;i++) {
            System.out.println("esperando ");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            return resultado.get();
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}