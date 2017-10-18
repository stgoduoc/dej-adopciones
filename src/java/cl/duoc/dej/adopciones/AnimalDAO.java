package cl.duoc.dej.adopciones;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String sql = "INSERT INTO adopciones(nombre, tipo, genero, edad, fecha_nacimiento) VALUES(?, ?, ?, ?, ?);";
        PreparedStatement prepareStatement = null;
        try {
            prepareStatement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // reemplaza los parametros del statement preparado
            prepareStatement.setString(1, a.getNombre());
            prepareStatement.setString(2, a.getTipo());
            prepareStatement.setString(3, a.getGenero());
            prepareStatement.setInt(4, a.getEdad());
            prepareStatement.setDate(5, new java.sql.Date(a.getFechaNacimiento().getTimeInMillis()));
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
                int edad = rs.getInt("edad");
                
                Date dateFechaNacimiento = rs.getDate("fecha_nacimiento");
                Calendar calendarFechaNacimiento = Calendar.getInstance();
                calendarFechaNacimiento.setTimeInMillis(dateFechaNacimiento.getTime());
                
                Animal animal = new Animal(id, nombre, tipo, genero, edad, calendarFechaNacimiento);
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
                    + "	, edad INT NOT NULL\n"
                    + "	, adoptado BOOLEAN NOT NULL DEFAULT FALSE\n"
                    + " , fecha_nacimiento DATE NOT NULL"
                    + ");";
            PreparedStatement prepareStatement = conexion.prepareStatement(sql);
            prepareStatement.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    List<Animal> buscarAnimales(String nombreBuscado) {
        Logger logger = Logger.getLogger(this.getClass().getSimpleName());
        try {
            PreparedStatement prepareStatement = 
                    conexion.prepareStatement("SELECT * FROM adopciones WHERE LOWER(nombre) LIKE ? ORDER BY id DESC");
            prepareStatement.setString(1, "%"+nombreBuscado.toLowerCase()+"%");
            ResultSet rs = prepareStatement.executeQuery();
            List<Animal> listaAnimales = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String genero = rs.getString("genero");
                String tipo = rs.getString("tipo");
                int edad = rs.getInt("edad");
                
                Date dateFechaNacimiento = rs.getDate("fecha_nacimiento");
                Calendar calendarFechaNacimiento = Calendar.getInstance();
                calendarFechaNacimiento.setTimeInMillis(dateFechaNacimiento.getTime());
                
                Animal animal = new Animal(id, nombre, tipo, genero, edad, calendarFechaNacimiento);
                listaAnimales.add(animal);
            }
            logger.log(Level.INFO, String.format("Se encontraron %s animales en la búsqueda."
                        , listaAnimales.size()));
            return listaAnimales;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return null;
    }

}
