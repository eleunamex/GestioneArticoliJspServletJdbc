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
 * Servlet implementation class ConfermaDeleteArticoloServlet
 */
@WebServlet("/ConfermaDeleteArticoloServlet")
public class ConfermaDeleteArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parametroIdArticoloDaVisualizzare = request.getParameter("idDaInviareComeParametro");

		ArticoloService service = MyServiceFactory.getArticoloServiceInstance();

		Articolo result = new Articolo();

		try {
			result = service.findById(Long.parseLong(parametroIdArticoloDaVisualizzare));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("articoloDaInviareAPaginaConfermaElimina", result);

		RequestDispatcher dispatcher = request.getRequestDispatcher("conferma.jsp");
		dispatcher.forward(request, response);
	}

}
