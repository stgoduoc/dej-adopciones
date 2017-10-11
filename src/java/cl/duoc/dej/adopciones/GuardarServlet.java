package cl.duoc.dej.adopciones;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Santiago Neira <sant.neira@profesor.duoc.cl>
 */
@WebServlet(name = "GuardarServlet", urlPatterns = {"/GuardarServlet"})
public class GuardarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Logger logger = Logger.getLogger(this.getClass().getSimpleName());

        List<String> mensajes = new ArrayList<>();
        List<String> errores = new ArrayList<>();
        String error = null;

        String nombre = request.getParameter("nombre");
        String tipo = request.getParameter("tipo");
        String genero = request.getParameter("genero");
        String stringFechaNacimiento = request.getParameter("fecha-nacimiento");
        Calendar fechaNacimiento = Calendar.getInstance();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateFechaNacimiento = simpleDateFormat.parse(stringFechaNacimiento);
            fechaNacimiento.setTimeInMillis(dateFechaNacimiento.getTime());
        } catch (ParseException pe) {
            error = "La fecha de nacimiento no cumple el formato esperado";
            errores.add(error);
            // Mensaje hacia el LOG con LEVEL info
            logger.info(error);
        }

        // crea y guarda el animal en BD
        if (errores.size() == 0) {
            Animal a = new Animal(nombre, tipo, genero, fechaNacimiento);
            AnimalDAO animalDAO = new AnimalDAO();
            Animal animalCreado = animalDAO.crearAnimal(a);
            if(animalCreado != null) {
                mensajes.add("Animal creado correctamente");
            }
        } else {
            request.setAttribute("errores", errores);
        }

        // setea mensajes y errores
        request.setAttribute("mensajes", mensajes);
        request.getRequestDispatcher("/crear.jsp").forward(request, response);
    }

}
