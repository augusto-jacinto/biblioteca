package com.biblioteca.repository;

import com.biblioteca.interfaces.CRUDOperations;
import com.biblioteca.model.Alumno;
import com.biblioteca.util.ConexionBD;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlumnoRepository implements CRUDOperations<Alumno> {

    @Override
    public void registrar(Alumno alumno) {
        String sqlPersona = "INSERT INTO Persona (nombres, apellidos, fechaNacimiento, numeroDocumento, direccion, nacionalidad, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlAlumno = "INSERT INTO Alumno (idPersona, codigoAlumno, estado) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBD.getConexion()) {
            conn.setAutoCommit(false);
            try (PreparedStatement pstmtPersona = conn.prepareStatement(sqlPersona, Statement.RETURN_GENERATED_KEYS)) {
                pstmtPersona.setString(1, alumno.getNombres());
                pstmtPersona.setString(2, alumno.getApellidos());
                pstmtPersona.setDate(3, Date.valueOf(alumno.getFechaNacimiento()));
                pstmtPersona.setString(4, alumno.getNumeroDocumento());
                pstmtPersona.setString(5, alumno.getDireccion());
                pstmtPersona.setString(6, alumno.getNacionalidad());
                pstmtPersona.setBoolean(7, alumno.isEstado());
                pstmtPersona.executeUpdate();
                try (ResultSet rs = pstmtPersona.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idPersona = rs.getInt(1);
                        try (PreparedStatement pstmtAlumno = conn.prepareStatement(sqlAlumno)) {
                            pstmtAlumno.setInt(1, idPersona);
                            pstmtAlumno.setString(2, alumno.getCodigoAlumno());
                            pstmtAlumno.setBoolean(3, alumno.isEstado());
                            pstmtAlumno.executeUpdate();
                        }
                    }
                }
                conn.commit();
                System.out.println("Alumno registrado exitosamente");
            } catch (SQLException e) {
                System.err.println("Error registrando alumno: " + e.getMessage());
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Error registrando alumno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Alumno buscar(int id) {
        String sql = "SELECT p.*, a.idAlumno, a.codigoAlumno FROM Alumno a JOIN Persona p ON a.idPersona = p.idPersona WHERE a.idAlumno = ?";
        Alumno alumno = null;
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setIdPersona(rs.getInt("idPersona"));
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setNombres(rs.getString("nombres"));
                alumno.setApellidos(rs.getString("apellidos"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
                alumno.setNumeroDocumento(rs.getString("numeroDocumento"));
                alumno.setDireccion(rs.getString("direccion"));
                alumno.setNacionalidad(rs.getString("nacionalidad"));
                alumno.setEstado(rs.getBoolean("estado"));
                alumno.setCodigoAlumno(rs.getString("codigoAlumno"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error buscando alumno: " + e.getMessage());
            e.printStackTrace();
        }
        return alumno;
    }

    @Override
    public List<Alumno> listar() {
        String sql = "SELECT p.*, a.idAlumno, a.codigoAlumno FROM Alumno a JOIN Persona p ON a.idPersona = p.idPersona WHERE p.estado = 1 AND a.estado = 1";
        List<Alumno> alumnos = new ArrayList<>();
        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdPersona(rs.getInt("idPersona"));
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setNombres(rs.getString("nombres"));
                alumno.setApellidos(rs.getString("apellidos"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
                alumno.setNumeroDocumento(rs.getString("numeroDocumento"));
                alumno.setDireccion(rs.getString("direccion"));
                alumno.setNacionalidad(rs.getString("nacionalidad"));
                alumno.setEstado(rs.getBoolean("estado"));
                alumno.setCodigoAlumno(rs.getString("codigoAlumno"));
                alumnos.add(alumno);
            }
        } catch (SQLException e) {
            System.err.println("Error listando alumnos: " + e.getMessage());
            e.printStackTrace();
        }
        return alumnos;
    }

    @Override
    public void actualizar(Alumno alumno) {
        String sqlPersona = "UPDATE Persona SET nombres = ?, apellidos = ?, fechaNacimiento = ?, numeroDocumento = ?, direccion = ?, nacionalidad = ? WHERE idPersona = ?";
        String sqlAlumno = "UPDATE Alumno SET codigoAlumno = ? WHERE idAlumno = ?";
        try (Connection conn = ConexionBD.getConexion()) {
            conn.setAutoCommit(false);
            try (PreparedStatement pstmtPersona = conn.prepareStatement(sqlPersona)) {
                pstmtPersona.setString(1, alumno.getNombres());
                pstmtPersona.setString(2, alumno.getApellidos());
                pstmtPersona.setDate(3, Date.valueOf(alumno.getFechaNacimiento()));
                pstmtPersona.setString(4, alumno.getNumeroDocumento());
                pstmtPersona.setString(5, alumno.getDireccion());
                pstmtPersona.setString(6, alumno.getNacionalidad());
                pstmtPersona.setInt(7, alumno.getIdPersona());
                pstmtPersona.executeUpdate();
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sqlAlumno)) {
                pstmt.setString(1, alumno.getCodigoAlumno());
                pstmt.setInt(2, alumno.getIdAlumno());
                pstmt.executeUpdate();
            }
            conn.commit();
            System.out.println("Alumno actualizado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error actualizando alumno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "UPDATE Alumno SET estado = 0 WHERE idAlumno = ?";
        String sqlPersona = "UPDATE Persona SET estado = 0 WHERE idPersona = (SELECT idPersona FROM Alumno WHERE idAlumno = ?)";
        try (Connection conn = ConexionBD.getConexion()) {
            conn.setAutoCommit(false);
            try (PreparedStatement pstmt1 = conn.prepareStatement(sql);
                 PreparedStatement pstmt2 = conn.prepareStatement(sqlPersona)) {
                pstmt1.setInt(1, id);
                pstmt1.executeUpdate();
                pstmt2.setInt(1, id);
                pstmt2.executeUpdate();
                conn.commit();
                System.out.println("Alumno eliminado exitosamente");
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            System.err.println("Error eliminando alumno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Alumno buscarPorCodigo(String codigoAlumno) {
        String sql = "SELECT p.*, a.idAlumno, a.codigoAlumno FROM Alumno a JOIN Persona p ON a.idPersona = p.idPersona WHERE a.codigoAlumno = ? AND p.estado = 1";
        Alumno alumno = null;
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigoAlumno);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setIdPersona(rs.getInt("idPersona"));
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setNombres(rs.getString("nombres"));
                alumno.setApellidos(rs.getString("apellidos"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setFechaRegistro(rs.getDate("fechaRegistro").toLocalDate());
                alumno.setNumeroDocumento(rs.getString("numeroDocumento"));
                alumno.setDireccion(rs.getString("direccion"));
                alumno.setNacionalidad(rs.getString("nacionalidad"));
                alumno.setEstado(rs.getBoolean("estado"));
                alumno.setCodigoAlumno(rs.getString("codigoAlumno"));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("Error buscando alumno por código: " + e.getMessage());
        }
        return alumno;
    }
}