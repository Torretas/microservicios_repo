package com.example.microservicio_cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.controlador"})
public class Microservicio_cursos {

    public static void main(String[] args) {
        SpringApplication.run(Microservicio_cursos.class, args);
    }

}
