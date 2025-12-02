package com.biblioteca.model;

import java.time.LocalDate;

public abstract class Persona {
    private int idPersona;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private LocalDate fechaRegistro;
    private String numeroDocumento;
    private String direccion;
    private String nacionalidad;
    private boolean estado;

    public Persona() {}

    public Persona(String nombres, String apellidos, LocalDate fechaNacimiento,
                   String numeroDocumento, String direccion, String nacionalidad) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.numeroDocumento = numeroDocumento;
        this.direccion = direccion;
        this.nacionalidad = nacionalidad;
        this.estado = true;
        this.fechaRegistro = LocalDate.now();
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "idPersona=" + idPersona +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", direccion='" + direccion + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", estado=" + estado +
                '}';
    }
}