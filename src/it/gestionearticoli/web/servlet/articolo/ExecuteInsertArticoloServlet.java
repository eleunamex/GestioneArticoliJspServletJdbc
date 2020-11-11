package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;
import it.gestionearticoli.service.MyServiceFactory;

@WebServlet("/ExecuteInsertArticoloServlet")
public class ExecuteInsertArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String codiceInputParam = request.getParameter("codice");
		String descrizioneInputParam = request.getParameter("descrizione");
		Long categoriaLongParam = Long.parseLong(request.getParameter("idCategoria"));
		String prezzoInputStringParam = request.getParameter("prezzo");
		Integer prezzo;

		// validazione
		try {
			prezzo = Integer.parseInt(prezzoInputStringParam);
		} catch (NumberFormatException e) {
			prezzo = 0;
		}

		if (codiceInputParam.isEmpty() || descrizioneInputParam.isEmpty() || prezzo < 1) {
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			System.out.println(prezzo);
			try {
				request.setAttribute("listaCategorieAttribute",
						MyServiceFactory.getCategoriaServiceInstance().listAll());
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("articolo/insert_articolo.jsp").forward(request, response);
			return;
		}

		// business
		Categoria categoria = new Categoria();
		categoria.setId(categoriaLongParam);

		Articolo articoloInstance = new Articolo(codiceInputParam, descrizioneInputParam, prezzo, categoria);

		try {
			MyServiceFactory.getArticoloServiceInstance().inserisciNuovo(articoloInstance);
			request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// results
		request.getRequestDispatcher("articolo/lista_articoli.jsp").forward(request, response);
	}

}
