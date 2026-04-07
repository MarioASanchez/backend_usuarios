package es.dwes.backend_usuarios.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/preferencias/{id}")
    public ResponseEntity<UsuarioEtiquetaDTO> obtenerPreferencias(@PathVariable Long id) {
        // Obtenemos la lista y, si no está vacía, devolvemos el primer (y único) DTO
        List<UsuarioEtiquetaDTO> resultado = servicio.obtenerEtiquetasUsuarios(id);

        if (resultado.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(resultado.get(0));
    }
}
