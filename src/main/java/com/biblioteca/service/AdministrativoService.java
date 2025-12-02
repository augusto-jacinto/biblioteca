package com.biblioteca.service;

import com.biblioteca.model.Administrativo;
import com.biblioteca.repository.AdministrativoRepository;
import java.util.List;

public class AdministrativoService {
    private AdministrativoRepository administrativoRepository;

    public AdministrativoService() {
        this.administrativoRepository = new AdministrativoRepository();
    }

    public void registrarAdministrativo(Administrativo administrativo) {
        administrativoRepository.registrar(administrativo);
    }

    public Administrativo buscarAdministrativo(int id) {
        return administrativoRepository.buscar(id);
    }

    public List<Administrativo> listarAdministrativos() {
        return administrativoRepository.listar();
    }

    public void actualizarAdministrativo(Administrativo administrativo) {
        administrativoRepository.actualizar(administrativo);
    }

    public void eliminarAdministrativo(int id) {
        administrativoRepository.eliminar(id);
    }

    public Administrativo buscarPorCodigo(String codigoAdministrativo) {
        return administrativoRepository.buscarPorCodigo(codigoAdministrativo);
    }
}