package es.dwes.backend_usuarios.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEtiquetaDTO {
    private Long idUsuario;
    private List<Long> idsEtiquetas;
}
