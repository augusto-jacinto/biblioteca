package com.biblioteca.service;

import com.biblioteca.model.DataContacto;
import com.biblioteca.repository.DataContactoRepository;
import java.util.List;

public class DataContactoService {
    private DataContactoRepository dataContactoRepository;

    public DataContactoService() {
        this.dataContactoRepository = new DataContactoRepository();
    }

    public void registrarDataContacto(DataContacto dataContacto) {
        dataContactoRepository.registrar(dataContacto);
    }

    public DataContacto buscarDataContacto(int id) {
        return dataContactoRepository.buscar(id);
    }

    public List<DataContacto> listarDataContactos() {
        return dataContactoRepository.listar();
    }

    public void actualizarDataContacto(DataContacto dataContacto) {
        dataContactoRepository.actualizar(dataContacto);
    }

    public void eliminarDataContacto(int id) {
        dataContactoRepository.eliminar(id);
    }

    public List<DataContacto> buscarPorPersona(int idPersona) {
        return dataContactoRepository.buscarPorPersona(idPersona);
    }
}