package com.biblioteca.service;

import com.biblioteca.model.Categoria;
import com.biblioteca.repository.CategoriaRepository;
import java.util.List;

public class CategoriaService {
    private CategoriaRepository categoriaRepository;

    public CategoriaService() {
        this.categoriaRepository = new CategoriaRepository();
    }

    public void registrarCategoria(Categoria categoria) {
        categoriaRepository.registrar(categoria);
    }

    public Categoria buscarCategoria(int id) {
        return categoriaRepository.buscar(id);
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.listar();
    }

    public void actualizarCategoria(Categoria categoria) {
        categoriaRepository.actualizar(categoria);
    }

    public void eliminarCategoria(int id) {
        categoriaRepository.eliminar(id);
    }
}