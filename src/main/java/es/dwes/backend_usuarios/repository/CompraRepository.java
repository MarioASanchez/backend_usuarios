package es.dwes.backend_usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.dwes.backend_usuarios.entity.CompraEntrada;

public interface CompraRepository extends JpaRepository<CompraEntrada, Long> {
    java.util.List<CompraEntrada> findByIdEvento(Long idEvento);
}
