package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class PersonaDAO {

	private Connection con;

    public PersonaDAO(Connection con) {
        this.con = con;
    }
    
    public Long obtenerIdporUsuario(String usuario) {
    	Long idPersona = null;
        
        String query = "SELECT p.id FROM personas p " +
                       "JOIN credenciales c ON p.idCredenciales = c.id " +
                       "WHERE c.usuario = ?";
        
        try ( PreparedStatement statement = con.prepareStatement(query)) {
             
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

	public int insertarPersona(String nombre, String email, Long idCredenciales) {
		String sqlInsertPersona = "INSERT INTO personas (nombre, email, idCredenciales) VALUES (?, ?, ?)";
		int filasAfectadas = 0;
		try (PreparedStatement ps = con.prepareStatement(sqlInsertPersona, PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, nombre);
			ps.setString(2, email);
			ps.setLong(3, idCredenciales);

			filasAfectadas = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			//System.out.println("Error al crear la persona.");
		}

		return filasAfectadas;

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

}
