package es.dwes.backend_usuarios.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetodoPagoDTO {
    private Long idUsuario;
    private String nombre;
    private boolean activo;
    
}
