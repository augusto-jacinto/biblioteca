package com.biblioteca.service;

import com.biblioteca.model.Prestamo;
import com.biblioteca.repository.PrestamoRepository;
import java.util.List;

public class PrestamoService {
    private PrestamoRepository prestamoRepository;

    public PrestamoService() {
        this.prestamoRepository = new PrestamoRepository();
    }

    public void registrarPrestamo(Prestamo prestamo) {
        prestamoRepository.registrar(prestamo);
    }

    public Prestamo buscarPrestamo(int id) {
        return prestamoRepository.buscar(id);
    }

    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.listar();
    }

    public void actualizarPrestamo(Prestamo prestamo) {
        prestamoRepository.actualizar(prestamo);
    }

    public void eliminarPrestamo(int id) {
        prestamoRepository.eliminar(id);
    }

    public List<Prestamo> listarPorAlumno(int idAlumno) {
        return prestamoRepository.listarPorAlumno(idAlumno);
    }
}