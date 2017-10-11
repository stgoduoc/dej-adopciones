package cl.duoc.dej.adopciones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Santiago Neira <sant.neira@profesor.duoc.cl>
 */
@WebServlet(name = "InstallServlet", urlPatterns = {"/install"})
public class InstallServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operacion = req.getParameter("operacion") + "";
        switch (operacion) {
            case "instalar":
                boolean resultadoInstalacion = instalar();
                if (resultadoInstalacion) {
                    req.setAttribute("mensaje", "Instalación completada");
                } else {
                    req.setAttribute("mensaje", "La instalación NO se completó.");
                }
                break;

            case "desinstalar":
                boolean resultadoDesinstalacion = desinstalar();
                if (resultadoDesinstalacion) {
                    req.setAttribute("mensaje", "Desinstalación completada");
                } else {
                    req.setAttribute("mensaje", "La desinstalación NO se completó.");
                }
                break;

            case "cargar":
                boolean resultadoCarga = cargar();
                if (resultadoCarga) {
                    req.setAttribute("mensaje", "Carga de datos completada");
                } else {
                    req.setAttribute("mensaje", "Ocurrieron errores al cargar los datos");
                }
                break;
        }
        //req.getRequestDispatcher("install.jsp").forward(req, resp);
    }

    private boolean cargar() {
        List<Animal> animales = new ArrayList<>();
        Calendar fechaNacimiento2 = Calendar.getInstance();
        fechaNacimiento2.add(Calendar.YEAR, -2);
        Calendar fechaNacimiento3 = Calendar.getInstance();
        fechaNacimiento3.add(Calendar.YEAR, -3);
        Calendar fechaNacimiento4 = Calendar.getInstance();
        fechaNacimiento4.add(Calendar.YEAR, -4);
        
        Animal thor = new Animal("Thor", "perro", "macho", fechaNacimiento2);
        thor.setAdoptado(true);
        animales.add(thor);
        animales.add(new Animal("Rocky", "perro", "macho", fechaNacimiento3));
        animales.add(new Animal("Flor", "perro", "hembra", fechaNacimiento4));
        animales.add(new Animal("Chola", "perro", "hembra", fechaNacimiento2));
        animales.add(new Animal("Pelusa", "gato", "hembra", fechaNacimiento4));
        animales.add(new Animal("Niki", "gato", "hembra", fechaNacimiento3));
        animales.add(new Animal("Bellota", "gato", "hembra", fechaNacimiento3));
        animales.add(new Animal("Evelyn", "perro", "hembra", fechaNacimiento4));
        animales.add(new Animal("Doris", "gato", "hembra", fechaNacimiento2));
        animales.add(new Animal("Sebastian", "perro", "macho", fechaNacimiento2));
        animales.add(new Animal("Kiba", "perro", "hembra", fechaNacimiento4));

        AnimalDAO animalDAO = new AnimalDAO();
        for (Animal a : animales) {
            Animal animal = animalDAO.crearAnimal(a);
            if (animal.getId() < 1) {
                System.err.println("Problemas al crear el animal en BD:" + animal.toString());
                return false;
            }
        }
        return true;
    }

    private boolean instalar() {
        AnimalDAO animalDAO = new AnimalDAO();
        return animalDAO.crearTablas();
    }

    private boolean desinstalar() {
        AnimalDAO animalDAO = new AnimalDAO();
        return animalDAO.borrarTablas();
    }

}
