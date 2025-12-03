package com.biblioteca.repository;

import com.biblioteca.interfaces.CRUDOperations;
import com.biblioteca.model.UsuarioSistema;
import com.biblioteca.util.ConexionBD;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioSistemaRepository implements CRUDOperations<UsuarioSistema> {

    @Override
    public void registrar(UsuarioSistema usuario) {
        String sqlPersona = "INSERT INTO Persona (nombres, apellidos, fechaNacimiento, numeroDocumento, direccion, nacionalidad, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlUsuario = "INSERT INTO UsuarioSistema (idPersona, usuario, contrasenaSystem) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBD.getConexion()) {
            conn.setAutoCommit(false);
            try (PreparedStatement pstmtPersona = conn.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS)) {
                pstmtPersona.setString(1, usuario.getNombres());
                pstmtPersona.setString(2, usuario.getApellidos());
                pstmtPersona.setDate(3, Date.valueOf(usuario.getFechaNacimiento()));
                pstmtPersona.setString(4, usuario.getNumeroDocumento());
                pstmtPersona.setString(5, usuario.getDireccion());
                pstmtPersona.setString(6, usuario.getNacionalidad());
                pstmtPersona.setBoolean(7, usuario.isEstado());
                pstmtPersona.executeUpdate();
                try (ResultSet rs = pstmtPersona.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idPersona = rs.getInt(1);
                        try (PreparedStatement pstmtUsuario = conn.prepareStatement(sqlUsuario)) {
                            pstmtUsuario.setInt(1, idPersona);
                            pstmtUsuario.setString(2, usuario.getUsuario());
                            pstmtUsuario.setString(3, usuario.getContrasenaSystem());
                            pstmtUsuario.executeUpdate();
                        }
                    }
                }
                conn.commit();
                System.out.println("Usuario del sistema registrado exitosamente");
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Error registrando usuario del sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public UsuarioSistema buscar(int id) {
        String sql = "SELECT p.*, u.idUsuario, u.usuario, u.contrasenaSystem FROM UsuarioSistema u JOIN Persona p ON u.idPersona = p.idPersona WHERE u.idUsuario = ?";
        UsuarioSistema usuario = null;
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                usuario = new UsuarioSistema();
                usuario.setIdPersona(rs.getInt("idPersona"));
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                usuario.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
                usuario.setNumeroDocumento(rs.getString("numeroDocumento"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setNacionalidad(rs.getString("nacionalidad"));
                usuario.setEstado(rs.getBoolean("estado"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContrasenaSystem(rs.getString("contrasenaSystem"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error buscando usuario del sistema: " + e.getMessage());
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public List<UsuarioSistema> listar() {
        String sql = "SELECT p.*, u.idUsuario, u.usuario, u.contrasenaSystem FROM UsuarioSistema u JOIN Persona p ON u.idPersona = p.idPersona WHERE p.estado = 1";
        List<UsuarioSistema> usuarios = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                UsuarioSistema usuario = new UsuarioSistema();
                usuario.setIdPersona(rs.getInt("idPersona"));
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                usuario.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
                usuario.setNumeroDocumento(rs.getString("numeroDocumento"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setNacionalidad(rs.getString("nacionalidad"));
                usuario.setEstado(rs.getBoolean("estado"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContrasenaSystem(rs.getString("contrasenaSystem"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Error listando usuarios del sistema: " + e.getMessage());
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public void actualizar(UsuarioSistema usuario) {
        String sql = "UPDATE UsuarioSistema SET usuario = ?, contrasenaSystem = ? WHERE idUsuario = ?";
        String sqlPersona = "UPDATE Persona SET nombres = ?, apellidos = ?, fechaNacimiento = ?, numeroDocumento = ?, direccion = ?, nacionalidad = ? WHERE idPersona = ?";
        try (Connection conn = ConexionBD.getConexion()) {
            conn.setAutoCommit(false);
            try (PreparedStatement pstmtPersona = conn.prepareStatement(sqlPersona)) {
                pstmtPersona.setString(1, usuario.getNombres());
                pstmtPersona.setString(2, usuario.getApellidos());
                pstmtPersona.setDate(3, Date.valueOf(usuario.getFechaNacimiento()));
                pstmtPersona.setString(4, usuario.getNumeroDocumento());
                pstmtPersona.setString(5, usuario.getDireccion());
                pstmtPersona.setString(6, usuario.getNacionalidad());
                pstmtPersona.setInt(7, usuario.getIdPersona());
                pstmtPersona.executeUpdate();
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, usuario.getUsuario());
                pstmt.setString(2, usuario.getContrasenaSystem());
                pstmt.setInt(3, usuario.getIdUsuario());
                pstmt.executeUpdate();
            }
            conn.commit();
            System.out.println("Usuario del sistema actualizado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error actualizando usuario del sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sqlUsuario = "DELETE FROM UsuarioSistema WHERE idUsuario = ?";
        String sqlPersona = "DELETE FROM Persona WHERE idPersona = (SELECT idPersona FROM UsuarioSistema WHERE idUsuario = ?)";
        try (Connection conn = ConexionBD.getConexion()) {
            conn.setAutoCommit(false);
            try {
                int idPersona = 0;
                String sqlGetPersona = "SELECT idPersona FROM UsuarioSistema WHERE idUsuario = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sqlGetPersona)) {
                    pstmt.setInt(1, id);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        idPersona = rs.getInt("idPersona");
                    }
                    rs.close();
                }

                try (PreparedStatement pstmt = conn.prepareStatement(sqlUsuario)) {
                    pstmt.setInt(1, id);
                    pstmt.executeUpdate();
                }

                if (idPersona > 0) {
                    String sqlDeletePersona = "DELETE FROM Persona WHERE idPersona = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(sqlDeletePersona)) {
                        pstmt.setInt(1, idPersona);
                        pstmt.executeUpdate();
                    }
                }

                conn.commit();
                System.out.println("Usuario del sistema eliminado exitosamente");
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Error eliminando usuario del sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public UsuarioSistema buscarPorUsuario(String usuario) {
        String sql = "SELECT p.*, u.idUsuario, u.usuario, u.contrasenaSystem FROM UsuarioSistema u JOIN Persona p ON u.idPersona = p.idPersona WHERE u.usuario = ? AND p.estado = 1";
        UsuarioSistema user = null;
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new UsuarioSistema();
                user.setIdPersona(rs.getInt("idPersona"));
                user.setIdUsuario(rs.getInt("idUsuario"));
                user.setNombres(rs.getString("nombres"));
                user.setApellidos(rs.getString("apellidos"));
                user.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                user.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
                user.setNumeroDocumento(rs.getString("numeroDocumento"));
                user.setDireccion(rs.getString("direccion"));
                user.setNacionalidad(rs.getString("nacionalidad"));
                user.setEstado(rs.getBoolean("estado"));
                user.setUsuario(rs.getString("usuario"));
                user.setContrasenaSystem(rs.getString("contrasenaSystem"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error buscando usuario del sistema: " + e.getMessage());
        }
        return user;
    }
}