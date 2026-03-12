package es.dwes.backend_usuarios.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
            .csrf(csrf -> csrf.disable()) // Deshabilitamos CSRF (necesario para probar POST en APIs)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/registro", "/login").permitAll() // <-- PERMITIR ACCESO PÚBLICO
                .anyRequest().authenticated() // Todo lo demás requiere login
            )
            .httpBasic(Customizer.withDefaults()); // Permite autenticación básica para pruebas
            
        return http.build();
    }    
}
