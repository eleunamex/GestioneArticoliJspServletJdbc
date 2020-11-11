package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.service.articolo.ArticoloService;

/**
 * Servlet implementation class DeleteArticoloServlet
 */
@WebServlet("/DeleteArticoloServlet")
public class DeleteArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parametroIdArticoloDaEliminareString = request.getParameter("idDaInviareComeParametro");

		String contextPath = request.getContextPath() + "/ListArticoliServlet";
		Long parametroIdCategoriaDaEliminare;

		if (parametroIdArticoloDaEliminareString == null || parametroIdArticoloDaEliminareString.isEmpty()) {
			response.sendRedirect(contextPath);
			return;
		} else {
			try {
				parametroIdCategoriaDaEliminare = Long.parseLong(request.getParameter("idDaInviareComeParametro"));
			} catch (NumberFormatException nfe) {
				response.sendRedirect(contextPath);
				return;
			}
		}

		ArticoloService service = it.gestionearticoli.service.MyServiceFactory.getArticoloServiceInstance();

		Articolo articolo = new Articolo();
		articolo.setId(parametroIdCategoriaDaEliminare);

		try {
			service.rimuovi(articolo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("articoloDaInviareAPaginaVisualizza", "ok");
		request.setAttribute("successMessage", "Articolo eliminato");

		RequestDispatcher dispatcher = request.getRequestDispatcher("ListArticoliServlet");

		dispatcher.forward(request, response);
	}

}
