package it.gestionearticoli.dao.utente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.Utente;

public class UtenteDAOImpl extends AbstractMySQLDAO implements UtenteDAO {

	@Override
	public List<Utente> list() throws Exception {
		if (isNotActive()) {
			return null;
		}

		ArrayList<Utente> result = new ArrayList<>();
		Utente utenteTemp = null;

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery("SELECT * FROM utente");

			while (rs.next()) {
				utenteTemp=new Utente();
				utenteTemp.setId(rs.getLong("ID_UTENTE"));
				utenteTemp.setNome(rs.getString("NOME"));
				utenteTemp.setCognome(rs.getString("COGNOME"));
				utenteTemp.setCodice_fiscale(rs.getString("CODICE_FISCALE"));
				utenteTemp.setUsername(rs.getString("USERNAME"));
				utenteTemp.setPassword(rs.getString("PW"));
				utenteTemp.setRuolo(rs.getString("RUOLO"));
				result.add(utenteTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Utente get(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Utente> findByExample(Utente input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
