package com.contactos.microservicio_contactos.controller;

import com.contactos.microservicio_contactos.model.Contacto;
import com.contactos.microservicio_contactos.service.AgendaService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") //permite recibir peticiones desde cualquier origen
@RestController
public class ContactosController {

    private final AgendaService service;

    public ContactosController(AgendaService service) {
        this.service = service;
    }

    @GetMapping(value="contactos",produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Contacto> recuperarContactos() {
        return service.recuperarContactos();
    }

    @GetMapping(value="contactos/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
    public Contacto recuperarContactos(@PathVariable("id") int id) {
        return service.buscarContacto(id);
    }

    @PostMapping(value="contactos",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.TEXT_PLAIN_VALUE)
    public String guardarContacto(@RequestBody Contacto contacto) {
        return String.valueOf(service.agregarContacto(contacto));
    }

    @PutMapping(value="contactos",consumes=MediaType.APPLICATION_JSON_VALUE)
    public void actualizarContacto(@RequestBody Contacto contacto) {
        service.actualizarContacto(contacto);
    }

    @DeleteMapping(value="contactos/{id}")
    public void eliminarPorId(@PathVariable("id") int idContacto) {
        service.eliminarContacto(idContacto);
    }

}
