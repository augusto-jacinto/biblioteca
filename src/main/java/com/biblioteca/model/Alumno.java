package com.biblioteca.model;

import java.time.LocalDate;

public class Alumno extends Persona {
    private int idAlumno;
    private String codigoAlumno;

    public Alumno() {
        super();
    }

    public Alumno(String nombres, String apellidos, LocalDate fechaNacimiento,
                  String numeroDocumento, String direccion, String nacionalidad,
                  String codigoAlumno) {
        super(nombres, apellidos, fechaNacimiento, numeroDocumento, direccion, nacionalidad);
        this.codigoAlumno = codigoAlumno;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getCodigoAlumno() {
        return codigoAlumno;
    }

    public void setCodigoAlumno(String codigoAlumno) {
        this.codigoAlumno = codigoAlumno;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "idAlumno=" + idAlumno +
                ", codigoAlumno='" + codigoAlumno + '\'' +
                ", " + super.toString() +
                '}';
    }
}