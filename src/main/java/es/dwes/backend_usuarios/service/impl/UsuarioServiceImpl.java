package es.dwes.backend_usuarios.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import es.dwes.backend_usuarios.DTO.UsuarioDTO;
import es.dwes.backend_usuarios.entity.Usuario;
import es.dwes.backend_usuarios.mapper.UsuarioMapper;
import es.dwes.backend_usuarios.repository.UsuarioRepository;
import es.dwes.backend_usuarios.service.UsuarioService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository repositorio;
    private final UsuarioMapper mapper;
    private final BCryptPasswordEncoder codificador;

    @Override
    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = this.mapper.toEntity(usuarioDTO);
        // Como en el registro no vamos a pedir muchos de los campos, los establecemos a cero
        String passwordCodificada = codificador.encode(usuario.getPassword()); 
        usuario.setPassword(passwordCodificada);
        usuario.setPuntosAcumulados(0);
        
        try {
            this.repositorio.save(usuario);
            return this.mapper.toDTO(usuario);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("El usuario ya existe ", e);
        } catch (Exception e ){
            throw new RuntimeException("Error inesperado al registrar el usuario ", e);
        }
        
    }

    @Override
    public UsuarioDTO inicioSesion(String email, String password) {
        Usuario usuario = this.repositorio.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("Usuario o contraseña incorrectos"));

        if(this.codificador.matches(password, usuario.getPassword())){
            return mapper.toDTO(usuario);
        }else{
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }
    }

    @Override
    public UsuarioDTO actualizarAdmin(Long id) throws Exception {
        Optional <Usuario> usuario = this.repositorio.findById(id);

        if(usuario.isPresent()){
            Usuario usuarioObtenido = usuario.get();
                if(usuarioObtenido.getAdmin()){
                    usuarioObtenido.setAdmin(false);
                }else{
                    usuarioObtenido.setAdmin(true);
                }      
            this.repositorio.save(usuarioObtenido);
            return this.mapper.toDTO(usuarioObtenido);
        }

        throw new Exception("No se ha podido actualizar el estatus del usuario");
    }

    @Override
    public List<UsuarioDTO> obtenerUsuarios(){
        return this.repositorio.findAll().stream().map(mapper::toDTO).toList();
    }


    @Override
    public UsuarioDTO actualizarDatos(Long id, UsuarioDTO dto) throws Exception {
        Optional <Usuario> usuario = this.repositorio.findById(id);

        if(usuario.isPresent()){
            Usuario usuarioObtenido = usuario.get();
            usuarioObtenido.setNombre(dto.getNombre());
            usuarioObtenido.setApellidos(dto.getApellidos());
            usuarioObtenido.setNombreUsuario(dto.getNombreUsuario());
            this.repositorio.save(usuarioObtenido);
            return this.mapper.toDTO(usuarioObtenido);
        }

        throw new Exception("No hemos podido actualizar tus datos");
    }

    @Override
    public void eliminarUsuario(Long id) {
       Optional<Usuario> usuario = this.repositorio.findById(id);
       if(usuario.isPresent()){
            this.repositorio.deleteById(id);
       }
    }

}
