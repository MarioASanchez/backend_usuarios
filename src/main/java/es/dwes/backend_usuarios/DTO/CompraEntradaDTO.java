package es.dwes.backend_usuarios.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraEntradaDTO {
    private Long idUsuario;
    private LocalDate fechaCompra;
    private Long cantidad;
}
