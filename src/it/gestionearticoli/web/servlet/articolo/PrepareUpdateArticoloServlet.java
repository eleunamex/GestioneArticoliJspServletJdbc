package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.service.MyServiceFactory;
import it.gestionearticoli.service.articolo.ArticoloService;

/**
 * Servlet implementation class PrepareUpdateArticoloServlet
 */
@WebServlet("/PrepareUpdateArticoloServlet")
public class PrepareUpdateArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parametroIdArticoloDaModificareString = request.getParameter("idDaInviareComeParametro");

		String contextPath = request.getContextPath() + "/ListArticoliServlet";
		Long parametroIdArticoloDaModificare;

		if (parametroIdArticoloDaModificareString == null || parametroIdArticoloDaModificareString.isEmpty()) {
			response.sendRedirect(contextPath);
			return;
		} else {
			try {
				parametroIdArticoloDaModificare = Long.parseLong(request.getParameter("idDaInviareComeParametro"));
			} catch (NumberFormatException nfe) {
				response.sendRedirect(contextPath);
				return;
			}
		}

		ArticoloService service = MyServiceFactory.getArticoloServiceInstance();

		Articolo result = new Articolo();
		List<Categoria> listaCategorie = new ArrayList<Categoria>();

		try {
			result = service.findById(parametroIdArticoloDaModificare);
			listaCategorie = MyServiceFactory.getCategoriaServiceInstance().listAll();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("articoloDaInviareAPaginaModifica", result);
		request.setAttribute("listaCategorieAttribute", listaCategorie);

		RequestDispatcher dispatcher = request.getRequestDispatcher("articolo/update_articolo.jsp");
		dispatcher.forward(request, response);
	}

}
