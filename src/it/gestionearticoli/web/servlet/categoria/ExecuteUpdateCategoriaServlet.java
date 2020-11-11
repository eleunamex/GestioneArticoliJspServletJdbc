package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.service.MyServiceFactory;
import it.gestionearticoli.service.categoria.CategoriaService;

/**
 * Servlet implementation class ExecuteUpdateCategoriaServlet
 */
@WebServlet("/ExecuteUpdateCategoriaServlet")
public class ExecuteUpdateCategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long idInputParam = Long.parseLong(request.getParameter("id"));
		String descrizioneInputParam = request.getParameter("descrizione");

		Categoria categoria = new Categoria(descrizioneInputParam);
		categoria.setId(idInputParam);

		CategoriaService service = MyServiceFactory.getCategoriaServiceInstance();

		if (descrizioneInputParam.isEmpty()) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			try {
				categoria = service.findById(idInputParam);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("categoriaDaInviareAPaginaModifica", categoria);
			request.getRequestDispatcher("categoria/update_categoria.jsp").forward(request, response);
			return;
		}

		try {
			service.aggiorna(categoria);
			request.setAttribute("listaCategorieAttribute", service.listAll());
			request.setAttribute("successMessage", "Categoria aggiornata");
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("categoria/lista_categorie.jsp").forward(request, response);
	}

}
