package com.biblioteca.model;

import java.time.LocalDate;

public class Profesor extends Persona {
    private int idProfesor;
    private String codigoProfesor;
    private String idGradoEstudio;

    public Profesor() {
        super();
    }

    public Profesor(String nombres, String apellidos, LocalDate fechaNacimiento,
                    String numeroDocumento, String direccion, String nacionalidad,
                    String codigoProfesor, String idGradoEstudio) {
        super(nombres, apellidos, fechaNacimiento, numeroDocumento, direccion, nacionalidad);
        this.codigoProfesor = codigoProfesor;
        this.idGradoEstudio = idGradoEstudio;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getCodigoProfesor() {
        return codigoProfesor;
    }

    public void setCodigoProfesor(String codigoProfesor) {
        this.codigoProfesor = codigoProfesor;
    }

    public String getIdGradoEstudio() {
        return idGradoEstudio;
    }

    public void setIdGradoEstudio(String idGradoEstudio) {
        this.idGradoEstudio = idGradoEstudio;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "idProfesor=" + idProfesor +
                ", codigoProfesor='" + codigoProfesor + '\'' +
                ", idGradoEstudio='" + idGradoEstudio + '\'' +
                ", " + super.toString() +
                '}';
    }
}