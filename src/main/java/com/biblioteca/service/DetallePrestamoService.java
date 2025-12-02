package com.biblioteca.service;

import com.biblioteca.model.DetallePrestamo;
import com.biblioteca.repository.DetallePrestamoRepository;
import java.util.List;

public class DetallePrestamoService {
    private DetallePrestamoRepository detallePrestamoRepository;

    public DetallePrestamoService() {
        this.detallePrestamoRepository = new DetallePrestamoRepository();
    }

    public void registrarDetallePrestamo(DetallePrestamo detallePrestamo) {
        detallePrestamoRepository.registrar(detallePrestamo);
    }

    public DetallePrestamo buscarDetallePrestamo(int id) {
        return detallePrestamoRepository.buscar(id);
    }

    public List<DetallePrestamo> listarDetallesPrestamo() {
        return detallePrestamoRepository.listar();
    }

    public void actualizarDetallePrestamo(DetallePrestamo detallePrestamo) {
        detallePrestamoRepository.actualizar(detallePrestamo);
    }

    public void eliminarDetallePrestamo(int id) {
        detallePrestamoRepository.eliminar(id);
    }

    public List<DetallePrestamo> buscarPorPrestamo(int idPrestamo) {
        return detallePrestamoRepository.buscarPorPrestamo(idPrestamo);
    }
}