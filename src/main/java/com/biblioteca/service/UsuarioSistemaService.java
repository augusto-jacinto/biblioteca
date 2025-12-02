package com.biblioteca.service;

import com.biblioteca.model.UsuarioSistema;
import com.biblioteca.repository.UsuarioSistemaRepository;
import java.util.List;

public class UsuarioSistemaService {
    private UsuarioSistemaRepository usuarioRepository;

    public UsuarioSistemaService() {
        this.usuarioRepository = new UsuarioSistemaRepository();
    }

    public void registrarUsuario(UsuarioSistema usuario) {
        usuarioRepository.registrar(usuario);
    }

    public UsuarioSistema buscarUsuario(int id) {
        return usuarioRepository.buscar(id);
    }

    public List<UsuarioSistema> listarUsuarios() {
        return usuarioRepository.listar();
    }

    public void actualizarUsuario(UsuarioSistema usuario) {
        usuarioRepository.actualizar(usuario);
    }

    public void eliminarUsuario(int id) {
        usuarioRepository.eliminar(id);
    }

    public UsuarioSistema buscarPorUsuario(String usuario) {
        return usuarioRepository.buscarPorUsuario(usuario);
    }

    public boolean validarCredenciales(String usuario, String contrasena) {
        UsuarioSistema user = usuarioRepository.buscarPorUsuario(usuario);
        return user != null && user.getContrasenaSystem().equals(contrasena);
    }
}