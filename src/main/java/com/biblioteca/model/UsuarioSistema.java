package com.biblioteca.model;

import java.time.LocalDate;

public class UsuarioSistema extends Persona {
    private int idUsuario;
    private String usuario;
    private String contrasenaSystem;

    public UsuarioSistema() {
        super();
    }

    public UsuarioSistema(String nombres, String apellidos, LocalDate fechaNacimiento,
                          String numeroDocumento, String direccion, String nacionalidad,
                          String usuario, String contrasenaSystem) {
        super(nombres, apellidos, fechaNacimiento, numeroDocumento, direccion, nacionalidad);
        this.usuario = usuario;
        this.contrasenaSystem = contrasenaSystem;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenaSystem() {
        return contrasenaSystem;
    }

    public void setContrasenaSystem(String contrasenaSystem) {
        this.contrasenaSystem = contrasenaSystem;
    }

    @Override
    public String toString() {
        return "UsuarioSistema{" +
                "idUsuario=" + idUsuario +
                ", usuario='" + usuario + '\'' +
                super.toString() +
                '}';
    }
}