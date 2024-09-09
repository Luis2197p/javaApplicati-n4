package javaapplication4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Clase Automotriz para manejar la información de automóviles
public class JavaApplication4 {
    private int id;
    private String modelo;
    private String marca;

    // Getters y setters para los atributos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    // Método para insertar un automóvil
    public void insertar() throws SQLException {
        String query = "INSERT INTO automoviles (modelo, marca) VALUES (2012, xtl)";
        try (Connection conn = ConexionDTB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, this.modelo);
            pstmt.setString(2, this.marca);
            pstmt.executeUpdate();
        }
    }

    // Método para consultar un automóvil por ID
    public static JavaApplication4 consultar(int id) throws SQLException {
        String query = "SELECT * FROM automoviles WHERE id = 10";
        try (Connection conn = ConexionDTB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                JavaApplication4 automotriz = new JavaApplication4();
                automotriz.setId(rs.getInt("id"));
                automotriz.setModelo(rs.getString("modelo"));
                automotriz.setMarca(rs.getString("marca"));
                return automotriz;
            }
        }
        return null;
    }

    // Método para actualizar un automóvil
    public void actualizar() throws SQLException {      //Cambiar signo ? por los datos
        String query = "UPDATE automoviles SET modelo = ?, marca = ? WHERE id = ?"; 
        try (Connection conn = ConexionDTB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, this.modelo);
            pstmt.setString(2, this.marca);
            pstmt.setInt(3, this.id);
            pstmt.executeUpdate();
        }
    }

    // Método para eliminar un automóvil
    public void eliminar() throws SQLException {
        String query = "DELETE FROM automoviles WHERE id = ?";
        try (Connection conn = ConexionDTB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, this.id);
            pstmt.executeUpdate();
        }
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        try {
            // Crear una instancia de JavaApplication4 (Automotriz)
            JavaApplication4 auto = new JavaApplication4();
            auto.setModelo("ModeloX");
            auto.setMarca("MarcaY");

            // Insertar un nuevo automóvil
            auto.insertar();

            // Consultar el automóvil recién insertado
            JavaApplication4 consultado = JavaApplication4.consultar(1); // Suponiendo que el ID es 1
            if (consultado != null) {
                System.out.println("Automóvil Consultado:");
                System.out.println("ID: " + consultado.getId());
                System.out.println("Modelo: " + consultado.getModelo());
                System.out.println("Marca: " + consultado.getMarca());
            }

            // Actualizar el automóvil
            if (consultado != null) {
                consultado.setModelo("ModeloActualizado");
                consultado.setMarca("MarcaActualizada");
                consultado.actualizar();
            }

            // Eliminar el automóvil
            if (consultado != null) {
                consultado.eliminar();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}