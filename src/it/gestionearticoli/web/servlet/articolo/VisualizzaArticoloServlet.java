package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.service.MyServiceFactory;
import it.gestionearticoli.service.articolo.ArticoloService;

/**
 * Servlet implementation class VisualizzaArticoloServlet
 */
@WebServlet("/VisualizzaArticoloServlet")
public class VisualizzaArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parametroIdArticoloDaVisualizzareString = request.getParameter("idDaInviareComeParametro");

		String contextPath = request.getContextPath() + "/ListArticoliServlet";
		Long parametroIdArticoloDaVisualizzare;

		if (parametroIdArticoloDaVisualizzareString == null || parametroIdArticoloDaVisualizzareString.isEmpty()) {
			response.sendRedirect(contextPath);
			return;
		} else {
			try {
				parametroIdArticoloDaVisualizzare = Long.parseLong(request.getParameter("idDaInviareComeParametro"));
			} catch (NumberFormatException nfe) {
				response.sendRedirect(contextPath);
				return;
			}
		}

		ArticoloService service = MyServiceFactory.getArticoloServiceInstance();

		Articolo result = new Articolo();

		try {
			result = service.findById(parametroIdArticoloDaVisualizzare);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("articoloDaInviareAPaginaVisualizza", result);

		RequestDispatcher dispatcher = request.getRequestDispatcher("articolo/show_articolo.jsp");
		dispatcher.forward(request, response);
	}

}
