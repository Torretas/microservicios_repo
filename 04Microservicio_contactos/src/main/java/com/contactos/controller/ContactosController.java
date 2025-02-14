package com.contactos.controller;

import com.contactos.model.Contacto;
import com.contactos.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", exposedHeaders = "total") //permite recibir peticiones desde cualquier origen
// Anotación que indica que esta clase es un controlador REST
@RestController
public class ContactosController {

    // Inyección de dependencia del servicio AgendaService
    @Autowired
    AgendaService service;

    // Metodo para recuperar todos los contactos
    @GetMapping(value="contactos", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contacto>> recuperarContactos() {
        // Llamada al servicio para obtener la lista de contactos
        List<Contacto> contactos = service.recuperarContactos();

        // Creación de un objeto HttpHeaders para añadir encabezados personalizados
        HttpHeaders headers = new HttpHeaders();

        // Añadir un encabezado con el número total de contactos
        headers.add("total", String.valueOf(contactos.size()));

        // Devolver una respuesta HTTP con la lista de contactos, los encabezados y un estado HTTP 200 (OK)
        return new ResponseEntity<>(contactos, headers, HttpStatus.OK);
    }

    // Metodo para recuperar un contacto por su ID
    @GetMapping(value="contactos/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contacto> recuperarContactos(@PathVariable("id") int id) {
        // Llamada al servicio para buscar un contacto por su ID
        // Devolver una respuesta HTTP con el contacto y un estado HTTP 200 (OK)
        return new ResponseEntity<>(service.buscarContacto(id), HttpStatus.OK);
    }

    // Metodo para guardar un nuevo contacto
    @PostMapping(value="contactos", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> guardarContacto(@RequestBody Contacto contacto) {
        // Llamada al servicio para agregar un nuevo contacto
        if (service.agregarContacto(contacto)) {
            // Devolver una respuesta HTTP con un estado HTTP 200 (OK) si el contacto se agrega correctamente
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            // Devolver una respuesta HTTP con un estado HTTP 409 (Conflict) si hay un problema al agregar el contacto
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    // Metodo para actualizar un contacto existente
    @PutMapping(value="contactos", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> actualizarContacto(@RequestBody Contacto contacto) {
        // Llamada al servicio para actualizar un contacto existente
        service.actualizarContacto(contacto);

        // Devolver una respuesta HTTP con un estado HTTP 200 (OK)
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Metodo para eliminar un contacto por su ID
    @DeleteMapping(value="contactos/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable("id") int idContacto) {
        // Llamada al servicio para eliminar un contacto por su ID
        service.eliminarContacto(idContacto);

        // Devolver una respuesta HTTP con un estado HTTP 200 (OK)
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

