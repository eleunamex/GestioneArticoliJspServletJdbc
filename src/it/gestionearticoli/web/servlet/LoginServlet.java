package it.gestionearticoli.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionearticoli.model.Utente;
import it.gestionearticoli.service.MyServiceFactory;
import it.gestionearticoli.service.utente.UtenteService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String usernameParameter = request.getParameter("username");
		String passwordParameter = request.getParameter("password");

		Utente utente = new Utente();

		UtenteService service = MyServiceFactory.getUtenteServiceInstance();

		try {
			utente = service.autenticazione(usernameParameter, passwordParameter);
			if (utente != null) {
				HttpSession oldSession = request.getSession(false);
				if (oldSession != null) { // se già esiste una sessione
					oldSession.invalidate(); // la invalida
				}

				HttpSession currentSession = request.getSession(); // crea una nuova sessione
				currentSession.setAttribute("user", utente); // setta l'utente nella sessione
				currentSession.setMaxInactiveInterval(3200);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
				request.setAttribute("errorMessage", "Username o password errati");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
