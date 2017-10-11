package cl.duoc.dej.adopciones;

import java.util.Calendar;

/**
 *
 * @author Santiago Neira <sant.neira@profesor.duoc.cl>
 */
public class Animal {
    
    private int id = 0;
    private String nombre;
    private String tipo;
    private String genero;
    private Calendar fechaNacimiento;
    private Calendar fechaCreacion = Calendar.getInstance();
    private boolean adoptado = false;

    public Animal() {
    }

    public Animal(String nombre, String tipo, String genero, Calendar fechaNacimiento) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public Animal(int id, String nombre, String tipo, String genero, Calendar fechaNacimiento, Calendar fechaCreacion, boolean adoptado) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaCreacion = fechaCreacion;
        this.adoptado = adoptado;
    }

    // getters y setters
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Calendar getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Calendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isAdoptado() {
        return adoptado;
    }

    public void setAdoptado(boolean adoptado) {
        this.adoptado = adoptado;
    }
        
}
