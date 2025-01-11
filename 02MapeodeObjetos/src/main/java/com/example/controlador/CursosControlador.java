package com.example.controlador;

import com.example.modelo.Curso;
import jakarta.annotation.PostConstruct;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CursosControlador {

    // Atributo del controlador como una lista de cursos
    private List<Curso> cursos;

    /*Anotación que me asegura que justo cuando
    se instancie el controlador se ejecute el metodo init*/
    @PostConstruct
    public void init() {
        // Creamos el arraylist de cursos aunque lo ideal es acceder a estos datos de una bbdd
        cursos = new ArrayList<>();
        cursos.add(new Curso("1DAM", 1000, "mañana"));
        cursos.add(new Curso("2DAM", 1000, "mañana"));
        cursos.add(new Curso("1SMR", 1000, "tarde"));
        cursos.add(new Curso("1DAW", 1000, "tarde"));
        cursos.add(new Curso("1ASIR", 1000, "telemático"));
    }

    // 2º recurso o metodo del ejercicio 4. URL->cursos
    @GetMapping(value="cursos", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Curso> getCursos() {
        return cursos;
    }

    // 1er recurso. Genera un curso. URL->curso
    @GetMapping(value="curso", produces= MediaType.APPLICATION_JSON_VALUE)
    public Curso getCurso() {
        return new Curso("2DAM", 1000, "mañana");

        /* Ya se encargará la librería Jackson de mapear un objeto a un JSON
        Lo hará cuando se reciba una petición GET cuya URL sea "curso" y
        que requiere un tipo de respuesta en formato JSON
         */
    }

    // 3er recurso. Buscador de cursos. Devuelve los cursos de un determinado nombre. URL cursos con variable
    @GetMapping(value="curso/{name}", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Curso> buscarCursos(@PathVariable("name") String nombre) {
        List<Curso> aux = new ArrayList<>();
        for (Curso curso : cursos) {
            if (curso.getNombre().contains(nombre)) {
                aux.add(curso);
            }
        }
        return aux;
    }
}
