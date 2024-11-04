package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
			// System.out.println("Error al crear la persona.");
		}

		return id;

	}

	public boolean existeEmail(String email) {

		String sqlString = "SELECT email FROM PERSONAS";
		ArrayList<String> emails = new ArrayList<String>();

		try {
			PreparedStatement pStatement = con.prepareStatement(sqlString);
			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				emails.add(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (emails.contains(email)) {
			return true;
		} else {
			return false;
		}

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
