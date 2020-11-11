package it.gestionearticoli.service.utente;

import it.gestionearticoli.dao.utente.UtenteDAO;
import it.gestionearticoli.model.Utente;

public interface UtenteService {

	public void setUtenteDao(UtenteDAO utenteDao);
	
	public Utente autenticazione(String username, String password) throws Exception ;
	
}
