package com.biblioteca.repository;

import com.biblioteca.interfaces.CRUDOperations;
import com.biblioteca.model.Administrativo;
import com.biblioteca.util.ConexionBD;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdministrativoRepository implements CRUDOperations<Administrativo> {

    @Override
    public void registrar(Administrativo administrativo) {
        String sqlPersona = "INSERT INTO Persona (nombres, apellidos, fechaNacimiento, numeroDocumento, direccion, nacionalidad, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlAdministrativo = "INSERT INTO Administrativo (idPersona, codigoAdministrativo, idCargo, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConexion()) {
            conn.setAutoCommit(false);
            try (PreparedStatement pstmtPersona = conn.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS)) {
                pstmtPersona.setString(1, administrativo.getNombres());
                pstmtPersona.setString(2, administrativo.getApellidos());
                pstmtPersona.setDate(3, Date.valueOf(administrativo.getFechaNacimiento()));
                pstmtPersona.setString(4, administrativo.getNumeroDocumento());
                pstmtPersona.setString(5, administrativo.getDireccion());
                pstmtPersona.setString(6, administrativo.getNacionalidad());
                pstmtPersona.setBoolean(7, administrativo.isEstado());
                pstmtPersona.executeUpdate();
                try (ResultSet rs = pstmtPersona.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idPersona = rs.getInt(1);
                        try (PreparedStatement pstmtAdministrativo = conn.prepareStatement(sqlAdministrativo)) {
                            pstmtAdministrativo.setInt(1, idPersona);
                            pstmtAdministrativo.setString(2, administrativo.getCodigoAdministrativo());
                            pstmtAdministrativo.setString(3, administrativo.getIdCargo());
                            pstmtAdministrativo.setBoolean(4, administrativo.isEstado());
                            pstmtAdministrativo.executeUpdate();
                        }
                    }
                }
                conn.commit();
                System.out.println("Administrativo registrado exitosamente");
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Error registrando administrativo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Administrativo buscar(int id) {
        String sql = "SELECT p.*, ad.idAdministrativo, ad.codigoAdministrativo, ad.idCargo FROM Administrativo ad JOIN Persona p ON ad.idPersona = p.idPersona WHERE ad.idAdministrativo = ?";
        Administrativo administrativo = null;
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                administrativo = new Administrativo();
                administrativo.setIdPersona(rs.getInt("idPersona"));
                administrativo.setIdAdministrativo(rs.getInt("idAdministrativo"));
                administrativo.setNombres(rs.getString("nombres"));
                administrativo.setApellidos(rs.getString("apellidos"));
                administrativo.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                administrativo.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
                administrativo.setNumeroDocumento(rs.getString("numeroDocumento"));
                administrativo.setDireccion(rs.getString("direccion"));
                administrativo.setNacionalidad(rs.getString("nacionalidad"));
                administrativo.setEstado(rs.getBoolean("estado"));
                administrativo.setCodigoAdministrativo(rs.getString("codigoAdministrativo"));
                administrativo.setIdCargo(rs.getString("idCargo"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error buscando administrativo: " + e.getMessage());
            e.printStackTrace();
        }
        return administrativo;
    }

    @Override
    public List<Administrativo> listar() {
        String sql = "SELECT p.*, ad.idAdministrativo, ad.codigoAdministrativo, ad.idCargo FROM Administrativo ad JOIN Persona p ON ad.idPersona = p.idPersona WHERE p.estado = 1 AND ad.estado = 1";
        List<Administrativo> administrativos = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Administrativo administrativo = new Administrativo();
                administrativo.setIdPersona(rs.getInt("idPersona"));
                administrativo.setIdAdministrativo(rs.getInt("idAdministrativo"));
                administrativo.setNombres(rs.getString("nombres"));
                administrativo.setApellidos(rs.getString("apellidos"));
                administrativo.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                administrativo.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
                administrativo.setNumeroDocumento(rs.getString("numeroDocumento"));
                administrativo.setDireccion(rs.getString("direccion"));
                administrativo.setNacionalidad(rs.getString("nacionalidad"));
                administrativo.setEstado(rs.getBoolean("estado"));
                administrativo.setCodigoAdministrativo(rs.getString("codigoAdministrativo"));
                administrativo.setIdCargo(rs.getString("idCargo"));
                administrativos.add(administrativo);
            }
        } catch (SQLException e) {
            System.err.println("Error listando administrativos: " + e.getMessage());
            e.printStackTrace();
        }
        return administrativos;
    }

    @Override
    public void actualizar(Administrativo administrativo) {
        String sqlPersona = "UPDATE Persona SET nombres = ?, apellidos = ?, fechaNacimiento = ?, numeroDocumento = ?, direccion = ?, nacionalidad = ? WHERE idPersona = ?";
        String sqlAdministrativo = "UPDATE Administrativo SET codigoAdministrativo = ?, idCargo = ? WHERE idAdministrativo = ?";
        try (Connection conn = ConexionBD.getConexion()) {
            conn.setAutoCommit(false);
            try (PreparedStatement pstmtPersona = conn.prepareStatement(sqlPersona)) {
                pstmtPersona.setString(1, administrativo.getNombres());
                pstmtPersona.setString(2, administrativo.getApellidos());
                pstmtPersona.setDate(3, Date.valueOf(administrativo.getFechaNacimiento()));
                pstmtPersona.setString(4, administrativo.getNumeroDocumento());
                pstmtPersona.setString(5, administrativo.getDireccion());
                pstmtPersona.setString(6, administrativo.getNacionalidad());
                pstmtPersona.setInt(7, administrativo.getIdPersona());
                pstmtPersona.executeUpdate();
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sqlAdministrativo)) {
                pstmt.setString(1, administrativo.getCodigoAdministrativo());
                pstmt.setString(2, administrativo.getIdCargo());
                pstmt.setInt(3, administrativo.getIdAdministrativo());
                pstmt.executeUpdate();
            }
            conn.commit();
            System.out.println("Administrativo actualizado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error actualizando administrativo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "UPDATE Administrativo SET estado = 0 WHERE idAdministrativo = ?";
        String sqlPersona = "UPDATE Persona SET estado = 0 WHERE idPersona = (SELECT idPersona FROM Administrativo WHERE idAdministrativo = ?)";
        try (Connection conn = ConexionBD.getConexion()) {
            conn.setAutoCommit(false);
            try (PreparedStatement pstmt1 = conn.prepareStatement(sql);
                 PreparedStatement pstmt2 = conn.prepareStatement(sqlPersona)) {
                pstmt1.setInt(1, id);
                pstmt1.executeUpdate();
                pstmt2.setInt(1, id);
                pstmt2.executeUpdate();
                conn.commit();
                System.out.println("Administrativo eliminado exitosamente");
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Error eliminando administrativo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Administrativo buscarPorCodigo(String codigoAdministrativo) {
        String sql = "SELECT p.*, ad.idAdministrativo, ad.codigoAdministrativo, ad.idCargo FROM Administrativo ad JOIN Persona p ON ad.idPersona = p.idPersona WHERE ad.codigoAdministrativo = ? AND p.estado = 1";
        Administrativo administrativo = null;
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigoAdministrativo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                administrativo = new Administrativo();
                administrativo.setIdPersona(rs.getInt("idPersona"));
                administrativo.setIdAdministrativo(rs.getInt("idAdministrativo"));
                administrativo.setNombres(rs.getString("nombres"));
                administrativo.setApellidos(rs.getString("apellidos"));
                administrativo.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                administrativo.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
                administrativo.setNumeroDocumento(rs.getString("numeroDocumento"));
                administrativo.setDireccion(rs.getString("direccion"));
                administrativo.setNacionalidad(rs.getString("nacionalidad"));
                administrativo.setEstado(rs.getBoolean("estado"));
                administrativo.setCodigoAdministrativo(rs.getString("codigoAdministrativo"));
                administrativo.setIdCargo(rs.getString("idCargo"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error buscando administrativo por código: " + e.getMessage());
        }
        return administrativo;
    }
}