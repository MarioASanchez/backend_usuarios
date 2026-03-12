package es.dwes.backend_usuarios.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="usuario")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombreUsuario", nullable=false, unique=true)
    private String nombreUsuario;

    @Column(name="nombre", nullable=false)
    private String nombre;

    @Column(name="apellidos", nullable=false)
    private String apellidos;

    @Column(name="email", nullable=false, unique=true)
    private String email;

    @JsonIgnore
    @Column(name="password", length=60)
    private String password;

    @Column(name="direccion", nullable=true)
    private String direccion;

    @Column(name="admin")
    private Boolean admin;

    @Column(name="puntosAcumulados", nullable=true)
    private Integer puntosAcumulados;

    @OneToMany(mappedBy="usuario")
    private List<MetodoPago> metodoPago;

    @OneToMany(mappedBy="usuario")
    private List<CompraEntrada> compraEntrada;



}
