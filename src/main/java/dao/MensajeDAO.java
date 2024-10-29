package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexionBD.ConexionBD;
import modelo.Ejemplar;
import modelo.Mensaje;
import modelo.Persona;

public class MensajeDAO {
	
	private static Connection con = ConexionBD.getConnection();
	
	public List<Mensaje> obtenerMensajesDeEjemplar(Ejemplar ejemplar) {
	    String sqlString = "SELECT m.id, m.fechahora, m.mensaje, m.idEjemplar, m.idPersona,"+ 
	               "p.nombre AS nombrePersona, p.email AS emailPersona" +
	        "FROM mensajes m"+
	        "JOIN personas p ON m.idPersona = p.id"+
	        "WHERE m.idEjemplar = ?"+
	        "ORDER BY m.fechaHora ASC";
	   

	    List<Mensaje> mensajes = new ArrayList<>();

	    try (PreparedStatement pstmt = con.prepareStatement(sqlString)) {
	        pstmt.setLong(1, ejemplar.getId()); 

	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                
	                Mensaje mensaje = new Mensaje();
	                mensaje.setId(rs.getLong("id"));
	                //mensaje.setFechahora(rs.getObject("fechahora", LocalDate.class));
	                mensaje.setMensaje(rs.getString("mensaje"));
	                mensaje.setIdEjemplar(rs.getLong("idEjemplar"));
	                mensaje.setIdPersona(rs.getLong("idPersona"));
	                
	               
	                Persona persona = new Persona();
	                persona.setNombre(rs.getString("nombre"));
	                persona.setEmail(rs.getString("email"));
	                //mensaje.setId(persona.getId());; 
	                
	                mensajes.add(mensaje); 
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return mensajes; 
	}

}
