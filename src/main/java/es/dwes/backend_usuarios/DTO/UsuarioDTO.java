package es.dwes.backend_usuarios.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String nombreUsuario;
    private String nombre;
    private String apellidos;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Boolean admin;
    private String email;
    private String direccion;
    private Integer puntosAcumulados;
}
