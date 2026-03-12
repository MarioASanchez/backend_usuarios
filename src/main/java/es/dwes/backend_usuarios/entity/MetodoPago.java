package es.dwes.backend_usuarios.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="metodo_pago")
@Data
public class MetodoPago {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="nombre")
    private String nombre;

    @Column(name="fechaRegistro")
    private LocalDate fechaRegistro;

    @Column(name="fechaExpiracion")
    private LocalDate fechaExpiracion;

    @Column(name="numTarjeta", nullable=true)
    private Long numTarjeta;

    @Column(name="paypalEmail", nullable=true)
    private String paypalEmail;

    @Column(name="activo")
    private boolean activo;

    @ManyToOne
    @JoinColumn(name="idUsuario", nullable=true)
    private Usuario usuario;
}
