package com.contactos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration  // Configuración de seguridad. Indica que es una clase de configuración de Spring.
@EnableWebSecurity // Habilita la seguridad web. Habilita las configuraciones de seguridad predeterminadas de Spring Security.
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager detailsManager() throws Exception{ // Metodo que devuelve un InMemoryUserDetailsManager con los usuarios y sus roles
        List<UserDetails> users=List.of(
                User.withUsername("user1") // Usuario user1 con contraseña user1 y rol USERS
                        .password("{noop}user1") // La contraseña se almacena en texto plano
                        .authorities("USERS") // Rol USERS .roles("USERS") es equivalente
                        .build(), // Construir el usuario
                User.withUsername("admin") // Usuario admin con contraseña admin y roles USERS y ADMIN
                        .password("{noop}admin")
                        .authorities("USERS","ADMIN") // Roles USERS y ADMIN .roles("USERS","ADMIN") es equivalente
                        .build()


        );
        return new InMemoryUserDetailsManager(users);
    }
	/*public DataSource dataSource() {
	DriverManagerDataSource ds=new DriverManagerDataSource();
	ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
	ds.setUrl("jdbc:mysql://localhost:3307/springsecurity?serverTimezone=UTC");
	ds.setUsername("root");
	ds.setPassword("root");
	return ds;
	}*/


    /*la siguiente configuración será para el caso de
     usuarios en una base de datos
    @Bean
    public JdbcUserDetailsManager usersDetailsJdbc() {
        JdbcUserDetailsManager jdbcDetails=new JdbcUserDetailsManager(datasource());
        jdbcDetails.setUsersByUsernameQuery("select username, password, enabled"
               + " from users where username=?");
        jdbcDetails.setAuthoritiesByUsernameQuery("select username, authority "
               + "from authorities where username=?");
        return jdbcDetails;
    }*/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // Metodo que devuelve un SecurityFilterChain con la configuración de seguridad
        http.csrf(csrf -> csrf.disable())  // Deshabilitar CSRF de forma actualizada (lambda), ya que no operamos en un navegador sino en una aplicación (cliente)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/contactos").hasAuthority("ADMIN") // Solo los usuarios con rol ADMIN pueden acceder a la URL /contactos mediante el metodo POST
                        .requestMatchers("/contactos").authenticated() // Solo los usuarios autenticados pueden acceder a la URL /contactos
                        .anyRequest().permitAll()  // Acceso libre a otros recursos (cualquier otra URL)
                )
                .httpBasic(withDefaults());  // Configurar autenticación básica con las opciones predeterminadas

        return http.build();
    }

}
