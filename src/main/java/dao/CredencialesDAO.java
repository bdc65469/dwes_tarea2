package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


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
			// System.out.println("Error al insertar las credenciales.");
		}

		return filasAfectadas;

	}

	public boolean existeUsuario(String usuario) {

		String sqlString = "SELECT usuario FROM CREDENCIALES";
		ArrayList<String> usuarios = new ArrayList<String>();

		try {
			PreparedStatement pStatement = con.prepareStatement(sqlString);
			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				usuarios.add(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (usuarios.contains(usuario)) {
			return true;
		} else {
			return false;
		}

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
		}catch (NullPointerException n){
			System.out.println("Base de datos no encontrada. Recuerda arrancar el xamp");
		} catch (SQLException e) {
			e.getLocalizedMessage();
		}
		return false; // Retorna false si el usuario no existe o si ocurre algún error
	}

}
