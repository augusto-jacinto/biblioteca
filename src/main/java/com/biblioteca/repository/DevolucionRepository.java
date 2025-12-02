package com.biblioteca.repository;

import com.biblioteca.interfaces.CRUDOperations;
import com.biblioteca.model.Devoluccion;
import com.biblioteca.util.ConexionBD;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DevolucionRepository implements CRUDOperations<Devoluccion> {

    @Override
    public void registrar(Devoluccion devoluccion) {
        String sql = "INSERT INTO Devoluccion (idPrestamo, idAdministrativo, fechaDevoluccion, observacion, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, devoluccion.getIdPrestamo());
            pstmt.setInt(2, devoluccion.getIdAdministrativo());
            pstmt.setDate(3, Date.valueOf(devoluccion.getFechaDevoluccion()));
            pstmt.setString(4, devoluccion.getObservacion());
            pstmt.setBoolean(5, devoluccion.isEstado());
            pstmt.executeUpdate();
            System.out.println("Devolución registrada exitosamente");
        } catch (SQLException e) {
            System.err.println("Error registrando devolución: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Devoluccion buscar(int id) {
        String sql = "SELECT * FROM Devoluccion WHERE idDevoluccion = ?";
        Devoluccion devoluccion = null;
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                devoluccion = new Devoluccion();
                devoluccion.setIdDevoluccion(rs.getInt("idDevoluccion"));
                devoluccion.setIdPrestamo(rs.getInt("idPrestamo"));
                devoluccion.setIdAdministrativo(rs.getInt("idAdministrativo"));
                devoluccion.setFechaDevoluccion(rs.getDate("fechaDevoluccion").toLocalDate());
                devoluccion.setObservacion(rs.getString("observacion"));
                devoluccion.setEstado(rs.getBoolean("estado"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error buscando devolución: " + e.getMessage());
            e.printStackTrace();
        }
        return devoluccion;
    }

    @Override
    public List<Devoluccion> listar() {
        String sql = "SELECT * FROM Devoluccion WHERE estado = 1";
        List<Devoluccion> devoluciones = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Devoluccion devoluccion = new Devoluccion();
                devoluccion.setIdDevoluccion(rs.getInt("idDevoluccion"));
                devoluccion.setIdPrestamo(rs.getInt("idPrestamo"));
                devoluccion.setIdAdministrativo(rs.getInt("idAdministrativo"));
                devoluccion.setFechaDevoluccion(rs.getDate("fechaDevoluccion").toLocalDate());
                devoluccion.setObservacion(rs.getString("observacion"));
                devoluccion.setEstado(rs.getBoolean("estado"));
                devoluciones.add(devoluccion);
            }
        } catch (SQLException e) {
            System.err.println("Error listando devoluciones: " + e.getMessage());
            e.printStackTrace();
        }
        return devoluciones;
    }

    @Override
    public void actualizar(Devoluccion devoluccion) {
        String sql = "UPDATE Devoluccion SET idPrestamo = ?, idAdministrativo = ?, fechaDevoluccion = ?, observacion = ?, estado = ? WHERE idDevoluccion = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, devoluccion.getIdPrestamo());
            pstmt.setInt(2, devoluccion.getIdAdministrativo());
            pstmt.setDate(3, Date.valueOf(devoluccion.getFechaDevoluccion()));
            pstmt.setString(4, devoluccion.getObservacion());
            pstmt.setBoolean(5, devoluccion.isEstado());
            pstmt.setInt(6, devoluccion.getIdDevoluccion());
            pstmt.executeUpdate();
            System.out.println("Devolución actualizada exitosamente");
        } catch (SQLException e) {
            System.err.println("Error actualizando devolución: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "UPDATE Devoluccion SET estado = 0 WHERE idDevoluccion = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Devolución eliminada exitosamente");
        } catch (SQLException e) {
            System.err.println("Error eliminando devolución: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Devoluccion buscarPorPrestamo(int idPrestamo) {
        String sql = "SELECT * FROM Devoluccion WHERE idPrestamo = ? AND estado = 1";
        Devoluccion devoluccion = null;
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPrestamo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                devoluccion = new Devoluccion();
                devoluccion.setIdDevoluccion(rs.getInt("idDevoluccion"));
                devoluccion.setIdPrestamo(rs.getInt("idPrestamo"));
                devoluccion.setIdAdministrativo(rs.getInt("idAdministrativo"));
                devoluccion.setFechaDevoluccion(rs.getDate("fechaDevoluccion").toLocalDate());
                devoluccion.setObservacion(rs.getString("observacion"));
                devoluccion.setEstado(rs.getBoolean("estado"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error buscando devolución: " + e.getMessage());
            e.printStackTrace();
        }
        return devoluccion;
    }
}