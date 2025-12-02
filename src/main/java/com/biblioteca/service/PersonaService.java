package com.biblioteca.service;

import com.biblioteca.model.Persona;
import com.biblioteca.repository.PersonaRepository;
import java.util.List;

public class PersonaService {
    private PersonaRepository personaRepository;

    public PersonaService() {
        this.personaRepository = new PersonaRepository();
    }

    public void registrarPersona(Persona persona) {
        personaRepository.registrar(persona);
    }

    public Persona buscarPersona(int id) {
        return personaRepository.buscar(id);
    }

    public List<Persona> listarPersonas() {
        return personaRepository.listar();
    }

    public void actualizarPersona(Persona persona) {
        personaRepository.actualizar(persona);
    }

    public void eliminarPersona(int id) {
        personaRepository.eliminar(id);
    }

    public Persona buscarPorDocumento(String numeroDocumento) {
        return personaRepository.buscarPorDocumento(numeroDocumento);
    }
}