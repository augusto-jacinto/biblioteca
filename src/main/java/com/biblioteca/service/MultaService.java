package com.biblioteca.service;

import com.biblioteca.model.Multa;
import com.biblioteca.repository.MultaRepository;
import java.util.List;

public class MultaService {
    private MultaRepository multaRepository;

    public MultaService() {
        this.multaRepository = new MultaRepository();
    }

    public void registrarMulta(Multa multa) {
        multaRepository.registrar(multa);
    }

    public Multa buscarMulta(int id) {
        return multaRepository.buscar(id);
    }

    public List<Multa> listarMultas() {
        return multaRepository.listar();
    }

    public void actualizarMulta(Multa multa) {
        multaRepository.actualizar(multa);
    }

    public void eliminarMulta(int id) {
        multaRepository.eliminar(id);
    }

    public List<Multa> buscarPorPrestamo(int idPrestamo) {
        return multaRepository.buscarPorPrestamo(idPrestamo);
    }

    public List<Multa> listarNoPageadas() {
        return multaRepository.listarNoPageadas();
    }
}