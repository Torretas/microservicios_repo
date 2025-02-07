package com.clientes.microservicio_clientes_asincrono.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.clientes.microservicio_clientes_asincrono.model.Persona;
import org.springframework.scheduling.annotation.Async;


public interface AccesoService {

    CompletableFuture<List<Persona>> llamadaServicio(Persona persona);

}
