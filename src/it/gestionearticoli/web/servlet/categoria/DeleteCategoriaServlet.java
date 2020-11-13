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
		ArticoloService articoloService= MyServiceFactory.getArticoloServiceInstance();
		
		Categoria categoria = new Categoria();
		categoria.setId(parametroIdCategoriaDaEliminare);
		
		List<Articolo> listaArticoli= new ArrayList<>();
		try {
			listaArticoli = articoloService.articoliFiltratiPerCategoria(categoria);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (listaArticoli.isEmpty()) {
			try {
				if(service.rimuovi(categoria)==1) {
					request.setAttribute("successMessage", "Categoria eliminata");					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			request.setAttribute("errorMessage", "Non puoi eliminare una categoria se ha degli articoli");
		}
		

		RequestDispatcher dispatcher = request.getRequestDispatcher("ListCategorieServlet");

		dispatcher.forward(request, response);
	}

}
