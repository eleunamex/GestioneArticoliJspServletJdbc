package it.gestionearticoli.web.servlet.articolo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.service.MyServiceFactory;

/**
 * Servlet implementation class CercaArticoloServlet
 */
@WebServlet("/CercaArticoloServlet")
public class CercaArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codiceInputParam = request.getParameter("codice");
		String descrizioneInputParam = request.getParameter("descrizione");
		String prezzoInputParam = request.getParameter("prezzo");
		
		if (codiceInputParam.isEmpty() && descrizioneInputParam.isEmpty() && prezzoInputParam.isEmpty()) {
			request.setAttribute("errorMessage", "Devi inserire almeno un campo di ricerca");
			request.getRequestDispatcher("articolo/cerca_articolo.jsp").forward(request, response);
			return;
		}
		
		Articolo articolo=new Articolo();
		articolo.setDescrizione(descrizioneInputParam);
		articolo.setCodice(codiceInputParam);
		if(!prezzoInputParam.isEmpty()) {
			try {
				articolo.setPrezzo(Integer.parseInt(prezzoInputParam));
			} catch (NumberFormatException e) {
				request.setAttribute("errorMessage", "Prezzo non valido");
				request.getRequestDispatcher("articolo/cerca_articolo.jsp").forward(request, response);
				return;
			}
		}
		
		try {
			request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().findByExample(articolo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("articolo/risultati_cerca_articolo.jsp").forward(request, response);
	}



}
