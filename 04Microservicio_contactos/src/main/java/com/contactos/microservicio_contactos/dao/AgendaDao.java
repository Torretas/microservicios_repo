package com.contactos.microservicio_contactos.dao;

import com.contactos.microservicio_contactos.model.Contacto;

import java.util.List;

public interface AgendaDao {

    void agregarContacto(Contacto contacto);

    Contacto recuperarContacto(String email);

    void eliminarContacto(String email);

    List<Contacto> devolverContactos();

    void eliminarContacto(int idContacto);

    Contacto recuperarContacto(int idContacto);

    void actualizarContacto(Contacto contacto);
}
