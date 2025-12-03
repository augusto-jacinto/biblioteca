package com.biblioteca.repository;

import com.biblioteca.interfaces.CRUDOperations;
import com.biblioteca.model.Persona;
import com.biblioteca.util.ConexionBD;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonaRepository implements CRUDOperations<Persona> {

    @Override
    public void registrar(Persona persona) {
        String sql = "INSERT INTO Persona (nombres, apellidos, fechaNacimiento, numeroDocumento, direccion, nacionalidad, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, persona.getNombres());
            pstmt.setString(2, persona.getApellidos());
            pstmt.setDate(3, Date.valueOf(persona.getFechaNacimiento()));
            pstmt.setString(4, persona.getNumeroDocumento());
            pstmt.setString(5, persona.getDireccion());
            pstmt.setString(6, persona.getNacionalidad());
            pstmt.setBoolean(7, persona.isEstado());
            pstmt.executeUpdate();
            System.out.println("Persona registrada exitosamente");
        } catch (SQLException e) {
            System.err.println("Error registrando persona: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Persona buscar(int id) {
        String sql = "SELECT * FROM Persona WHERE idPersona = ?";
        Persona persona = null;
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                persona = new Persona() {};
                persona.setIdPersona(rs.getInt("idPersona"));
                persona.setNombres(rs.getString("nombres"));
                persona.setApellidos(rs.getString("apellidos"));
                persona.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                persona.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
                persona.setNumeroDocumento(rs.getString("numeroDocumento"));
                persona.setDireccion(rs.getString("direccion"));
                persona.setNacionalidad(rs.getString("nacionalidad"));
                persona.setEstado(rs.getBoolean("estado"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error buscando persona: " + e.getMessage());
            e.printStackTrace();
        }
        return persona;
    }

    @Override
    public List<Persona> listar() {
        String sql = "SELECT * FROM Persona WHERE estado = 1";
        List<Persona> personas = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Persona persona = new Persona() {};
                persona.setIdPersona(rs.getInt("idPersona"));
                persona.setNombres(rs.getString("nombres"));
                persona.setApellidos(rs.getString("apellidos"));
                persona.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                persona.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
                persona.setNumeroDocumento(rs.getString("numeroDocumento"));
                persona.setDireccion(rs.getString("direccion"));
                persona.setNacionalidad(rs.getString("nacionalidad"));
                persona.setEstado(rs.getBoolean("estado"));
                personas.add(persona);
            }
        } catch (SQLException e) {
            System.err.println("Error listando personas: " + e.getMessage());
            e.printStackTrace();
        }
        return personas;
    }

    @Override
    public void actualizar(Persona persona) {
        String sql = "UPDATE Persona SET nombres = ?, apellidos = ?, fechaNacimiento = ?, numeroDocumento = ?, direccion = ?, nacionalidad = ?, estado = ? WHERE idPersona = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, persona.getNombres());
            pstmt.setString(2, persona.getApellidos());
            pstmt.setDate(3, Date.valueOf(persona.getFechaNacimiento()));
            pstmt.setString(4, persona.getNumeroDocumento());
            pstmt.setString(5, persona.getDireccion());
            pstmt.setString(6, persona.getNacionalidad());
            pstmt.setBoolean(7, persona.isEstado());
            pstmt.setInt(8, persona.getIdPersona());
            pstmt.executeUpdate();
            System.out.println("Persona actualizada exitosamente");
        } catch (SQLException e) {
            System.err.println("Error actualizando persona: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Persona WHERE idPersona = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Persona eliminada exitosamente");
        } catch (SQLException e) {
            System.err.println("Error eliminando persona: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Persona buscarPorDocumento(String numeroDocumento) {
        String sql = "SELECT * FROM Persona WHERE numeroDocumento = ? AND estado = 1";
        Persona persona = null;
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, numeroDocumento);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                persona = new Persona() {};
                persona.setIdPersona(rs.getInt("idPersona"));
                persona.setNombres(rs.getString("nombres"));
                persona.setApellidos(rs.getString("apellidos"));
                persona.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                persona.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
                persona.setNumeroDocumento(rs.getString("numeroDocumento"));
                persona.setDireccion(rs.getString("direccion"));
                persona.setNacionalidad(rs.getString("nacionalidad"));
                persona.setEstado(rs.getBoolean("estado"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error buscando persona por documento: " + e.getMessage());
        }
        return persona;
    }
}