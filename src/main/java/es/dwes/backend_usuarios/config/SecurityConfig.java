package es.dwes.backend_usuarios.config;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // Utilizado para hashear las contraseñas
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Habilitamos CORS para que el frontend (puerto 8000) pueda hacer peticiones al backend (8083)
            .cors(Customizer.withDefaults()) 
            // Deshabilitamos CSRF para simplificar las peticiones POST desde React
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Permitimos acceso público a registro, login y toda la API de compras para desarrollo
                .requestMatchers("/registro", "/login", "/api/**", "/permisos", "/cambiarDatos/**", "/eliminarCuenta/**").permitAll() 
                .anyRequest().authenticated()        
                
            )
            .httpBasic(Customizer.withDefaults());
            
        return http.build();
    }

    // Configuración detallada de CORS para evitar el error "Failed to fetch"
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Permitimos cualquier origen (*) para evitar problemas de conectividad en local
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
