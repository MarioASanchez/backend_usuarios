package es.dwes.backend_usuarios.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import es.dwes.backend_usuarios.DTO.EtiquetaDTO;
import es.dwes.backend_usuarios.entity.Etiqueta;

@Component
public class EtiquetaMapper {

    private final ModelMapper mapper = new ModelMapper();

    public EtiquetaDTO toDTO(Etiqueta etiqueta) {
        return mapper.map(etiqueta, EtiquetaDTO.class);
    }

    public Etiqueta toEntity(EtiquetaDTO etiquetaDTO) {
        return mapper.map(etiquetaDTO, Etiqueta.class);
    }
}
