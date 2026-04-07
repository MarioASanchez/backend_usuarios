package es.dwes.backend_usuarios.service;

import java.util.List;

import es.dwes.backend_usuarios.DTO.UsuarioEtiquetaDTO;

public interface UsuarioEtiquetaService {
    void sincronizarEtiquetas(UsuarioEtiquetaDTO dto);
    public List<UsuarioEtiquetaDTO> obtenerEtiquetasUsuarios(Long id);
}
