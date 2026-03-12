package es.dwes.backend_usuarios.service;

import es.dwes.backend_usuarios.DTO.UsuarioDTO;

public interface UsuarioService {
    // Registrar un usuario
    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO);
    // Iniciar Sesión
    public UsuarioDTO inicioSesion(String email, String password);
    // Modificar permisos de administrador
    public UsuarioDTO actualizarAdmin(Long id) throws Exception;
    // Modificar datos
    public UsuarioDTO actualizarDatos(Long id, UsuarioDTO dto);
    // Eliminar cuenta
    public void eliminarUsuario(Long id);
}
