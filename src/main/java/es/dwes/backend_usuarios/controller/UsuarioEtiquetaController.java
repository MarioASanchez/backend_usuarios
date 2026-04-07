package es.dwes.backend_usuarios.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.dwes.backend_usuarios.DTO.UsuarioEtiquetaDTO;
import es.dwes.backend_usuarios.service.UsuarioEtiquetaService;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping
@AllArgsConstructor
public class UsuarioEtiquetaController {

    private final UsuarioEtiquetaService servicio;

    @PostMapping("/preferencias")
    public ResponseEntity<String> guardarEtiquetas(@RequestBody UsuarioEtiquetaDTO dto) {
        try {

            this.servicio.sincronizarEtiquetas(dto);
            return ResponseEntity.ok("OK");
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
