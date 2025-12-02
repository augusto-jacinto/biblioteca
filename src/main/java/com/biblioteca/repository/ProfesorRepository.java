package com.biblioteca.repository;

import com.biblioteca.interfaces.CRUDOperations;
import com.biblioteca.model.Profesor;
import com.biblioteca.util.ConexionBD;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProfesorRepository implements CRUDOperations<Profesor> {

    @Override
    public void registrar(Profesor profesor) {
        String sqlPersona = "INSERT INTO Persona (nombres, apellidos, fechaNacimiento, numeroDocumento, direccion, nacionalidad, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlProfesor = "INSERT INTO Profesor (idPersona, codigoProfesor, idGradoEstudio, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConexion()) {
            conn.setAutoCommit(false);
            try (PreparedStatement pstmtPersona = conn.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS)) {
                pstmtPersona.setString(1, profesor.getNombres());
                pstmtPersona.setString(2, profesor.getApellidos());
                pstmtPersona.setDate(3, Date.valueOf(profesor.getFechaNacimiento()));
                pstmtPersona.setString(4, profesor.getNumeroDocumento());
                pstmtPersona.setString(5, profesor.getDireccion());
                pstmtPersona.setString(6, profesor.getNacionalidad());
                pstmtPersona.setBoolean(7, profesor.isEstado());
                pstmtPersona.executeUpdate();
                try (ResultSet rs = pstmtPersona.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idPersona = rs.getInt(1);
                        try (PreparedStatement pstmtProfesor = conn.prepareStatement(sqlProfesor)) {
                            pstmtProfesor.setInt(1, idPersona);
                            pstmtProfesor.setString(2, profesor.getCodigoProfesor());
                            pstmtProfesor.setString(3, profesor.getIdGradoEstudio());
                            pstmtProfesor.setBoolean(4, profesor.isEstado());
                            pstmtProfesor.executeUpdate();
                        }
                    }
                }
                conn.commit();
                System.out.println("Profesor registrado exitosamente");
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Error registrando profesor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Profesor buscar(int id) {
        String sql = "SELECT p.*, pr.idProfesor, pr.codigoProfesor, pr.idGradoEstudio FROM Profesor pr JOIN Persona p ON pr.idPersona = p.idPersona WHERE pr.idProfesor = ?";
        Profesor profesor = null;
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                profesor = new Profesor();
                profesor.setIdPersona(rs.getInt("idPersona"));
                profesor.setIdProfesor(rs.getInt("idProfesor"));
                profesor.setNombres(rs.getString("nombres"));
                profesor.setApellidos(rs.getString("apellidos"));
                profesor.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                profesor.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
                profesor.setNumeroDocumento(rs.getString("numeroDocumento"));
                profesor.setDireccion(rs.getString("direccion"));
                profesor.setNacionalidad(rs.getString("nacionalidad"));
                profesor.setEstado(rs.getBoolean("estado"));
                profesor.setCodigoProfesor(rs.getString("codigoProfesor"));
                profesor.setIdGradoEstudio(rs.getString("idGradoEstudio"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error buscando profesor: " + e.getMessage());
            e.printStackTrace();
        }
        return profesor;
    }
    @Override
    public List<Profesor> listar() {
        String sql = "SELECT p.*, pr.idProfesor, pr.codigoProfesor, pr.idGradoEstudio FROM Profesor pr JOIN Persona p ON pr.idPersona = p.idPersona WHERE p.estado = 1 AND pr.estado = 1";
        List<Profesor> profesores = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Profesor profesor = new Profesor();
                profesor.setIdPersona(rs.getInt("idPersona"));
                profesor.setIdProfesor(rs.getInt("idProfesor"));
                profesor.setNombres(rs.getString("nombres"));
                profesor.setApellidos(rs.getString("apellidos"));
                profesor.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                profesor.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
                profesor.setNumeroDocumento(rs.getString("numeroDocumento"));
                profesor.setDireccion(rs.getString("direccion"));
                profesor.setNacionalidad(rs.getString("nacionalidad"));
                profesor.setEstado(rs.getBoolean("estado"));
                profesor.setCodigoProfesor(rs.getString("codigoProfesor"));
                profesor.setIdGradoEstudio(rs.getString("idGradoEstudio"));
                profesores.add(profesor);
            }
        } catch (SQLException e) {
            System.err.println("Error listando profesores: " + e.getMessage());
            e.printStackTrace();
        }
        return profesores;
    }

    @Override
    public void actualizar(Profesor profesor) {
        String sqlPersona = "UPDATE Persona SET nombres = ?, apellidos = ?, fechaNacimiento = ?, numeroDocumento = ?, direccion = ?, nacionalidad = ? WHERE idPersona = ?";
        String sqlProfesor = "UPDATE Profesor SET codigoProfesor = ?, idGradoEstudio = ? WHERE idProfesor = ?";
        try (Connection conn = ConexionBD.getConexion()) {
            conn.setAutoCommit(false);
            try (PreparedStatement pstmtPersona = conn.prepareStatement(sqlPersona)) {
                pstmtPersona.setString(1, profesor.getNombres());
                pstmtPersona.setString(2, profesor.getApellidos());
                pstmtPersona.setDate(3, Date.valueOf(profesor.getFechaNacimiento()));
                pstmtPersona.setString(4, profesor.getNumeroDocumento());
                pstmtPersona.setString(5, profesor.getDireccion());
                pstmtPersona.setString(6, profesor.getNacionalidad());
                pstmtPersona.setInt(7, profesor.getIdPersona());
                pstmtPersona.executeUpdate();
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sqlProfesor)) {
                pstmt.setString(1, profesor.getCodigoProfesor());
                pstmt.setString(2, profesor.getIdGradoEstudio());
                pstmt.setInt(3, profesor.getIdProfesor());
                pstmt.executeUpdate();
            }
            conn.commit();
            System.out.println("Profesor actualizado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error actualizando profesor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "UPDATE Profesor SET estado = 0 WHERE idProfesor = ?";
        String sqlPersona = "UPDATE Persona SET estado = 0 WHERE idPersona = (SELECT idPersona FROM Profesor WHERE idProfesor = ?)";
        try (Connection conn = ConexionBD.getConexion()) {
            conn.setAutoCommit(false);
            try (PreparedStatement pstmt1 = conn.prepareStatement(sql);
                 PreparedStatement pstmt2 = conn.prepareStatement(sqlPersona)) {
                pstmt1.setInt(1, id);
                pstmt1.executeUpdate();
                pstmt2.setInt(1, id);
                pstmt2.executeUpdate();
                conn.commit();
                System.out.println("Profesor eliminado exitosamente");
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Error eliminando profesor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Profesor buscarPorCodigo(String codigoProfesor) {
        String sql = "SELECT p.*, pr.idProfesor, pr.codigoProfesor, pr.idGradoEstudio FROM Profesor pr JOIN Persona p ON pr.idPersona = p.idPersona WHERE pr.codigoProfesor = ? AND p.estado = 1";
        Profesor profesor = null;
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigoProfesor);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                profesor = new Profesor();
                profesor.setIdPersona(rs.getInt("idPersona"));
                profesor.setIdProfesor(rs.getInt("idProfesor"));
                profesor.setNombres(rs.getString("nombres"));
                profesor.setApellidos(rs.getString("apellidos"));
                profesor.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                profesor.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
                profesor.setNumeroDocumento(rs.getString("numeroDocumento"));
                profesor.setDireccion(rs.getString("direccion"));
                profesor.setNacionalidad(rs.getString("nacionalidad"));
                profesor.setEstado(rs.getBoolean("estado"));
                profesor.setCodigoProfesor(rs.getString("codigoProfesor"));
                profesor.setIdGradoEstudio(rs.getString("idGradoEstudio"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error buscando profesor por código: " + e.getMessage());
        }
        return profesor;
    }
}