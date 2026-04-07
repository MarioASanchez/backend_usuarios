package es.dwes.backend_usuarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.dwes.backend_usuarios.entity.UsuarioEtiqueta;

public interface UsuarioEtiquetaRepository extends JpaRepository<UsuarioEtiqueta, Long>{
    void deleteByUsuario_Id(Long idUsuario);
    List<UsuarioEtiqueta> findByUsuario_Id(Long idUsuario);
}
