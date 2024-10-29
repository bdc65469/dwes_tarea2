package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import conexionBD.ConexionBD;
import modelo.Ejemplar;
import modelo.Mensaje;
import modelo.Persona;

public class MensajeDAO {
	
	private Connection con;

    public MensajeDAO(Connection con) {
        this.con = con;
    }
	
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
	
	public List<Mensaje> obtenerMensajesPorRangoDeFecha(LocalDate fechaInicio, LocalDate fechaFin){
        List<Mensaje> mensajes = new ArrayList<>();
        
        String sql = "SELECT fechaHora, mensaje, idEjemplar, idPersona FROM mensajes " +
                     "WHERE fechaHora BETWEEN ? AND ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            // Configura los parámetros del rango de fechas
            stmt.setDate(1, Date.valueOf(fechaInicio));
            stmt.setDate(2, Date.valueOf(fechaFin));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Mensaje mensaje = new Mensaje();
                    mensaje.setId(rs.getLong("id"));
                    mensaje.setFechahora(rs.getDate("fechaHora").toLocalDate());
                    mensaje.setMensaje(rs.getString("mensaje"));
                    mensaje.setIdEjemplar(rs.getLong("idEjemplar"));
                    mensaje.setIdPersona(rs.getLong("idPersona"));
                    
                    mensajes.add(mensaje);
                }
            }
        }catch (SQLException e) {
	        e.printStackTrace();
	    }
        
        return mensajes;
    }
	
	public List<Mensaje> obtenerMensajesPorPersona(Long idPersona) throws SQLException {
        List<Mensaje> mensajes = new ArrayList<>();
        
        String sql = "SELECT m.id, m.fechaHora, m.mensaje, m.idEjemplar, m.idPersona " +
                     "FROM mensajes m " +
                     "JOIN personas p ON m.idPersona = p.id " +
                     "WHERE m.idPersona = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setLong(1, idPersona);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Mensaje mensaje = new Mensaje();
                    mensaje.setId(rs.getLong("id"));
                    mensaje.setFechahora(rs.getDate("fechaHora").toLocalDate());
                    mensaje.setMensaje(rs.getString("mensaje"));
                    mensaje.setIdEjemplar(rs.getLong("idEjemplar"));
                    mensaje.setIdPersona(rs.getLong("idPersona"));   
                    
                    mensajes.add(mensaje);
                }
            }
        }catch (SQLException e) {
	        e.printStackTrace();
	    }
        
        return mensajes;
    }

}
