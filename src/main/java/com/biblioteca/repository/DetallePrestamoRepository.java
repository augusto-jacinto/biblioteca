package com.biblioteca.repository;

import com.biblioteca.interfaces.CRUDOperations;
import com.biblioteca.model.DetallePrestamo;
import com.biblioteca.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetallePrestamoRepository implements CRUDOperations<DetallePrestamo> {

    @Override
    public void registrar(DetallePrestamo detallePrestamo) {
        String sql = "INSERT INTO DetallePrestamo (idPrestamo, idRecurso, estado) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, detallePrestamo.getIdPrestamo());
            pstmt.setInt(2, detallePrestamo.getIdRecurso());
            pstmt.setBoolean(3, detallePrestamo.isEstado());
            pstmt.executeUpdate();
            System.out.println("Detalle de préstamo registrado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error registrando detalle de préstamo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public DetallePrestamo buscar(int id) {
        String sql = "SELECT * FROM DetallePrestamo WHERE idDetallePrestamo = ?";
        DetallePrestamo detallePrestamo = null;
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                detallePrestamo = new DetallePrestamo();
                detallePrestamo.setIdDetallePrestamo(rs.getInt("idDetallePrestamo"));
                detallePrestamo.setIdPrestamo(rs.getInt("idPrestamo"));
                detallePrestamo.setIdRecurso(rs.getInt("idRecurso"));
                detallePrestamo.setEstado(rs.getBoolean("estado"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error buscando detalle de préstamo: " + e.getMessage());
            e.printStackTrace();
        }
        return detallePrestamo;
    }

    @Override
    public List<DetallePrestamo> listar() {
        String sql = "SELECT * FROM DetallePrestamo WHERE estado = 1";
        List<DetallePrestamo> detallesPrestamo = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                DetallePrestamo detallePrestamo = new DetallePrestamo();
                detallePrestamo.setIdDetallePrestamo(rs.getInt("idDetallePrestamo"));
                detallePrestamo.setIdPrestamo(rs.getInt("idPrestamo"));
                detallePrestamo.setIdRecurso(rs.getInt("idRecurso"));
                detallePrestamo.setEstado(rs.getBoolean("estado"));
                detallesPrestamo.add(detallePrestamo);
            }
        } catch (SQLException e) {
            System.err.println("Error listando detalles de préstamo: " + e.getMessage());
            e.printStackTrace();
        }
        return detallesPrestamo;
    }

    @Override
    public void actualizar(DetallePrestamo detallePrestamo) {
        String sql = "UPDATE DetallePrestamo SET idPrestamo = ?, idRecurso = ?, estado = ? WHERE idDetallePrestamo = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, detallePrestamo.getIdPrestamo());
            pstmt.setInt(2, detallePrestamo.getIdRecurso());
            pstmt.setBoolean(3, detallePrestamo.isEstado());
            pstmt.setInt(4, detallePrestamo.getIdDetallePrestamo());
            pstmt.executeUpdate();
            System.out.println("Detalle de préstamo actualizado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error actualizando detalle de préstamo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM DetallePrestamo WHERE idDetallePrestamo = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Detalle de préstamo eliminado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error eliminando detalle de préstamo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<DetallePrestamo> buscarPorPrestamo(int idPrestamo) {
        String sql = "SELECT * FROM DetallePrestamo WHERE idPrestamo = ? AND estado = 1";
        List<DetallePrestamo> detallesPrestamo = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPrestamo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                DetallePrestamo detallePrestamo = new DetallePrestamo();
                detallePrestamo.setIdDetallePrestamo(rs.getInt("idDetallePrestamo"));
                detallePrestamo.setIdPrestamo(rs.getInt("idPrestamo"));
                detallePrestamo.setIdRecurso(rs.getInt("idRecurso"));
                detallePrestamo.setEstado(rs.getBoolean("estado"));
                detallesPrestamo.add(detallePrestamo);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error buscando detalles de préstamo: " + e.getMessage());
            e.printStackTrace();
        }
        return detallesPrestamo;
    }
}