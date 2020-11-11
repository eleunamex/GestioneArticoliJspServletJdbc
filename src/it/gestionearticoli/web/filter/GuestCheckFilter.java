package it.gestionearticoli.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.gestionearticoli.model.Utente;

/**
 * Servlet Filter implementation class RuoloCheckFilter
 */
@WebFilter("/GuestCheckFilter")
public class GuestCheckFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		Utente utente = new Utente();
		utente = (Utente) req.getSession().getAttribute("user");
		if (utente.getRuolo().equals("guest")) { // se l'utente è guest
			req.getSession().invalidate();
			res.sendRedirect("login.jsp"); // redirect alla pagina stessa
		} else {
			chain.doFilter(request, response);
		}
	}

}
