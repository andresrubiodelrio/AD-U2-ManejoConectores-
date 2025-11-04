package Mysql.Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private static final String HOST = "localhost:3306";
    private static final String BBDD = "progdam";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";

    private static Connection conexion = null;

    private MySQL() {
        //Evitamos que se creen instancias
    }

    public static Connection establecerConexion()
    {
        if (conexion != null) {
            return conexion;
        }

        try {
            conexion = DriverManager.getConnection("jdbc:mysql://"+HOST+"/"+BBDD,USUARIO,CONTRASENA);
            System.out.println("Conexión a MySQL realizada correctamente.");
        } catch (SQLException e) {
            System.out.println("ERROR MySQL:  "+ e.toString());
        }
        return conexion;
    }

    public static void cerrarConexion() {
        try {
            if (conexion != null) {
                conexion.close();
                conexion = null;
                System.out.println("Conexión a MySQL cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR MySQL: "+ e.toString());
        }
    }

}