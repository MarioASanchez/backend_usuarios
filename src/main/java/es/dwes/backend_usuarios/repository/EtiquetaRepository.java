package es.dwes.backend_usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.dwes.backend_usuarios.entity.Etiqueta;

public interface EtiquetaRepository extends JpaRepository<Etiqueta, Long>{

}
