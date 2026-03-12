package es.dwes.backend_usuarios.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEtiquetaDTO {
    private Long idUsuario;
    private Long idEtiqueta;
}
