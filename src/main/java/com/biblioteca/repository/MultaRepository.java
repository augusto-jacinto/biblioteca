package com.biblioteca.repository;

import com.biblioteca.interfaces.CRUDOperations;
import com.biblioteca.model.Multa;
import com.biblioteca.util.ConexionBD;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MultaRepository implements CRUDOperations<Multa> {

    @Override
    public void registrar(Multa multa) {
        String sql = "INSERT INTO Multa (idPrestamo, pagada, fechaGeneracion, fechaPago, descripcion) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, multa.getIdPrestamo());
            pstmt.setBoolean(2, multa.isPagada());
            pstmt.setDate(3, Date.valueOf(multa.getFechaGeneracion()));
            pstmt.setDate(4, multa.getFechaPago() != null ? Date.valueOf(multa.getFechaPago()) : null);
            pstmt.setString(5, multa.getDescripcion());
            pstmt.executeUpdate();
            System.out.println("Multa registrada exitosamente");
        } catch (SQLException e) {
            System.err.println("Error registrando multa: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Multa buscar(int id) {
        String sql = "SELECT * FROM Multa WHERE idMulta = ?";
        Multa multa = null;
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                multa = new Multa();
                multa.setIdMulta(rs.getInt("idMulta"));
                multa.setIdPrestamo(rs.getInt("idPrestamo"));
                multa.setPagada(rs.getBoolean("pagada"));
                multa.setFechaGeneracion(rs.getDate("fechaGeneracion").toLocalDate());
                multa.setFechaPago(rs.getDate("fechaPago") != null ? rs.getDate("fechaPago").toLocalDate() : null);
                multa.setDescripcion(rs.getString("descripcion"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error buscando multa: " + e.getMessage());
            e.printStackTrace();
        }
        return multa;
    }

    @Override
    public List<Multa> listar() {
        String sql = "SELECT * FROM Multa";
        List<Multa> multas = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Multa multa = new Multa();
                multa.setIdMulta(rs.getInt("idMulta"));
                multa.setIdPrestamo(rs.getInt("idPrestamo"));
                multa.setPagada(rs.getBoolean("pagada"));
                multa.setFechaGeneracion(rs.getDate("fechaGeneracion").toLocalDate());
                multa.setFechaPago(rs.getDate("fechaPago") != null ? rs.getDate("fechaPago").toLocalDate() : null);
                multa.setDescripcion(rs.getString("descripcion"));
                multas.add(multa);
            }
        } catch (SQLException e) {
            System.err.println("Error listando multas: " + e.getMessage());
            e.printStackTrace();
        }
        return multas;
    }

    @Override
    public void actualizar(Multa multa) {
        String sql = "UPDATE Multa SET idPrestamo = ?, pagada = ?, fechaGeneracion = ?, fechaPago = ?, descripcion = ? WHERE idMulta = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, multa.getIdPrestamo());
            pstmt.setBoolean(2, multa.isPagada());
            pstmt.setDate(3, Date.valueOf(multa.getFechaGeneracion()));
            pstmt.setDate(4, multa.getFechaPago() != null ? Date.valueOf(multa.getFechaPago()) : null);
            pstmt.setString(5, multa.getDescripcion());
            pstmt.setInt(6, multa.getIdMulta());
            pstmt.executeUpdate();
            System.out.println("Multa actualizada exitosamente");
        } catch (SQLException e) {
            System.err.println("Error actualizando multa: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Multa WHERE idMulta = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Multa eliminada exitosamente");
        } catch (SQLException e) {
            System.err.println("Error eliminando multa: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Multa> buscarPorPrestamo(int idPrestamo) {
        String sql = "SELECT * FROM Multa WHERE idPrestamo = ?";
        List<Multa> multas = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPrestamo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Multa multa = new Multa();
                multa.setIdMulta(rs.getInt("idMulta"));
                multa.setIdPrestamo(rs.getInt("idPrestamo"));
                multa.setPagada(rs.getBoolean("pagada"));
                multa.setFechaGeneracion(rs.getDate("fechaGeneracion").toLocalDate());
                multa.setFechaPago(rs.getDate("fechaPago") != null ? rs.getDate("fechaPago").toLocalDate() : null);
                multa.setDescripcion(rs.getString("descripcion"));
                multas.add(multa);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error buscando multas: " + e.getMessage());
            e.printStackTrace();
        }
        return multas;
    }

    public List<Multa> listarNoPageadas() {
        String sql = "SELECT * FROM Multa WHERE pagada = 0";
        List<Multa> multas = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Multa multa = new Multa();
                multa.setIdMulta(rs.getInt("idMulta"));
                multa.setIdPrestamo(rs.getInt("idPrestamo"));
                multa.setPagada(rs.getBoolean("pagada"));
                multa.setFechaGeneracion(rs.getDate("fechaGeneracion").toLocalDate());
                multa.setFechaPago(rs.getDate("fechaPago") != null ? rs.getDate("fechaPago").toLocalDate() : null);
                multa.setDescripcion(rs.getString("descripcion"));
                multas.add(multa);
            }
        } catch (SQLException e) {
            System.err.println("Error listando multas no pagadas: " + e.getMessage());
            e.printStackTrace();
        }
        return multas;
    }
}