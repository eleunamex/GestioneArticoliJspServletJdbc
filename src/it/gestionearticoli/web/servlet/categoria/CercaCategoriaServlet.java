package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.service.MyServiceFactory;

/**
 * Servlet implementation class CercaCategoriaServlet
 */
@WebServlet("/CercaCategoriaServlet")
public class CercaCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String descrizioneInputParam = request.getParameter("descrizione");

		if (descrizioneInputParam.isEmpty()) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("categoria/cerca_categoria.jsp").forward(request, response);
			return;
		}

		Categoria categoria = new Categoria();
		categoria.setDescrizione(descrizioneInputParam);

		try {
			request.setAttribute("listaCategorieAttribute",
					MyServiceFactory.getCategoriaServiceInstance().findByExample(categoria));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("categoria/risultati_cerca_categoria.jsp").forward(request, response);

	}

}
