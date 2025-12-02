package com.biblioteca.repository;

import com.biblioteca.interfaces.CRUDOperations;
import com.biblioteca.model.DataContacto;
import com.biblioteca.util.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataContactoRepository implements CRUDOperations<DataContacto> {

    @Override
    public void registrar(DataContacto dataContacto) {
        String sql = "INSERT INTO DataContacto (idPersona, idTipoContacto, dato, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, dataContacto.getIdPersona());
            pstmt.setInt(2, dataContacto.getIdTipoContacto());
            pstmt.setString(3, dataContacto.getDato());
            pstmt.setBoolean(4, dataContacto.isEstado());
            pstmt.executeUpdate();
            System.out.println("Dato de contacto registrado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error registrando dato de contacto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public DataContacto buscar(int id) {
        String sql = "SELECT * FROM DataContacto WHERE idPersonaContacto = ?";
        DataContacto dataContacto = null;
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                dataContacto = new DataContacto();
                dataContacto.setIdPersonaContacto(rs.getInt("idPersonaContacto"));
                dataContacto.setIdPersona(rs.getInt("idPersona"));
                dataContacto.setIdTipoContacto(rs.getInt("idTipoContacto"));
                dataContacto.setDato(rs.getString("dato"));
                dataContacto.setEstado(rs.getBoolean("estado"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error buscando dato de contacto: " + e.getMessage());
            e.printStackTrace();
        }
        return dataContacto;
    }

    @Override
    public List<DataContacto> listar() {
        String sql = "SELECT * FROM DataContacto WHERE estado = 1";
        List<DataContacto> datosContacto = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                DataContacto dataContacto = new DataContacto();
                dataContacto.setIdPersonaContacto(rs.getInt("idPersonaContacto"));
                dataContacto.setIdPersona(rs.getInt("idPersona"));
                dataContacto.setIdTipoContacto(rs.getInt("idTipoContacto"));
                dataContacto.setDato(rs.getString("dato"));
                dataContacto.setEstado(rs.getBoolean("estado"));
                datosContacto.add(dataContacto);
            }
        } catch (SQLException e) {
            System.err.println("Error listando datos de contacto: " + e.getMessage());
            e.printStackTrace();
        }
        return datosContacto;
    }

    @Override
    public void actualizar(DataContacto dataContacto) {
        String sql = "UPDATE DataContacto SET idPersona = ?, idTipoContacto = ?, dato = ?, estado = ? WHERE idPersonaContacto = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, dataContacto.getIdPersona());
            pstmt.setInt(2, dataContacto.getIdTipoContacto());
            pstmt.setString(3, dataContacto.getDato());
            pstmt.setBoolean(4, dataContacto.isEstado());
            pstmt.setInt(5, dataContacto.getIdPersonaContacto());
            pstmt.executeUpdate();
            System.out.println("Dato de contacto actualizado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error actualizando dato de contacto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "UPDATE DataContacto SET estado = 0 WHERE idPersonaContacto = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Dato de contacto eliminado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error eliminando dato de contacto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<DataContacto> buscarPorPersona(int idPersona) {
        String sql = "SELECT * FROM DataContacto WHERE idPersona = ? AND estado = 1";
        List<DataContacto> datosContacto = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPersona);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                DataContacto dataContacto = new DataContacto();
                dataContacto.setIdPersonaContacto(rs.getInt("idPersonaContacto"));
                dataContacto.setIdPersona(rs.getInt("idPersona"));
                dataContacto.setIdTipoContacto(rs.getInt("idTipoContacto"));
                dataContacto.setDato(rs.getString("dato"));
                dataContacto.setEstado(rs.getBoolean("estado"));
                datosContacto.add(dataContacto);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error buscando datos de contacto por persona: " + e.getMessage());
            e.printStackTrace();
        }
        return datosContacto;
    }
}