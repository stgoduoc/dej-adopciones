package cl.duoc.dej.adopciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Santiago Neira <sant.neira@profesor.duoc.cl>
 */
public class AnimalDAO {

    Connection conexion;

    public AnimalDAO() {
        conexion = Conexion.getConexion();
    }

    public boolean eliminarAnimal(int id) {
        String sql = "DELETE FROM adopciones WHERE id = ?";
        try {
            PreparedStatement prepareStatement = conexion.prepareStatement(sql);
            prepareStatement.setInt(1, id);
            prepareStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public Animal crearAnimal(Animal a) {
        String sql = "INSERT INTO adopciones(nombre, tipo, genero, nacimiento, fecha_creacion, adoptado) VALUES(?, ?, ?, ?, ?, ?);";
        PreparedStatement prepareStatement = null;
        try {
            prepareStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // reemplaza los parametros del statement preparado
            prepareStatement.setString(1, a.getNombre());
            prepareStatement.setString(2, a.getTipo());
            prepareStatement.setString(3, a.getGenero());
            prepareStatement.setDate(4, new java.sql.Date(a.getFechaNacimiento().getTimeInMillis()));
            prepareStatement.setTimestamp(5, new Timestamp(a.getFechaCreacion().getTimeInMillis()) );
            prepareStatement.setBoolean(6, a.isAdoptado());
            // ejecuta
            prepareStatement.executeUpdate();
            // recupera
            ResultSet generatedKeys = prepareStatement.getGeneratedKeys();

            int id = -1;
            while (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
            a.setId(id);

            return a;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            } catch (Exception e) {

            }
        }
        return null;
    }

    public List<Animal> getAnimales() {
        try {
            PreparedStatement prepareStatement = conexion.prepareStatement("SELECT * FROM adopciones ORDER BY id DESC");
            ResultSet rs = prepareStatement.executeQuery();
            List<Animal> listaAnimales = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String genero = rs.getString("genero");
                String tipo = rs.getString("tipo");
                Calendar fechaNacimiento = Calendar.getInstance();
                fechaNacimiento.setTimeInMillis( rs.getDate("nacimiento").getTime() );
                Calendar fechaCreacion = Calendar.getInstance();
                fechaCreacion.setTimeInMillis(rs.getTimestamp("fecha_creacion").getTime());
                boolean adoptado = rs.getBoolean("adoptado");
                Animal animal = new Animal(id, nombre, tipo, genero, fechaNacimiento, fechaCreacion, adoptado);
                listaAnimales.add(animal);
            }
            return listaAnimales;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public boolean borrarTablas() {
        try {
            String sql = "DROP TABLE adopciones";
            PreparedStatement prepareStatement = conexion.prepareStatement(sql);
            prepareStatement.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean crearTablas() {
        try {
            String sql = "CREATE TABLE adopciones(\n"
                    + "	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT\n"
                    + "	, nombre VARCHAR(255) NOT NULL\n"
                    + "	, tipo VARCHAR(255) NOT NULL\n"
                    + "	, genero VARCHAR(255) NOT NULL\n"
                    + "	, nacimiento DATE NOT NULL\n"
                    + "	, fecha_creacion TIMESTAMP NOT NULL\n"
                    + "	, adoptado BOOLEAN NOT NULL DEFAULT FALSE\n"
                    + ");";
            PreparedStatement prepareStatement = conexion.prepareStatement(sql);
            prepareStatement.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

}
