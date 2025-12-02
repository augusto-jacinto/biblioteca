package com.biblioteca.model;

import java.time.LocalDate;

public class Administrativo extends Persona {
    private int idAdministrativo;
    private String codigoAdministrativo;
    private String idCargo;

    public Administrativo() {
        super();
    }

    public Administrativo(String nombres, String apellidos, LocalDate fechaNacimiento,
                          String numeroDocumento, String direccion, String nacionalidad,
                          String codigoAdministrativo, String idCargo) {
        super(nombres, apellidos, fechaNacimiento, numeroDocumento, direccion, nacionalidad);
        this.codigoAdministrativo = codigoAdministrativo;
        this.idCargo = idCargo;
    }

    public int getIdAdministrativo() {
        return idAdministrativo;
    }

    public void setIdAdministrativo(int idAdministrativo) {
        this.idAdministrativo = idAdministrativo;
    }

    public String getCodigoAdministrativo() {
        return codigoAdministrativo;
    }

    public void setCodigoAdministrativo(String codigoAdministrativo) {
        this.codigoAdministrativo = codigoAdministrativo;
    }

    public String getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(String idCargo) {
        this.idCargo = idCargo;
    }

    @Override
    public String toString() {
        return "Administrativo{" +
                "idAdministrativo=" + idAdministrativo +
                ", codigoAdministrativo='" + codigoAdministrativo + '\'' +
                ", idCargo='" + idCargo + '\'' +
                ", " + super.toString() +
                '}';
    }
}