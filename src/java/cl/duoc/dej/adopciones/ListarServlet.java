package cl.duoc.dej.adopciones;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ListarServlet", urlPatterns = {"/listar"})
public class ListarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AnimalDAO animalDAO = new AnimalDAO();
        List<Animal> animales = animalDAO.getAnimales();
        req.setAttribute("animales", animales);
        req.getRequestDispatcher("/WEB-INF/jsp/listar.jsp").forward(req, resp);
    }

    
    
}
