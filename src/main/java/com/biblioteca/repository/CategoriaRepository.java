package com.biblioteca.repository;

import com.biblioteca.interfaces.CRUDOperations;
import com.biblioteca.model.Categoria;
import com.biblioteca.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepository implements CRUDOperations<Categoria> {

    @Override
    public void registrar(Categoria categoria) {
        String sql = "INSERT INTO Categoria (nombre, descripcion, estado) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoria.getNombre());
            pstmt.setString(2, categoria.getDescripcion());
            pstmt.setBoolean(3, categoria.isEstado());
            pstmt.executeUpdate();
            System.out.println("Categoría registrada exitosamente");
        } catch (SQLException e) {
            System.err.println("Error registrando categoría: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Categoria buscar(int id) {
        String sql = "SELECT * FROM Categoria WHERE idCategoria = ?";
        Categoria categoria = null;
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));
                categoria.setEstado(rs.getBoolean("estado"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error buscando categoría: " + e.getMessage());
            e.printStackTrace();
        }
        return categoria;
    }

    @Override
    public List<Categoria> listar() {
        String sql = "SELECT * FROM Categoria WHERE estado = 1";
        List<Categoria> categorias = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));
                categoria.setEstado(rs.getBoolean("estado"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            System.err.println("Error listando categorías: " + e.getMessage());
            e.printStackTrace();
        }
        return categorias;
    }

    @Override
    public void actualizar(Categoria categoria) {
        String sql = "UPDATE Categoria SET nombre = ?, descripcion = ?, estado = ? WHERE idCategoria = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoria.getNombre());
            pstmt.setString(2, categoria.getDescripcion());
            pstmt.setBoolean(3, categoria.isEstado());
            pstmt.setInt(4, categoria.getIdCategoria());
            pstmt.executeUpdate();
            System.out.println("Categoría actualizada exitosamente");
        } catch (SQLException e) {
            System.err.println("Error actualizando categoría: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "UPDATE Categoria SET estado = 0 WHERE idCategoria = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Categoría eliminada exitosamente");
        } catch (SQLException e) {
            System.err.println("Error eliminando categoría: " + e.getMessage());
            e.printStackTrace();
        }
    }
}