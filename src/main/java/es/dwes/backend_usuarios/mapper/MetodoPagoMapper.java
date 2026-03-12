package es.dwes.backend_usuarios.mapper;

import org.modelmapper.ModelMapper;

import es.dwes.backend_usuarios.DTO.MetodoPagoDTO;
import es.dwes.backend_usuarios.entity.MetodoPago;

public class MetodoPagoMapper {
    private final ModelMapper mapper = new ModelMapper();

    public MetodoPagoDTO toDTO(MetodoPago metodoPago) {
        return mapper.map(metodoPago, MetodoPagoDTO.class);
    }

    public MetodoPago toEntity(MetodoPagoDTO metodoPagoDTO) {
        return mapper.map(metodoPagoDTO, MetodoPago.class);
    }
}
