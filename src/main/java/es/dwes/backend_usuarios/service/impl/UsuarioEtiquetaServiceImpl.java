package es.dwes.backend_usuarios.service.impl;

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
}
