package es.dwes.backend_usuarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.dwes.backend_usuarios.entity.CompraEntrada;

public interface CompraRepository extends JpaRepository<CompraEntrada, Long> {
    List<CompraEntrada> findByIdEvento(Long idEvento);
    List<CompraEntrada> findByUsuario_Id(Long idUsuario);
}
