package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CredencialesDAO {

	private Connection con;

	public CredencialesDAO(Connection con) {
		this.con = con;
	}

	public int crearCredenciales(String usuario, String contrasena, Long idPersona) {

		int filasAfectadas = 0;
		String sqlInsertCredenciales = "INSERT INTO credenciales (usuario, password, idPersona) VALUES (?, ?, ?)";

		try (PreparedStatement ps = con.prepareStatement(sqlInsertCredenciales,
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, usuario);
			ps.setString(2, contrasena);
			ps.setLong(3, idPersona);

			filasAfectadas = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return filasAfectadas;

	}

	/**
	 * Comprueba si ya hay una persona registrada en la base de datos con ese usuario
	 * @param usuario Usuario a comprobar
	 * @return True si existe una persona con ese usuario, false si no existe
	 */
	public boolean existeUsuario(String usuario) {
		
		String sqlString = "SELECT COUNT(*) FROM CREDENCIALES WHERE LOWER(usuario) = LOWER(?)";

		try {
			PreparedStatement pStatement = con.prepareStatement(sqlString);
			pStatement.setString(1, usuario);
			ResultSet rs = pStatement.executeQuery();

			if (rs.next() && rs.getInt(1) > 0) {
				return true; 
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	public boolean Login(String usuario, String contrasena) {
		String sqlString = "SELECT password FROM CREDENCIALES WHERE usuario = ? AND usuario <> 'admin'";
		try {
			PreparedStatement stmt = con.prepareStatement(sqlString);

			stmt.setString(1, usuario);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String pass = rs.getString("password");
				return pass.equals(contrasena); // Compara la contraseña ingresada con la almacenada
			}
		} catch (NullPointerException n) {
			System.out.println("Base de datos no encontrada. Recuerda arrancar el xamp");
		} catch (SQLException e) {
			e.getLocalizedMessage();
		}
		return false; // Retorna false si el usuario no existe o si ocurre algún error
	}

}
