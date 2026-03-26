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
@Table(name="compra_entrada")
@Data
public class CompraEntrada {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuario;

    @Column(name="fechaCompra")
    private LocalDate fechaCompra;

    @Column(name="idEvento")
    private Long idEvento;

    @Column(name="precio")
    private Double precio;

    @Column(name="cantidad")
    private Long cantidad;

    @Column(name="asientos")
    private String asientos;
}
