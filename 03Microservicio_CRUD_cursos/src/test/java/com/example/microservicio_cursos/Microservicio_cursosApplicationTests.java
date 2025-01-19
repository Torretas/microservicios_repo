package com.example.microservicio_cursos;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Microservicio_cursosApplicationTests {

    // Utilizamos el objeto MockMvc para realizar peticiones HTTP a nuestra aplicación
    // y comprobar que las respuestas son las esperadas a través de la consola

    @Autowired // Autowired es una anotación que se utiliza para inyectar dependencias automáticamente en una clase
    MockMvc mock;

    @Test
    @Order(1)
    void testCursos() throws Exception {
        mock.perform(get("/cursos")).andDo(print());
    }

    // Prueba del metodo DELETE
    @Test
    @Order(0)
    void eliminarCurso() throws Exception {
        mock.perform(delete("/curso/2DAM"));
    }

    // POST
    @Test
    @Order(2)
    void testAlta() throws Exception {
        mock.perform(post("/curso")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"2DAM\",\"duracion\":1000,\"horario\":\"mañana\"}"))
                .andDo(print());
    }

    // PUT
    @Test
    @Order(3)
    void testActualizar() throws Exception {
        mock.perform(put("/curso")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nombre\":\"2DAM\",\"horas\":500,\"horario\":\"tarde\"}"))
                .andDo(print());
    }

}
