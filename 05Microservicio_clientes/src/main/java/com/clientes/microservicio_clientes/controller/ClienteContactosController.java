package com.clientes.microservicio_clientes.controller;

import com.clientes.microservicio_clientes.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class ClienteContactosController {
    String urlBase="http://localhost:8080";

    //@Autowired
    //RestTemplate template;

    private final RestTemplate template;

    // Constructor para inyectar la dependencia de RestTemplate que se necesita para realizar las peticiones al servicio remoto de contactos
    public ClienteContactosController(RestTemplate template) {   //Para evitar el uso de @Autowired y hacer el objeto template inmutable
        this.template = template;
    }

    /* Controlador que realiza dos operaciones principales
     *   1. Enviar una nueva persona mediante una petición POST
     *   2. Recupear una lista de personas mediante una petición GET
     *  La respuesta no es personalizada, se devuelve la lista de personas tal cual se recibe del servicio remoto

    @GetMapping(value="/personas/{nombre}/{email}/{edad}", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> altaNueva(@PathVariable("nombre") String nombre,
                                   @PathVariable("email") String email,
                                   @PathVariable("edad") int edad) {

        Persona persona = new Persona(nombre,email,edad);
        //realiza dos llamadas al servicio remoto, la primera para agregar una nueva persona
        //y la segunda para recuperar las personas existentes
        template.postForLocation(urlBase+"/contactos", persona);
        Persona[] personas = template.getForObject(urlBase + "/contactos", Persona[].class);
        return Arrays.asList(personas);
    }
    @GetMapping(value="/personas/{edad1}/{edad2}", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> buscarEdades(@PathVariable("edad1") int edad1, @PathVariable("edad2") int edad2) {

        //realiza una llamada al servicio remoto para recuperar las personas existentes y filtra por edad los resultados obtenidos
        Persona[] personas=template.getForObject(urlBase+"/contactos", Persona[].class);
        return Arrays.stream(personas)
                .filter( p -> p.getEdad() >= edad1 && p.getEdad() <= edad2)
                .collect(Collectors.toList());
    }

     */
    // Ejemplo de respuesta personalizada con el uso de ResponseEntity y HttpHeaders para agregar encabezados personalizados en la respuesta

    String url = "http://localhost:8080"; // URL base del servicio de contactos

    // Metodo para dar de alta una persona
    @GetMapping(value="/personas/{nombre}/{email}/{edad}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Persona>> altaPersona(
            @PathVariable("nombre") String nombre,
            @PathVariable("email") String email,
            @PathVariable("edad") int edad) {

        // Crear una nueva instancia de Persona con los datos proporcionados
        Persona persona = new Persona(nombre, email, edad);

        try {

            // Realizar una solicitud POST para agregar la persona al servicio de contactos
            ResponseEntity<Void> respuesta = template.postForEntity(url + "/contactos", persona, Void.class);

            // Realizar una solicitud GET para obtener todos los contactos
            ResponseEntity<Persona[]> personas = template.getForEntity(url + "/contactos", Persona[].class);

            // Obtener los encabezados de la respuesta
            HttpHeaders headers = personas.getHeaders();
            System.out.println("GET Response Headers: " + headers);

            // Obtener el valor del encabezado "total" y convertirlo a entero
            /*se está utilizando un encabezado personalizado total para enviar información adicional entre microservicios. Esto se utiliza para indicar el número total de contactos que se han recuperado o agregado.*/
            int total = Integer.parseInt(headers.get("total").get(0));

            // Si no se agregaron contactos, devolver null
            if (total == 0) {
                return null;
            }

            // Devolver la lista de personas obtenidas
            // Arrays.asList(Objects.requireNonNull(personas.getBody()));
            return new ResponseEntity<>(Arrays.asList(Objects.requireNonNull(personas.getBody())), headers, HttpStatus.OK);


        } catch (HttpClientErrorException err) {
            // Manejar errores de cliente HTTP imprimiendo la traza de la excepción y devolviendo null
            err.printStackTrace();
            return null;
        }
    }

    // Metodo para buscar personas por rango de edades
    @GetMapping(value="/personas/{edad1}/{edad2}", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> buscarEdades(
            @PathVariable("edad1") int edad1,
            @PathVariable("edad2") int edad2) {

        // Realizar una solicitud GET para obtener todos los contactos
        ResponseEntity<Persona[]> personas = template.getForEntity(url + "/contactos", Persona[].class);

        // Filtrar las personas cuya edad esté en el rango especificado y devolver la lista resultante
        return Arrays.stream(personas.getBody())
                .filter(p -> p.getEdad() >= edad1 && p.getEdad() <= edad2)
                .collect(Collectors.toList());
    }


}
