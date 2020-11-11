package it.gestionearticoli.web.servlet.categoria;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.service.MyServiceFactory;

/**
 * Servlet implementation class ListCategorieServlet
 */
@WebServlet("/ListCategorieServlet")
public class ListCategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("listaCategorieAttribute", MyServiceFactory.getCategoriaServiceInstance().listAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("categoria/lista_categorie.jsp").forward(request, response);
	}

}
