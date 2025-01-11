package com.example.miprimermicroservicio;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

// Anotación Spring MVC que indica que esta clase es un controlador web
@RestController
public class SaludoController {

    /*Anotación que asocia este metodo a una petición GET
    para clientes que admitan texto plano como respuesta*/

    @GetMapping(value = "saludo", produces = MediaType.TEXT_PLAIN_VALUE)
    public String saludo() {
        return "Hola, este es mi primer microservicio";
    }

    // Nuevo recurso, que ante una petición GET, generará un mensaje de saludo personalizado
    @GetMapping(value="saludo/{name}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String saludo(@PathVariable("name") String nombre) {
        return "Bienvenido a Spring Boot, querido: " + nombre;
    }

    /*Recogida de datos a través de parámetros stringquery.
    No se indica la variable en la dirección, se pasa por parámetro*/
    @GetMapping(value="saludopersonal", produces = MediaType.TEXT_PLAIN_VALUE)
    public String saludo(@RequestParam("name") String nombre, @RequestParam("edad") int edad) {
        return "Bienvenido " + nombre + ", eres un dunedain de " + edad + " años";
    }

}
