package it.gestionearticoli.dao.articolo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.Articolo;
import it.gestionearticoli.model.Categoria;

public class ArticoloDAOImpl extends AbstractMySQLDAO implements ArticoloDAO {

	@Override
	public List<Articolo> list() throws Exception {
		if (isNotActive()) {
			return null;
		}

		ArrayList<Articolo> result = new ArrayList<Articolo>();
		Articolo articoloTemp = null;

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery("select a.id as id_a, a.codice as codice_a, a.descrizione as descrizione_a, "
					+ " a.prezzo as prezzo_a, a.categoria_fk as categoria_fk, c.id_categoria as id_c, "
					+ " c.descrizione as descrizione_c from articolo a left join categoria c on a.categoria_fk = c.id_categoria order by a.id");

			while (rs.next()) {
				articoloTemp = new Articolo();
				articoloTemp.setCodice(rs.getString("codice_a"));
				articoloTemp.setDescrizione(rs.getString("descrizione_a"));
				articoloTemp.setPrezzo(rs.getInt("prezzo_a"));
				articoloTemp.setId(rs.getLong("id_a"));
				Categoria categoria = new Categoria();
				categoria.setId(rs.getLong("categoria_fk"));
				categoria.setDescrizione(rs.getString("descrizione_c"));
				articoloTemp.setCategoria(categoria);
				result.add(articoloTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Articolo get(Long id) throws Exception {
		if (isNotActive() || id == null) {
			return null;
		}
		String query = "select a.id as id_a, a.codice as codice_a, a.descrizione as descrizione_a, "
				+ "a.prezzo as prezzo_a, a.categoria_fk as categoria_fk, c.id_categoria as id_c, c.descrizione as descrizione_c "
				+ "from articolo a left join categoria c on a.categoria_fk = c.id_categoria where a.id=?;";
		Articolo articolo = null;
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					articolo = new Articolo();
					articolo.setCodice(rs.getString("codice_a"));
					articolo.setDescrizione(rs.getString("descrizione_a"));
					articolo.setPrezzo(rs.getInt("prezzo_a"));
					articolo.setId(rs.getLong("id_a"));
					Categoria categoria = new Categoria();
					categoria.setId(rs.getLong("categoria_fk"));
					categoria.setDescrizione(rs.getString("descrizione_c"));
					articolo.setCategoria(categoria);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articolo;
	}

	@Override
	public int update(Articolo input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}

		int result = 0;

		try (PreparedStatement ps = connection
				.prepareStatement("UPDATE articolo set CODICE=?, DESCRIZIONE=?, PREZZO=?, CATEGORIA_FK=? WHERE ID=?")) {
			ps.setString(1, input.getCodice());
			ps.setString(2, input.getDescrizione());
			ps.setInt(3, input.getPrezzo());
			ps.setLong(4, input.getCategoria().getId());
			ps.setLong(5, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Articolo input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}

		int result = 0;

		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO articolo (codice, descrizione, prezzo, categoria_fk) VALUES (?, ?, ?,?);")) {
			ps.setString(1, input.getCodice());
			ps.setString(2, input.getDescrizione());
			ps.setInt(3, input.getPrezzo());
			ps.setLong(4, input.getCategoria().getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Articolo input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}
		String query = "DELETE FROM articolo WHERE ID = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setLong(1, input.getId());

			if (preparedStatement.executeUpdate() > 0) {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Articolo> articlesFilteredByCategory(Categoria categoria) throws Exception {
		if (isNotActive() || categoria == null) {
			return null;
		}

		String query = "select a.id as id_a, a.codice as codice_a, a.descrizione as descrizione_a, "
				+ "	a.prezzo as prezzo_a, a.categoria_fk as categoria_fk, c.id_categoria as id_c, "
				+ "	c.descrizione as descrizione_c from articolo a inner join categoria c on a.categoria_fk = c.id_categoria "
				+ "  where a.categoria_fk=? order by a.id";
		Articolo articoloTemp = null;
		ArrayList<Articolo> result = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setLong(1, categoria.getId());
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					articoloTemp = new Articolo();
					articoloTemp.setCodice(rs.getString("codice_a"));
					articoloTemp.setDescrizione(rs.getString("descrizione_a"));
					articoloTemp.setPrezzo(rs.getInt("prezzo_a"));
					articoloTemp.setId(rs.getLong("id_a"));
					Categoria categoriaResult = new Categoria();
					categoriaResult.setId(rs.getLong("categoria_fk"));
					categoriaResult.setDescrizione(rs.getString("descrizione_c"));
					articoloTemp.setCategoria(categoriaResult);
					result.add(articoloTemp);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Articolo> findByExample(Articolo input) throws Exception {
		if (isNotActive()|| input == null) {
			return null;
		}
		
		String query= "select a.id as id_a, a.codice as codice_a, a.descrizione as descrizione_a, "
				+ "a.prezzo as prezzo_a, a.categoria_fk as categoria_fk, c.id_categoria as id_c, "
				+ " c.descrizione as descrizione_c from articolo a left join categoria c on a.categoria_fk = c.id_categoria "
				+ " WHERE a.codice LIKE ? AND a.descrizione LIKE ? ";
				
		Articolo articolo = null;
		
		List<Articolo> listaArticoli = new ArrayList<>();
		
		if(input.getPrezzo()==null) {
			query=query+ " order by a.id ";
		}else {
			query=query+" AND PREZZO = ? order by a.id ";
		}
			
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			
			if(input.getCodice()!=null) {
				ps.setString(1, "%"+input.getCodice()+"%");
			}else {
				ps.setString(1, "%%");
			}
			
			if(input.getDescrizione()!=null) {
				ps.setString(2, "%"+input.getDescrizione()+"%");
			}else {
				ps.setString(2, "%%");
			}
			
			if(input.getPrezzo()==null ) {
			}else {
				ps.setInt(3, input.getPrezzo());
			}
			
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					articolo = new Articolo();
					articolo.setCodice(rs.getString("codice_a"));
					articolo.setDescrizione(rs.getString("descrizione_a"));
					articolo.setPrezzo(rs.getInt("prezzo_a"));
					articolo.setId(rs.getLong("id_a"));
					Categoria categoria = new Categoria();
					categoria.setId(rs.getLong("categoria_fk"));
					categoria.setDescrizione(rs.getString("descrizione_c"));
					articolo.setCategoria(categoria);
					listaArticoli.add(articolo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaArticoli;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;

	}

}
