package com.biblioteca.service;

import com.biblioteca.model.Devoluccion;
import com.biblioteca.repository.DevolucionRepository;
import java.util.List;

public class DevolucionService {
    private DevolucionRepository devolucionRepository;

    public DevolucionService() {
        this.devolucionRepository = new DevolucionRepository();
    }

    public void registrarDevoluccion(Devoluccion devoluccion) {
        devolucionRepository.registrar(devoluccion);
    }

    public Devoluccion buscarDevoluccion(int id) {
        return devolucionRepository.buscar(id);
    }

    public List<Devoluccion> listarDevoluciones() {
        return devolucionRepository.listar();
    }

    public void actualizarDevoluccion(Devoluccion devoluccion) {
        devolucionRepository.actualizar(devoluccion);
    }

    public void eliminarDevoluccion(int id) {
        devolucionRepository.eliminar(id);
    }

    public Devoluccion buscarPorPrestamo(int idPrestamo) {
        return devolucionRepository.buscarPorPrestamo(idPrestamo);
    }
}