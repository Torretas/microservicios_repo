package com.contactos.microservicio_contactos.dao;

import com.contactos.microservicio_contactos.model.Contacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AgendaDaoImpl implements AgendaDao{

   @Autowired
    AgendaJpaSpring agenda;

    @Override
    public void agregarContacto(Contacto contacto) {
        agenda.save(contacto);
    }

    @Override
    public void eliminarContacto(String email) {
        agenda.eliminarPorEmail(email);
    }

    @Override
    public List<Contacto> devolverContactos() {
        return agenda.findAll();
    }

    @Override
    public void eliminarContacto(int idContacto) {
        // TODO Auto-generated method stub
    }

    @Override
    public Contacto recuperarContacto(String email) {
        return agenda.findByEmail(email);
    }

    @Override
    public void actualizarContacto(Contacto contacto) {
        // TODO Auto-generated method stub
    }
}
