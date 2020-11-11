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
 * Servlet implementation class ExecuteInsertCategoriaServlet
 */
@WebServlet("/ExecuteInsertCategoriaServlet")
public class ExecuteInsertCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String descrizioneInputParam = request.getParameter("descrizione");

		if (descrizioneInputParam.isEmpty()) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("categoria/insert_categoria.jsp").forward(request, response);
			return;
		}

		// business
		Categoria categoria = new Categoria(descrizioneInputParam);

		try {
			MyServiceFactory.getCategoriaServiceInstance().inserisciNuovo(categoria);
			request.setAttribute("listaCategorieAttribute", MyServiceFactory.getCategoriaServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// results
		request.getRequestDispatcher("categoria/lista_categorie.jsp").forward(request, response);
	}

}
