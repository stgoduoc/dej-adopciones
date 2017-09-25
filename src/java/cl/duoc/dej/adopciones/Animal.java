package cl.duoc.dej.adopciones;

/**
 *
 * @author Santiago Neira <sant.neira@profesor.duoc.cl>
 */
public class Animal {
    
    private int id = 0;
    private String nombre;
    private String tipo;
    private String genero;
    private int edad;
    private boolean adoptado = false;

    public Animal() {
    }

    public Animal(String nombre, String tipo, String genero, int edad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.genero = genero;
        this.edad = edad;
    }

    public Animal(int id, String nombre, String tipo, String genero, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.genero = genero;
        this.edad = edad;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isAdoptado() {
        return adoptado;
    }

    public void setAdoptado(boolean adoptado) {
        this.adoptado = adoptado;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    
    
}
