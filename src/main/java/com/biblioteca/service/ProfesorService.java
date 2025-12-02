package com.biblioteca.service;

import com.biblioteca.model.Profesor;
import com.biblioteca.repository.ProfesorRepository;
import java.util.List;

public class ProfesorService {
    private ProfesorRepository profesorRepository;

    public ProfesorService() {
        this.profesorRepository = new ProfesorRepository();
    }

    public void registrarProfesor(Profesor profesor) {
        profesorRepository.registrar(profesor);
    }

    public Profesor buscarProfesor(int id) {
        return profesorRepository.buscar(id);
    }

    public List<Profesor> listarProfesores() {
        return profesorRepository.listar();
    }

    public void actualizarProfesor(Profesor profesor) {
        profesorRepository.actualizar(profesor);
    }

    public void eliminarProfesor(int id) {
        profesorRepository.eliminar(id);
    }

    public Profesor buscarPorCodigo(String codigoProfesor) {
        return profesorRepository.buscarPorCodigo(codigoProfesor);
    }
}