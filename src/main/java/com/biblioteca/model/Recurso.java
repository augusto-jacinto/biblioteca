package com.biblioteca.model;

public class Recurso {
    private int idRecurso;
    private int idCategoria;
    private String titulo;
    private String autor;
    private String isbn;
    private int cantidadDisponible;
    private int cantidadTotal;
    private boolean estado;

    public Recurso() {}

    public Recurso(int idCategoria, String titulo, String autor, String isbn,
                   int cantidadDisponible, int cantidadTotal) {
        this.idCategoria = idCategoria;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadTotal = cantidadTotal;
        this.estado = true;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Recurso{" +
                "idRecurso=" + idRecurso +
                ", idCategoria=" + idCategoria +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", isbn='" + isbn + '\'' +
                ", cantidadDisponible=" + cantidadDisponible +
                ", cantidadTotal=" + cantidadTotal +
                ", estado=" + estado +
                '}';
    }
}