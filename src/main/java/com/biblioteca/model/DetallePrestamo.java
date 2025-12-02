package com.biblioteca.model;

public class DetallePrestamo {
    private int idDetallePrestamo;
    private int idPrestamo;
    private int idRecurso;
    private boolean estado;

    public DetallePrestamo() {}

    public DetallePrestamo(int idPrestamo, int idRecurso) {
        this.idPrestamo = idPrestamo;
        this.idRecurso = idRecurso;
        this.estado = true;
    }

    public int getIdDetallePrestamo() {
        return idDetallePrestamo;
    }

    public void setIdDetallePrestamo(int idDetallePrestamo) {
        this.idDetallePrestamo = idDetallePrestamo;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "DetallePrestamo{" +
                "idDetallePrestamo=" + idDetallePrestamo +
                ", idPrestamo=" + idPrestamo +
                ", idRecurso=" + idRecurso +
                ", estado=" + estado +
                '}';
    }
}