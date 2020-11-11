package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;

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
 * Servlet implementation class PrepareUpdateCategoria
 */
@WebServlet("/PrepareUpdateCategoriaServlet")
public class PrepareUpdateCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String parametroIdCategoriaDaModificareString = request.getParameter("idDaInviareComeParametro");

		String contextPath = request.getContextPath() + "/ListArticoliServlet";
		Long parametroIdCategoriaDaModificare;

		if (parametroIdCategoriaDaModificareString == null || parametroIdCategoriaDaModificareString.isEmpty()) {
			response.sendRedirect(contextPath);
			return;
		} else {
			try {
				parametroIdCategoriaDaModificare = Long.parseLong(request.getParameter("idDaInviareComeParametro"));
			} catch (NumberFormatException nfe) {
				response.sendRedirect(contextPath);
				return;
			}
		}

		CategoriaService service = MyServiceFactory.getCategoriaServiceInstance();

		Categoria result = new Categoria();

		try {
			result = service.findById(parametroIdCategoriaDaModificare);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("categoriaDaInviareAPaginaModifica", result);

		RequestDispatcher dispatcher = request.getRequestDispatcher("categoria/update_categoria.jsp");
		dispatcher.forward(request, response);
	}

}
