package com.biblioteca.repository;

import com.biblioteca.interfaces.CRUDOperations;
import com.biblioteca.model.TipoContacto;
import com.biblioteca.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoContactoRepository implements CRUDOperations<TipoContacto> {

    @Override
    public void registrar(TipoContacto tipoContacto) {
        String sql = "INSERT INTO TipoContacto (nombre, estado) VALUES (?, ?)";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tipoContacto.getNombre());
            pstmt.setBoolean(2, tipoContacto.isEstado());
            pstmt.executeUpdate();
            System.out.println("Tipo de contacto registrado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error registrando tipo de contacto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public TipoContacto buscar(int id) {
        String sql = "SELECT * FROM TipoContacto WHERE idTipoContacto = ?";
        TipoContacto tipoContacto = null;
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                tipoContacto = new TipoContacto();
                tipoContacto.setIdTipoContacto(rs.getInt("idTipoContacto"));
                tipoContacto.setNombre(rs.getString("nombre"));
                tipoContacto.setEstado(rs.getBoolean("estado"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error buscando tipo de contacto: " + e.getMessage());
            e.printStackTrace();
        }
        return tipoContacto;
    }

    @Override
    public List<TipoContacto> listar() {
        String sql = "SELECT * FROM TipoContacto WHERE estado = 1";
        List<TipoContacto> tiposContacto = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                TipoContacto tipoContacto = new TipoContacto();
                tipoContacto.setIdTipoContacto(rs.getInt("idTipoContacto"));
                tipoContacto.setNombre(rs.getString("nombre"));
                tipoContacto.setEstado(rs.getBoolean("estado"));
                tiposContacto.add(tipoContacto);
            }
        } catch (SQLException e) {
            System.err.println("Error listando tipos de contacto: " + e.getMessage());
            e.printStackTrace();
        }
        return tiposContacto;
    }

    @Override
    public void actualizar(TipoContacto tipoContacto) {
        String sql = "UPDATE TipoContacto SET nombre = ?, estado = ? WHERE idTipoContacto = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tipoContacto.getNombre());
            pstmt.setBoolean(2, tipoContacto.isEstado());
            pstmt.setInt(3, tipoContacto.getIdTipoContacto());
            pstmt.executeUpdate();
            System.out.println("Tipo de contacto actualizado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error actualizando tipo de contacto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM TipoContacto WHERE idTipoContacto = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Tipo de contacto eliminado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error eliminando tipo de contacto: " + e.getMessage());
            e.printStackTrace();
        }
    }
}