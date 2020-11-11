package it.gestionearticoli.web.servlet.categoria;

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
import it.gestionearticoli.service.categoria.CategoriaService;

/**
 * Servlet implementation class VisualizzaArticoliDellaCategoria
 */
@WebServlet("/VisualizzaCategoriaServlet")
public class VisualizzaCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String parametroIdCategoriaDaVisualizzareString = request.getParameter("idDaInviareComeParametro");

		String contextPath = request.getContextPath() + "/ListCategorieServlet";
		Long parametroIdCategoriaDaVisualizzare;

		if (parametroIdCategoriaDaVisualizzareString == null || parametroIdCategoriaDaVisualizzareString.isEmpty()) {
			response.sendRedirect(contextPath);
			return;
		} else {
			try {
				parametroIdCategoriaDaVisualizzare = Long.parseLong(request.getParameter("idDaInviareComeParametro"));
			} catch (NumberFormatException nfe) {
				response.sendRedirect(contextPath);
				return;
			}
		}

		ArticoloService articoloService = MyServiceFactory.getArticoloServiceInstance();
		CategoriaService categoriaService = MyServiceFactory.getCategoriaServiceInstance();

		List<Articolo> result = new ArrayList<>();
		Categoria categoria = new Categoria();

		try {
			categoria = categoriaService.findById(parametroIdCategoriaDaVisualizzare);
			result = articoloService.articoliFiltratiPerCategoria(categoria);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("listaArticoliAttribute", result);
		request.setAttribute("categoriaAttribute", categoria);

		RequestDispatcher dispatcher = request.getRequestDispatcher("categoria/show_categoria.jsp");
		dispatcher.forward(request, response);
	}

}
