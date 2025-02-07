package com.contactos.microservicio_contactos.service;

import com.contactos.microservicio_contactos.dao.AgendaDao;
import com.contactos.microservicio_contactos.model.Contacto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaServiceImpl implements AgendaService {

    private final AgendaDao dao;

    // Constructor para inyectar la dependencia
    //@Autowired
    public AgendaServiceImpl(AgendaDao dao) {   //Para evitar el uso de @Autowired y hacer el objeto dao inmutable
        this.dao = dao;
    }

    @Override
    public boolean agregarContacto(Contacto contacto) {
        //a�ade el contacto si no existe
        if(dao.recuperarContacto(contacto.getIdContacto())==null) {
            dao.agregarContacto(contacto);
            return true;
        }
        return false;
    }

    @Override
    public List<Contacto> recuperarContactos() {
        /** Para simular un retraso en la respuesta al cliente al implementar la llamada asincrona
         * try {
         *    Thread.sleep(10000);
         *    } catch (InterruptedException e) {
         *    e.printStackTrace();
         *    }
         */

        return dao.devolverContactos();
    }

    @Override
    public void actualizarContacto(Contacto contacto) {
        //elimina el contacto si existe
        if(dao.recuperarContacto(contacto.getIdContacto())!=null) {
            dao.actualizarContacto(contacto);
        }
    }

    @Override
    public boolean eliminarContacto(int idcontacto) {
        if(dao.recuperarContacto(idcontacto)!=null) {
            dao.eliminarContacto(idcontacto);
            return true;
        }
        return false;
    }

    @Override
    public Contacto buscarContacto(int idcontacto) {
        return dao.recuperarContacto(idcontacto);
    }
}
