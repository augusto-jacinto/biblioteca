package com.biblioteca.interfaces;

import java.util.List;

public interface CRUDOperations<T> {
    void registrar(T objeto);
    T buscar(int id);
    List<T> listar();
    void actualizar(T objeto);
    void eliminar(int id);
}