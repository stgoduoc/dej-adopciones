package cl.duoc.dej.adopciones;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "BorrarServlet", urlPatterns = {"/borrar"})
public class BorrarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringId = req.getParameter("id");
        int id = 0;

        List<String> errores = new ArrayList<>();
        if (stringId == null || stringId.isEmpty()) {
            errores.add("Debe especificar el ID del Animal a borrar!");
        }
        try {
            id = Integer.parseInt(stringId);
        } catch (Exception e) {
            errores.add("El ID entregado es inválido");
        }

        AnimalDAO animalDAO = new AnimalDAO();
        boolean animalEliminado = animalDAO.eliminarAnimal(id);

        if (animalEliminado) {
            req.setAttribute("mensaje", "Animal borrado exitosamente.");
        } else {
            errores.add("Ocurrió un error al eliminar el Animal.");
        }

        req.setAttribute("errores", errores);
        req.setAttribute("animales", animalDAO.getAnimales());
        
        req.getRequestDispatcher("/WEB-INF/jsp/listar.jsp").forward(req, resp);
    }

}
