package com.biblioteca.model;

import java.time.LocalDate;

public class Prestamo {
    private int idPrestamo;
    private int idAlumno;
    private int idAdministrativo;
    private LocalDate fechaPrestamo;
    private LocalDate fechaVencimiento;
    private boolean estado;

    public Prestamo() {}

    public Prestamo(int idAlumno, int idAdministrativo, LocalDate fechaPrestamo, LocalDate fechaVencimiento) {
        this.idAlumno = idAlumno;
        this.idAdministrativo = idAdministrativo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = true;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdAdministrativo() {
        return idAdministrativo;
    }

    public void setIdAdministrativo(int idAdministrativo) {
        this.idAdministrativo = idAdministrativo;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "idPrestamo=" + idPrestamo +
                ", idAlumno=" + idAlumno +
                ", idAdministrativo=" + idAdministrativo +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaVencimiento=" + fechaVencimiento +
                ", estado=" + estado +
                '}';
    }
}