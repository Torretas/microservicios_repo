package com.example.mapeodeobjetos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.controlador"})
public class MapeodeObjetosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapeodeObjetosApplication.class, args);
    }

}
