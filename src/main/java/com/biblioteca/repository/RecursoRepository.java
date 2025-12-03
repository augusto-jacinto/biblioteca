package com.biblioteca.repository;

import com.biblioteca.interfaces.CRUDOperations;
import com.biblioteca.model.Recurso;
import com.biblioteca.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecursoRepository implements CRUDOperations<Recurso> {

    @Override
    public void registrar(Recurso recurso) {
        String sql = "INSERT INTO Recurso (idCategoria, titulo, autor, isbn, cantidadDisponible, cantidadTotal, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, recurso.getIdCategoria());
            pstmt.setString(2, recurso.getTitulo());
            pstmt.setString(3, recurso.getAutor());
            pstmt.setString(4, recurso.getIsbn());
            pstmt.setInt(5, recurso.getCantidadDisponible());
            pstmt.setInt(6, recurso.getCantidadTotal());
            pstmt.setBoolean(7, recurso.isEstado());
            pstmt.executeUpdate();
            System.out.println("Recurso registrado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error registrando recurso: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Recurso buscar(int id) {
        String sql = "SELECT * FROM Recurso WHERE idRecurso = ?";
        Recurso recurso = null;
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                recurso = new Recurso();
                recurso.setIdRecurso(rs.getInt("idRecurso"));
                recurso.setIdCategoria(rs.getInt("idCategoria"));
                recurso.setTitulo(rs.getString("titulo"));
                recurso.setAutor(rs.getString("autor"));
                recurso.setIsbn(rs.getString("isbn"));
                recurso.setCantidadDisponible(rs.getInt("cantidadDisponible"));
                recurso.setCantidadTotal(rs.getInt("cantidadTotal"));
                recurso.setEstado(rs.getBoolean("estado"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error buscando recurso: " + e.getMessage());
            e.printStackTrace();
        }
        return recurso;
    }

    @Override
    public List<Recurso> listar() {
        String sql = "SELECT * FROM Recurso WHERE estado = 1";
        List<Recurso> recursos = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Recurso recurso = new Recurso();
                recurso.setIdRecurso(rs.getInt("idRecurso"));
                recurso.setIdCategoria(rs.getInt("idCategoria"));
                recurso.setTitulo(rs.getString("titulo"));
                recurso.setAutor(rs.getString("autor"));
                recurso.setIsbn(rs.getString("isbn"));
                recurso.setCantidadDisponible(rs.getInt("cantidadDisponible"));
                recurso.setCantidadTotal(rs.getInt("cantidadTotal"));
                recurso.setEstado(rs.getBoolean("estado"));
                recursos.add(recurso);
            }
        } catch (SQLException e) {
            System.err.println("Error listando recursos: " + e.getMessage());
            e.printStackTrace();
        }
        return recursos;
    }

    @Override
    public void actualizar(Recurso recurso) {
        String sql = "UPDATE Recurso SET idCategoria = ?, titulo = ?, autor = ?, isbn = ?, cantidadDisponible = ?, cantidadTotal = ?, estado = ? WHERE idRecurso = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, recurso.getIdCategoria());
            pstmt.setString(2, recurso.getTitulo());
            pstmt.setString(3, recurso.getAutor());
            pstmt.setString(4, recurso.getIsbn());
            pstmt.setInt(5, recurso.getCantidadDisponible());
            pstmt.setInt(6, recurso.getCantidadTotal());
            pstmt.setBoolean(7, recurso.isEstado());
            pstmt.setInt(8, recurso.getIdRecurso());
            pstmt.executeUpdate();
            System.out.println("Recurso actualizado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error actualizando recurso: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        // Primero verificar si el recurso tiene relaciones con DetallePrestamo
        String sqlVerificar = "SELECT COUNT(*) as total FROM DetallePrestamo WHERE idRecurso = ?";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmtVerificar = conn.prepareStatement(sqlVerificar)) {

            pstmtVerificar.setInt(1, id);
            ResultSet rs = pstmtVerificar.executeQuery();

            if (rs.next()) {
                int totalRelaciones = rs.getInt("total");

                if (totalRelaciones > 0) {
                    System.err.println("ADVERTENCIA: No se puede eliminar el recurso con ID " + id);
                    System.err.println("El registro se encuentra relacionado en la tabla DetallePrestamo.");
                    System.err.println("Total de relaciones encontradas: " + totalRelaciones);
                    rs.close();
                    return;
                }
            }
            rs.close();

            // Si no hay relaciones, proceder con la eliminación
            String sql = "DELETE FROM Recurso WHERE idRecurso = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                int filasAfectadas = pstmt.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Recurso eliminado exitosamente");
                } else {
                    System.err.println("No se encontró el recurso con ID " + id);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error eliminando recurso: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Recurso> listarPorCategoria(int idCategoria) {
        String sql = "SELECT * FROM Recurso WHERE idCategoria = ? AND estado = 1";
        List<Recurso> recursos = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCategoria);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Recurso recurso = new Recurso();
                recurso.setIdRecurso(rs.getInt("idRecurso"));
                recurso.setIdCategoria(rs.getInt("idCategoria"));
                recurso.setTitulo(rs.getString("titulo"));
                recurso.setAutor(rs.getString("autor"));
                recurso.setIsbn(rs.getString("isbn"));
                recurso.setCantidadDisponible(rs.getInt("cantidadDisponible"));
                recurso.setCantidadTotal(rs.getInt("cantidadTotal"));
                recurso.setEstado(rs.getBoolean("estado"));
                recursos.add(recurso);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error listando recursos por categoría: " + e.getMessage());
            e.printStackTrace();
        }
        return recursos;
    }
}