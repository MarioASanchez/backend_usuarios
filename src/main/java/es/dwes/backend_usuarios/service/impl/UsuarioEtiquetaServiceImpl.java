package es.dwes.backend_usuarios.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import es.dwes.backend_usuarios.DTO.UsuarioEtiquetaDTO;
import es.dwes.backend_usuarios.entity.Usuario;
import es.dwes.backend_usuarios.entity.UsuarioEtiqueta;
import es.dwes.backend_usuarios.repository.UsuarioEtiquetaRepository;
import es.dwes.backend_usuarios.repository.UsuarioRepository;
import es.dwes.backend_usuarios.service.UsuarioEtiquetaService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioEtiquetaServiceImpl implements UsuarioEtiquetaService {

    private final UsuarioEtiquetaRepository repositorio;
    private final UsuarioRepository repositorioUsuarios;


    @Override
    @Transactional
    public void sincronizarEtiquetas(UsuarioEtiquetaDTO dto) {
        // Buscamos el usuario (para asegurarnos de que existe)
        Usuario usuario = this.repositorioUsuarios.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getIdUsuario()));

        // Borramos todas las etiquetas actuales que tenga ese usuario
        this.repositorio.deleteByUsuario_Id(usuario.getId());

        // Si el array de etiquetas no está vacío, creamos y guardamos las nuevas
        for (Long idEt : dto.getIdsEtiquetas()) {
            UsuarioEtiqueta ue = new UsuarioEtiqueta();
            ue.setUsuario(usuario);
            ue.setIdEtiqueta(idEt); // Solo el ID (Long), no el objeto Etiqueta
            this.repositorio.save(ue);
        }
    }

    @Override
    public List<UsuarioEtiquetaDTO> obtenerEtiquetasUsuarios(Long id) {
        // 1. Obtenemos todos los registros de la base de datos para ese usuario
        List<UsuarioEtiqueta> asignaciones = this.repositorio.findByUsuario_Id(id);

        // 2. Extraemos solo los IDs de las etiquetas (idEtiqueta) de esos registros
        List<Long> idsSoloEtiquetas = asignaciones.stream()
                .map(UsuarioEtiqueta::getIdEtiqueta)
                .collect(Collectors.toList());

        // 3. Creamos el DTO de respuesta
        UsuarioEtiquetaDTO dto = new UsuarioEtiquetaDTO();
        dto.setIdUsuario(id);
        dto.setIdsEtiquetas(idsSoloEtiquetas);

        
        return List.of(dto);
    }
}
