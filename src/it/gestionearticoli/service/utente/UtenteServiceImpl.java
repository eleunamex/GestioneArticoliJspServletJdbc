package it.gestionearticoli.service.utente;

import java.sql.Connection;

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
	public Utente authentication(String username, String password) throws Exception {
		Utente result = new Utente();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			utenteDao.setConnection(connection);

			result = utenteDao.autenticazione(username, password);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

}
