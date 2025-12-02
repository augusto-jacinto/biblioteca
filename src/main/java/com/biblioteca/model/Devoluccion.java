package com.biblioteca.model;

import java.time.LocalDate;

public class Devoluccion {
    private int idDevoluccion;
    private int idPrestamo;
    private int idAdministrativo;
    private LocalDate fechaDevoluccion;
    private String observacion;
    private boolean estado;

    public int getIdDevoluccion() {
        return idDevoluccion;
    }

    public void setIdDevoluccion(int idDevoluccion) {
        this.idDevoluccion = idDevoluccion;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdAdministrativo() {
        return idAdministrativo;
    }

    public void setIdAdministrativo(int idAdministrativo) {
        this.idAdministrativo = idAdministrativo;
    }

    public LocalDate getFechaDevoluccion() {
        return fechaDevoluccion;
    }

    public void setFechaDevoluccion(LocalDate fechaDevoluccion) {
        this.fechaDevoluccion = fechaDevoluccion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Devoluccion() {
    }

    public Devoluccion(int idPrestamo, int idAdministrativo, LocalDate fechaDevoluccion, String observacion) {
        this.idPrestamo = idPrestamo;
        this.idAdministrativo = idAdministrativo;
        this.fechaDevoluccion = fechaDevoluccion;
        this.observacion = observacion;
        this.estado = true;
    }

    @Override
    public String toString() {
        return "Devoluccion{" +
                "idDevoluccion=" + idDevoluccion +
                ", idPrestamo=" + idPrestamo +
                ", idAdministrativo=" + idAdministrativo +
                ", fechaDevoluccion=" + fechaDevoluccion +
                ", observacion='" + observacion + '\'' +
                ", estado=" + estado +
                '}';
    }
}
