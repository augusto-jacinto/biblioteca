package com;

import com.biblioteca.util.ConexionBD;
import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        System.out.println("Probando conexión a SQL Server...");

        Connection conn = ConexionBD.getConexion();

        if (conn != null) {
            System.out.println("✓ Conexión exitosa!");
            ConexionBD.cerrarConexion(conn);
        } else {
            System.out.println("✗ Error en la conexión");
        }
    }
}