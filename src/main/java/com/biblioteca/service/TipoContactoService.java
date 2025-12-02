package com.biblioteca.service;

import com.biblioteca.model.TipoContacto;
import com.biblioteca.repository.TipoContactoRepository;
import java.util.List;

public class TipoContactoService {
    private TipoContactoRepository tipoContactoRepository;

    public TipoContactoService() {
        this.tipoContactoRepository = new TipoContactoRepository();
    }

    public void registrarTipoContacto(TipoContacto tipoContacto) {
        tipoContactoRepository.registrar(tipoContacto);
    }

    public TipoContacto buscarTipoContacto(int id) {
        return tipoContactoRepository.buscar(id);
    }

    public List<TipoContacto> listarTiposContacto() {
        return tipoContactoRepository.listar();
    }

    public void actualizarTipoContacto(TipoContacto tipoContacto) {
        tipoContactoRepository.actualizar(tipoContacto);
    }

    public void eliminarTipoContacto(int id) {
        tipoContactoRepository.eliminar(id);
    }
}