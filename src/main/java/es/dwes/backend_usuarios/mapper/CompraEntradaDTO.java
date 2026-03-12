package es.dwes.backend_usuarios.mapper;

import org.modelmapper.ModelMapper;

import es.dwes.backend_usuarios.entity.CompraEntrada;

public class CompraEntradaDTO {
    private final ModelMapper mapper = new ModelMapper();

    public CompraEntradaDTO toDTO(CompraEntrada compraEntrada) {
        return mapper.map(compraEntrada, CompraEntradaDTO.class);
    }

    public CompraEntrada toEntity(CompraEntradaDTO compraEntradaDTO) {
        return mapper.map(compraEntradaDTO, CompraEntrada.class);
    }
}
