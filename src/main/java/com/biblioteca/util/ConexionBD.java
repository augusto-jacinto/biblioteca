package com.biblioteca.util;

import java.sql.*;

public class ConexionBD {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=BibliotecaDB;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASSWORD = "Abc123456";
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    static {
        try {
            Class.forName(DRIVER);
            System.out.println("Driver JDBC cargado correctamente");
        } catch (ClassNotFoundException e) {
            System.err.println("Error cargando el driver JDBC: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConexion() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión a la base de datos exitosa");
            return conn;
        } catch (SQLException e) {
            System.err.println("Error conectando a la base de datos: " + e.getMessage());
            System.err.println("URL: " + URL);
            System.err.println("Usuario: " + USER);
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isConexionValida(Connection conn) {
        if (conn == null) {
            return false;
        }
        try {
            return !conn.isClosed();
        } catch (SQLException e) {
            System.err.println("Error verificando conexión: " + e.getMessage());
            return false;
        }
    }

    public static void cerrarConexion(Connection conn) {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                    System.out.println("Conexión cerrada correctamente");
                }
            } catch (SQLException e) {
                System.err.println("Error cerrando conexión: " + e.getMessage());
            }
        }
    }

    public static void cerrarResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                System.out.println("ResultSet cerrado correctamente");
            } catch (SQLException e) {
                System.err.println("Error cerrando ResultSet: " + e.getMessage());
            }
        }
    }

    public static void cerrarStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
                System.out.println("Statement cerrado correctamente");
            } catch (SQLException e) {
                System.err.println("Error cerrando Statement: " + e.getMessage());
            }
        }
    }

    public static void cerrarRecursos(ResultSet rs, Statement stmt, Connection conn) {
        cerrarResultSet(rs);
        cerrarStatement(stmt);
        cerrarConexion(conn);
    }

    public static void cerrarRecursos(Statement stmt, Connection conn) {
        cerrarStatement(stmt);
        cerrarConexion(conn);
    }

    public static void cerrarRecursos(Connection conn) {
        cerrarConexion(conn);
    }
}