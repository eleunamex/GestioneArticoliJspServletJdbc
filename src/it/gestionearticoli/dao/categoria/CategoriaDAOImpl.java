package it.gestionearticoli.dao.categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionearticoli.dao.AbstractMySQLDAO;
import it.gestionearticoli.model.Categoria;

public class CategoriaDAOImpl extends AbstractMySQLDAO implements CategoriaDAO {

	@Override
	public List<Categoria> list() throws Exception {
		if (isNotActive()) {
			return null;
		}

		ArrayList<Categoria> result = new ArrayList<Categoria>();
		Categoria categoriaTemp = null;

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery("select * from categoria order by id_categoria");

			while (rs.next()) {
				categoriaTemp = new Categoria();
				categoriaTemp.setId(rs.getLong("ID_CATEGORIA"));
				categoriaTemp.setDescrizione(rs.getString("DESCRIZIONE"));
				result.add(categoriaTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Categoria get(Long id) throws Exception {
		if (isNotActive() || id == null) {
			return null;
		}
		String query = "SELECT * FROM categoria WHERE ID_CATEGORIA = ?";
		Categoria categoria = null;
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					categoria = new Categoria();
					categoria.setId(rs.getLong("ID_CATEGORIA"));
					categoria.setDescrizione(rs.getString("DESCRIZIONE"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoria;
	}

	@Override
	public int update(Categoria input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}

		int result = 0;

		try (PreparedStatement ps = connection
				.prepareStatement("UPDATE categoria set DESCRIZIONE=? WHERE ID_CATEGORIA=?")) {
			ps.setString(1, input.getDescrizione());
			ps.setLong(2, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Categoria input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}

		int result = 0;

		try (PreparedStatement ps = connection.prepareStatement("INSERT INTO categoria (DESCRIZIONE) VALUES (?);")) {
			ps.setString(1, input.getDescrizione());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Categoria input) throws Exception {
		if (isNotActive() || input == null) {
			return -1;
		}
		String query = "DELETE FROM categoria WHERE ID_CATEGORIA = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setLong(1, input.getId());

			if (preparedStatement.executeUpdate() > 0) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	public List<Categoria> findByExample(Categoria input) throws Exception {
		if (isNotActive() || input == null) {
			return null;
		}
		String query = "SELECT * FROM categoria WHERE DESCRIZIONE LIKE ?";
		Categoria categoria = null;
		List<Categoria> listaCategorie = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			ps.setString(1, "%" + input.getDescrizione() + "%");
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					categoria = new Categoria();
					categoria.setId(rs.getLong("ID_CATEGORIA"));
					categoria.setDescrizione(rs.getString("DESCRIZIONE"));
					listaCategorie.add(categoria);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCategorie;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
