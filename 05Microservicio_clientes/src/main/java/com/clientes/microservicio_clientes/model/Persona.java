package com.clientes.microservicio_clientes.model;

public class Persona {
    private String nombre;
    private String email;
    private int edad;
    public Persona(String nombre, String email, int edad) {
        super();
        this.nombre = nombre;
        this.email = email;
        this.edad = edad;
    }
    public Persona() {
        super();
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
}