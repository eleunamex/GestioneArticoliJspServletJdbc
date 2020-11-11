package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.service.MyServiceFactory;
import it.gestionearticoli.service.categoria.CategoriaService;

/**
 * Servlet implementation class DeleteCategoriaServlet
 */
@WebServlet("/DeleteCategoriaServlet")
public class DeleteCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parametroIdCategoriaDaEliminareString = request.getParameter("idDaInviareComeParametro");

		String contextPath = request.getContextPath() + "/ListCategorieServlet";
		Long parametroIdCategoriaDaEliminare;

		if (parametroIdCategoriaDaEliminareString == null || parametroIdCategoriaDaEliminareString.isEmpty()) {
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

		CategoriaService service = MyServiceFactory.getCategoriaServiceInstance();

		Categoria categoria = new Categoria();
		categoria.setId(parametroIdCategoriaDaEliminare);

		try {
			service.rimuovi(categoria);
			request.setAttribute("successMessage", "Categoria eliminata");
		} catch (SQLIntegrityConstraintViolationException s) {
			request.setAttribute("errorMessage", "Non puoi eliminare una categoria se ha degli articoli");
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("ListCategorieServlet");

		dispatcher.forward(request, response);
	}

}
