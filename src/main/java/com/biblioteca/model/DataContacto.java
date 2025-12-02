package com.biblioteca.model;

public class DataContacto {
    private int idPersonaContacto;
    private int idPersona;
    private int idTipoContacto;
    private String dato;
    private boolean estado;

    public DataContacto() {}

    public DataContacto(int idPersona, int idTipoContacto, String dato) {
        this.idPersona = idPersona;
        this.idTipoContacto = idTipoContacto;
        this.dato = dato;
        this.estado = true;
    }

    public int getIdPersonaContacto() {
        return idPersonaContacto;
    }

    public void setIdPersonaContacto(int idPersonaContacto) {
        this.idPersonaContacto = idPersonaContacto;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdTipoContacto() {
        return idTipoContacto;
    }

    public void setIdTipoContacto(int idTipoContacto) {
        this.idTipoContacto = idTipoContacto;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "DataContacto{" +
                "idPersonaContacto=" + idPersonaContacto +
                ", idPersona=" + idPersona +
                ", idTipoContacto=" + idTipoContacto +
                ", dato='" + dato + '\'' +
                ", estado=" + estado +
                '}';
    }
}