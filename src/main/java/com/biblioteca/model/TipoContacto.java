package com.biblioteca.model;

public class TipoContacto {
    private int idTipoContacto;
    private String nombre;
    private boolean estado;

    public TipoContacto() {}

    public TipoContacto(String nombre) {
        this.nombre = nombre;
        this.estado = true;
    }

    public int getIdTipoContacto() {
        return idTipoContacto;
    }

    public void setIdTipoContacto(int idTipoContacto) {
        this.idTipoContacto = idTipoContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "TipoContacto{" +
                "idTipoContacto=" + idTipoContacto +
                ", nombre='" + nombre + '\'' +
                ", estado=" + estado +
                '}';
    }
}