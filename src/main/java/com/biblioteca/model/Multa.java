package com.biblioteca.model;

import java.time.LocalDate;

public class Multa {
    private int idMulta;
    private int idPrestamo;
    private boolean pagada;
    private LocalDate fechaGeneracion;
    private LocalDate fechaPago;
    private String descripcion;

    public Multa() {}

    public Multa(int idPrestamo, LocalDate fechaGeneracion, String descripcion) {
        this.idPrestamo = idPrestamo;
        this.fechaGeneracion = fechaGeneracion;
        this.descripcion = descripcion;
        this.pagada = false;
    }

    public int getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(int idMulta) {
        this.idMulta = idMulta;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDate fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Multa{" +
                "idMulta=" + idMulta +
                ", idPrestamo=" + idPrestamo +
                ", pagada=" + pagada +
                ", fechaGeneracion=" + fechaGeneracion +
                ", fechaPago=" + fechaPago +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}