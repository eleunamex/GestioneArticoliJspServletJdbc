package it.gestionearticoli.service.utente;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import it.gestionearticoli.connection.MyConnection;
import it.gestionearticoli.dao.Constants;
import it.gestionearticoli.dao.utente.UtenteDAO;
import it.gestionearticoli.model.Utente;

public class UtenteServiceImpl implements UtenteService {

	private UtenteDAO utenteDao;

	@Override
	public void setUtenteDao(UtenteDAO utenteDao) {
		this.utenteDao = utenteDao;
	}

	@Override
	public Utente autenticazione(String username, String password) throws Exception {
		List<Utente> listaUtenti = new ArrayList<>();
		Utente utenteResult = null;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {
			utenteDao.setConnection(connection);

			listaUtenti = utenteDao.list();
			for (Utente utente : listaUtenti) {
				if(utente.getUsername().equals(username) && utente.getPassword().equals(password)) {
					utenteResult=new Utente();
					return utenteResult=utente;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return utenteResult;
	}

}
