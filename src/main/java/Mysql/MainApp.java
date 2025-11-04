package Mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Mysql.Utilidades.MySQL;
public class MainApp
{
    private static Connection conexion = MySQL.establecerConexion();

    public static void main(String[] args)
    {
        insertar("Juan");
        insertar("Pedro");
        insertar("Antonio");
        insertar("Pablo");
        insertar("Carlos");

        listar();

        actualizar(1,"Don Pepito");
        actualizar(5,"Don Jose");

        eliminar(2);

        MySQL.cerrarConexion();

    }

    private static void insertar(String nombre)
    {

        PreparedStatement sentencia = null;

        try {

            sentencia = conexion.prepareStatement("INSERT INTO personas (nombre) VALUES (?)");
            sentencia.setString(1, nombre);
            int row = sentencia.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally
        {
            try
            {
                sentencia.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void listar()
    {
        Statement sentencia = null;
        ResultSet filas = null;

        try {
            String sentenciaStr = "SELECT id, nombre FROM personas";
            sentencia = conexion.createStatement();
            filas = sentencia.executeQuery(sentenciaStr);
            while (filas.next()) {
                String id = filas.getString(1);
                String nombre = filas.getString(2);
                System.out.println(id+" | "+nombre);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally
        {
            try
            {
                filas.close();
                sentencia.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void actualizar(int id, String nombre)
    {
        PreparedStatement sentencia = null;

        try {

            sentencia = conexion.prepareStatement("UPDATE personas SET nombre=? WHERE id=?");
            sentencia.setString(1, nombre);
            sentencia.setInt(2, id);
            int row = sentencia.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally
        {
            try
            {
                sentencia.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void eliminar(int id)
    {
        PreparedStatement sentencia = null;

        try {

            sentencia = conexion.prepareStatement("DELETE FROM personas WHERE id=?");
            sentencia.setInt(1, id);;
            int row = sentencia.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally
        {
            try
            {
                sentencia.close();
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
