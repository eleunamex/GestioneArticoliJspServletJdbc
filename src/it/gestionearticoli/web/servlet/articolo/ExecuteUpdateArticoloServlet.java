package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteUpdateArticoloServlet
 */
@WebServlet("/ExecuteUpdateArticoloServlet")
public class ExecuteUpdateArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long idInputParam = Long.parseLong(request.getParameter("id"));
		String codiceInputParam = request.getParameter("codice");
		String descrizioneInputParam = request.getParameter("descrizione");
		Long categoriaLongParam = Long.parseLong(request.getParameter("idCategoria"));
		String prezzoInputStringParam = request.getParameter("prezzo");
		Integer prezzo;

		// validate
		try {
			prezzo = Integer.parseInt(prezzoInputStringParam);
		} catch (NumberFormatException e) {
			prezzo = 0;
		}

		if (codiceInputParam.isEmpty() || descrizioneInputParam.isEmpty() || prezzo < 1) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");

			Articolo result = new Articolo();
			List<Categoria> listaCategorie = new ArrayList<Categoria>();
			try {
				result = MyServiceFactory.getArticoloServiceInstance().findById(idInputParam);
				listaCategorie = MyServiceFactory.getCategoriaServiceInstance().listAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("articoloDaInviareAPaginaModifica", result);
			request.setAttribute("listaCategorieAttribute", listaCategorie);
			request.getRequestDispatcher("articolo/update_articolo.jsp").forward(request, response);
			return;
		}

		// business
		Categoria categoria = new Categoria();
		categoria.setId(categoriaLongParam);
		Articolo articoloInstance = new Articolo(codiceInputParam, descrizioneInputParam, prezzo, categoria);
		articoloInstance.setId(idInputParam);
		try {
			MyServiceFactory.getArticoloServiceInstance().aggiorna(articoloInstance);
			request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().listAll());
			request.setAttribute("successMessage", "Articolo aggiornato");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// results
		request.getRequestDispatcher("articolo/lista_articoli.jsp").forward(request, response);
	}

}
