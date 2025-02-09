package com.contactos.microservicio_contactos.service;

import com.contactos.microservicio_contactos.model.Contacto;

import java.util.List;

public interface AgendaService {
    boolean agregarContacto(Contacto contacto);
    List<Contacto> recuperarContactos();
    void actualizarContacto(Contacto contacto);
    boolean eliminarContacto(int idcontacto);
    Contacto buscarContacto(int idcontacto);
}
