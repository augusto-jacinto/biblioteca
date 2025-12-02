package com.biblioteca.service;

import com.biblioteca.model.Recurso;
import com.biblioteca.repository.RecursoRepository;
import java.util.List;

public class RecursoService {
    private RecursoRepository recursoRepository;

    public RecursoService() {
        this.recursoRepository = new RecursoRepository();
    }

    public void registrarRecurso(Recurso recurso) {
        recursoRepository.registrar(recurso);
    }

    public Recurso buscarRecurso(int id) {
        return recursoRepository.buscar(id);
    }

    public List<Recurso> listarRecursos() {
        return recursoRepository.listar();
    }

    public void actualizarRecurso(Recurso recurso) {
        recursoRepository.actualizar(recurso);
    }

    public void eliminarRecurso(int id) {
        recursoRepository.eliminar(id);
    }

    public List<Recurso> listarPorCategoria(int idCategoria) {
        return recursoRepository.listarPorCategoria(idCategoria);
    }
}