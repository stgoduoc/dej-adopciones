package cl.duoc.dej.adopciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Santiago Neira <sant.neira@profesor.duoc.cl>
 */
public class Conexion {

    private static Connection connection;

    public static Connection getConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            properties.put("user", "root");
            properties.put("password", "Duocadmin2017");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/adopcion", properties);
            return connection;
        } catch (ClassNotFoundException cnfe) {
            System.err.println("No se encontró el Driver para MySQL");
        } catch (SQLException se) {
            System.err.println(se.getMessage());
        }
        return null;
    }

}
