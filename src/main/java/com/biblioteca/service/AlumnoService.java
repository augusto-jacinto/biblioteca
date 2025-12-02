package com.biblioteca.service;

import com.biblioteca.model.Alumno;
import com.biblioteca.repository.AlumnoRepository;
import java.util.List;

public class AlumnoService {
    private AlumnoRepository alumnoRepository;

    public AlumnoService() {
        this.alumnoRepository = new AlumnoRepository();
    }

    public void registrarAlumno(Alumno alumno) {
        alumnoRepository.registrar(alumno);
    }

    public Alumno buscarAlumno(int id) {
        return alumnoRepository.buscar(id);
    }

    public List<Alumno> listarAlumnos() {
        return alumnoRepository.listar();
    }

    public void actualizarAlumno(Alumno alumno) {
        alumnoRepository.actualizar(alumno);
    }

    public void eliminarAlumno(int id) {
        alumnoRepository.eliminar(id);
    }

    public Alumno buscarPorCodigo(String codigoAlumno) {
        return alumnoRepository.buscarPorCodigo(codigoAlumno);
    }
}