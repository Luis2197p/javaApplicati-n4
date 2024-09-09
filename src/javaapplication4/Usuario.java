/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author avila
 */

public class Usuario {
    private int id;
    private String nombre;
    private String email;

    // Getters y setters para los atributos

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método para insertar un usuario
    public void insertar() throws SQLException {
        String query = "INSERT INTO usuario (nombre, email) VALUES (?, ?)";
        try (Connection conn = ConexiónDTB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, this.nombre);
            pstmt.setString(2, this.email);
            pstmt.executeUpdate();
        }
    }

    // Método para consultar un usuario por ID
    public static Usuario consultar(int id) throws SQLException {
        String query = "SELECT * FROM usuarios WHERE id = ?";
        try (Connection conn = ConexiónDTB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
                return usuario;
            }
        }
        return null;
    }

    // Método para actualizar un usuario
    public void actualizar() throws SQLException {
        String query = "UPDATE usuarios SET nombre = ?, email = ? WHERE id = ?";
        try (Connection conn = ConexiónDTB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, this.nombre);
            pstmt.setString(2, this.email);
            pstmt.setInt(3, this.id);
            pstmt.executeUpdate();
        }
    }

    // Método para eliminar un usuario
    public void eliminar() throws SQLException {
        String query = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = ConexiónDTB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, this.id);
            pstmt.executeUpdate();
    }
}

    private static class ConexiónDTB {

        private static Connection getConnection() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        public ConexiónDTB() {
        }
    }
}