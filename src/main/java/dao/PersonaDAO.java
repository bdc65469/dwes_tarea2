package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Persona;

public class PersonaDAO {

	private Connection con;

	public PersonaDAO(Connection con) {
		this.con = con;
	}

	public Long obtenerIdporUsuario(String usuario) {
		Long idPersona = null;

		String query = "SELECT p.id FROM personas p " + 
		"JOIN credenciales c ON p.id = c.idPersona "+ 
		"WHERE c.usuario = ?";

		try (PreparedStatement statement = con.prepareStatement(query)) {

			statement.setString(1, usuario);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				idPersona = resultSet.getLong("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return idPersona;
	}

	public Long insertarPersona(String nombre, String email) {
		Long id = 0L;
		String sqlInsertPersona = "INSERT INTO personas (nombre, email) VALUES (?, ?)";
		int filasAfectadas = 0;
		try (PreparedStatement ps = con.prepareStatement(sqlInsertPersona, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, nombre);
			ps.setString(2, email);

			filasAfectadas = ps.executeUpdate();
			if (filasAfectadas > 0) {
				try (ResultSet rs = ps.getGeneratedKeys()) {
					if (rs.next()) {
						id = rs.getLong(1); // Retorna el ID autogenerado de las credenciales
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;

	}

	public boolean existeEmail(String email) {

		String sqlString = "SELECT COUNT(*) FROM PERSONAS WHERE LOWER(email) = LOWER(?)";

		try {
			PreparedStatement pStatement = con.prepareStatement(sqlString);
			pStatement.setString(1, email);
			ResultSet rs = pStatement.executeQuery();

			if (rs.next() && rs.getInt(1) > 0) {
				return true; 
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	public Persona obtenerPersonaPorId(Long id) {
		String sqlString = "SELECT id, nombre, email FROM personas WHERE id = ?";
		Persona persona = null;

		try (PreparedStatement pstmt = con.prepareStatement(sqlString)) {
			pstmt.setLong(1, id);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					persona = new Persona();
					persona.setId(rs.getLong("id"));
					persona.setNombre(rs.getString("nombre"));
					persona.setEmail(rs.getString("email"));
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return persona;
	}

}
