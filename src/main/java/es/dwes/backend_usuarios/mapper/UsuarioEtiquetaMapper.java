package es.dwes.backend_usuarios.mapper;

import org.modelmapper.ModelMapper;

import es.dwes.backend_usuarios.DTO.UsuarioEtiquetaDTO;
import es.dwes.backend_usuarios.entity.UsuarioEtiqueta;

public class UsuarioEtiquetaMapper {

    private final ModelMapper mapper = new ModelMapper();

    public UsuarioEtiquetaDTO toDTO(UsuarioEtiqueta usuarioEtiqueta) {
        return mapper.map(usuarioEtiqueta, UsuarioEtiquetaDTO.class);
    }

    public UsuarioEtiqueta toEntity(UsuarioEtiquetaDTO usuarioEtiquetaDTO) {
        return mapper.map(usuarioEtiquetaDTO, UsuarioEtiqueta.class);
    }
}
