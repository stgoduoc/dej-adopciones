package cl.duoc.dej.adopciones;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CrearServlet", urlPatterns = {"/CrearServlet"})
public class CrearServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nombre = req.getParameter("nombre");
            String tipo = req.getParameter("tipo");
            String genero = req.getParameter("genero");
            String stringFechaNacimiento = req.getParameter("fechanacimiento");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = simpleDateFormat.parse(stringFechaNacimiento);
            Calendar fechaNacimiento = Calendar.getInstance();
            fechaNacimiento.setTimeInMillis(parse.getTime());
            
            // validaciones
            
            // lo que sigue para guardar con clase DAO ...
            AnimalDAO animalDAO = new AnimalDAO();
            Animal a = new Animal(nombre, tipo, genero, 0);
            a.setFechaNacimiento(fechaNacimiento);
            a = animalDAO.crearAnimal(a);
            if(a != null && a.getId() > 0) {
                req.setAttribute("mensaje", "El animal se creó correctamente.");
            } else {
                req.setAttribute("mensaje", "Hubo problemas al crear el animal.");
            }
            
            // request dispatch al JSP
            req.getRequestDispatcher("/crear.jsp").forward(req, resp);
        } catch (Exception e) {
            Logger logger = Logger.getLogger(getClass().getSimpleName());
            logger.log(Level.SEVERE, "Se produjo un error al crear el animal");
        }
    }

}
