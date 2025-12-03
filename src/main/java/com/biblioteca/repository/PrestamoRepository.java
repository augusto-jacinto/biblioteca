package com.biblioteca.repository;

import com.biblioteca.interfaces.CRUDOperations;
import com.biblioteca.model.Prestamo;
import com.biblioteca.util.ConexionBD;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrestamoRepository implements CRUDOperations<Prestamo> {

    @Override
    public void registrar(Prestamo prestamo) {
        String sql = "INSERT INTO Prestamo (idAlumno, idAdministrativo, fechaPrestamo, fechaVencimiento, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, prestamo.getIdAlumno());
            pstmt.setInt(2, prestamo.getIdAdministrativo());
            pstmt.setDate(3, Date.valueOf(prestamo.getFechaPrestamo()));
            pstmt.setDate(4, Date.valueOf(prestamo.getFechaVencimiento()));
            pstmt.setBoolean(5, prestamo.isEstado());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    prestamo.setIdPrestamo(rs.getInt(1));
                }
            }
            System.out.println("Préstamo registrado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error registrando préstamo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Prestamo buscar(int id) {
        String sql = "SELECT * FROM Prestamo WHERE idPrestamo = ?";
        Prestamo prestamo = null;
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                prestamo = new Prestamo();
                prestamo.setIdPrestamo(rs.getInt("idPrestamo"));
                prestamo.setIdAlumno(rs.getInt("idAlumno"));
                prestamo.setIdAdministrativo(rs.getInt("idAdministrativo"));
                prestamo.setFechaPrestamo(rs.getDate("fechaPrestamo").toLocalDate());
                prestamo.setFechaVencimiento(rs.getDate("fechaVencimiento").toLocalDate());
                prestamo.setEstado(rs.getBoolean("estado"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error buscando préstamo: " + e.getMessage());
            e.printStackTrace();
        }
        return prestamo;
    }

    @Override
    public List<Prestamo> listar() {
        String sql = "SELECT * FROM Prestamo WHERE estado = 1";
        List<Prestamo> prestamos = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setIdPrestamo(rs.getInt("idPrestamo"));
                prestamo.setIdAlumno(rs.getInt("idAlumno"));
                prestamo.setIdAdministrativo(rs.getInt("idAdministrativo"));
                prestamo.setFechaPrestamo(rs.getDate("fechaPrestamo").toLocalDate());
                prestamo.setFechaVencimiento(rs.getDate("fechaVencimiento").toLocalDate());
                prestamo.setEstado(rs.getBoolean("estado"));
                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            System.err.println("Error listando préstamos: " + e.getMessage());
            e.printStackTrace();
        }
        return prestamos;
    }

    @Override
    public void actualizar(Prestamo prestamo) {
        String sql = "UPDATE Prestamo SET idAlumno = ?, idAdministrativo = ?, fechaPrestamo = ?, fechaVencimiento = ?, estado = ? WHERE idPrestamo = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, prestamo.getIdAlumno());
            pstmt.setInt(2, prestamo.getIdAdministrativo());
            pstmt.setDate(3, Date.valueOf(prestamo.getFechaPrestamo()));
            pstmt.setDate(4, Date.valueOf(prestamo.getFechaVencimiento()));
            pstmt.setBoolean(5, prestamo.isEstado());
            pstmt.setInt(6, prestamo.getIdPrestamo());
            pstmt.executeUpdate();
            System.out.println("Préstamo actualizado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error actualizando préstamo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Prestamo WHERE idPrestamo = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Préstamo eliminado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error eliminando préstamo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Prestamo> listarPorAlumno(int idAlumno) {
        String sql = "SELECT * FROM Prestamo WHERE idAlumno = ? AND estado = 1";
        List<Prestamo> prestamos = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAlumno);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setIdPrestamo(rs.getInt("idPrestamo"));
                prestamo.setIdAlumno(rs.getInt("idAlumno"));
                prestamo.setIdAdministrativo(rs.getInt("idAdministrativo"));
                prestamo.setFechaPrestamo(rs.getDate("fechaPrestamo").toLocalDate());
                prestamo.setFechaVencimiento(rs.getDate("fechaVencimiento").toLocalDate());
                prestamo.setEstado(rs.getBoolean("estado"));
                prestamos.add(prestamo);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error listando préstamos por alumno: " + e.getMessage());
            e.printStackTrace();
        }
        return prestamos;
    }
}