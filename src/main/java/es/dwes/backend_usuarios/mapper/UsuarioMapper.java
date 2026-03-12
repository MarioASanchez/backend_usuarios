package es.dwes.backend_usuarios.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import es.dwes.backend_usuarios.DTO.UsuarioDTO;
import es.dwes.backend_usuarios.entity.Usuario;

@Component
public class UsuarioMapper {

    private final ModelMapper mapper = new ModelMapper();

    public UsuarioDTO toDTO(Usuario usuario) {
        return mapper.map(usuario, UsuarioDTO.class);
    }

    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        return mapper.map(usuarioDTO, Usuario.class);
    }
}
