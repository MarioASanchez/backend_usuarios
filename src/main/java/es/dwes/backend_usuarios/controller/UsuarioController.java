package es.dwes.backend_usuarios.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.dwes.backend_usuarios.DTO.UsuarioDTO;
import es.dwes.backend_usuarios.config.JwtService;
import es.dwes.backend_usuarios.service.UsuarioService;
import lombok.AllArgsConstructor;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping
@AllArgsConstructor
public class UsuarioController {
    private final UsuarioService servicio;
    private final JwtService jwtServicio;


    @PostMapping("/registro")
    public ResponseEntity<UsuarioDTO> registrarUsuario(@RequestBody UsuarioDTO usuario){
        return ResponseEntity.ok(this.servicio.registrarUsuario(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody UsuarioDTO usuarioDTO){
        try {
            UsuarioDTO usuario = this.servicio.inicioSesion(usuarioDTO.getEmail(), usuarioDTO.getPassword());
            
            // Generación del Token que se comunica con el front
            String token = jwtServicio.crearToken(usuario);

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("usuario", usuario);
            respuesta.put("token", token);

            return ResponseEntity.ok(respuesta);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
        
    }

    // Por implementar el actualizar y el eliminar, además del actualizar a administrador
}
