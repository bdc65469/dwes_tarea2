package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import modelo.Mensaje;
import modelo.Planta;


public class MensajeDAO {

	private Connection con;

	public MensajeDAO(Connection con) {
		this.con = con;
	}

	public int crearMensaje(Mensaje mensaje) {

		int filasInsertadas = 0;
		String query = "INSERT INTO mensajes (fechaHora, mensaje, idEjemplar, idPersona) VALUES (?, ?, ?, ?)";

		try (PreparedStatement statement = con.prepareStatement(query)) {

			statement.setTimestamp(1, Timestamp.valueOf(mensaje.getFechahora()));
			statement.setString(2, mensaje.getMensaje()); 
			statement.setLong(3, mensaje.getIdEjemplar()); 
			statement.setLong(4, mensaje.getIdPersona()); 

			// Ejecutar la inserción
			filasInsertadas = statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return filasInsertadas;
	}

	public List<Mensaje> obtenerMensajesDeEjemplar(Long idejemplar) {
		String sqlString = "SELECT m.id, m.fechaHora, m.mensaje, m.idEjemplar, m.idPersona, "
                + "p.nombre, p.email "
                + "FROM mensajes m "
                + "JOIN personas p ON m.idPersona = p.id "
                + "WHERE m.idEjemplar = ? "
                + "ORDER BY m.fechaHora ASC";

		List<Mensaje> mensajes = new ArrayList<>();

		try (PreparedStatement pstmt = con.prepareStatement(sqlString)) {
			pstmt.setLong(1, idejemplar);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {

					Mensaje mensaje = new Mensaje();
					mensaje.setId(rs.getLong("id"));
					mensaje.setFechahora(rs.getTimestamp("fechaHora").toLocalDateTime());
					mensaje.setMensaje(rs.getString("mensaje"));
					mensaje.setIdEjemplar(rs.getLong("idEjemplar"));
					mensaje.setIdPersona(rs.getLong("idPersona"));


					mensajes.add(mensaje);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mensajes;
	}

	public List<Mensaje> obtenerMensajesPorRangoDeFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		List<Mensaje> mensajes = new ArrayList<>();

		String sql = "SELECT fechaHora, mensaje, idEjemplar, idPersona FROM mensajes "
				+ "WHERE fechaHora BETWEEN ? AND ?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			// Configura los parámetros del rango de fechas
			stmt.setTimestamp(1, Timestamp.valueOf(fechaInicio));
			stmt.setTimestamp(2, Timestamp.valueOf(fechaFin));

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Mensaje mensaje = new Mensaje();
					mensaje.setFechahora(rs.getTimestamp("fechaHora").toLocalDateTime());
					mensaje.setMensaje(rs.getString("mensaje"));
					mensaje.setIdEjemplar(rs.getLong("idEjemplar"));
					mensaje.setIdPersona(rs.getLong("idPersona"));

					mensajes.add(mensaje);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mensajes;
	}

	public List<Mensaje> obtenerMensajesPorPersona(Long idPersona) {
		List<Mensaje> mensajes = new ArrayList<>();

		String sql = "SELECT m.id, m.fechaHora, m.mensaje, m.idEjemplar, m.idPersona " + 
		"FROM mensajes m "+ 
		"JOIN personas p ON m.idPersona = p.id " + 
		"WHERE m.idPersona = ?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setLong(1, idPersona);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Mensaje mensaje = new Mensaje();
					mensaje.setId(rs.getLong("id"));
					mensaje.setFechahora(rs.getTimestamp("fechaHora").toLocalDateTime());
					mensaje.setMensaje(rs.getString("mensaje"));
					mensaje.setIdEjemplar(rs.getLong("idEjemplar"));
					mensaje.setIdPersona(rs.getLong("idPersona"));

					mensajes.add(mensaje);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mensajes;
	}
	
	public List<Mensaje> obtenerMensajesPorPlanta(Planta p) {
		List<Mensaje> mensajes = new ArrayList<>();

		String sql = "SELECT m.id, m.fechaHora, m.mensaje, m.idEjemplar, m.idPersona " + 
		"FROM mensajes m, ejemplares e, plantas p "+ 
		"WHERE m.idEjemplar = e.id and e.idplanta = p.codigo and p.codigo = ?";

		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, p.getCodigo());

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Mensaje mensaje = new Mensaje();
					mensaje.setId(rs.getLong("id"));
					mensaje.setFechahora(rs.getTimestamp("fechaHora").toLocalDateTime());
					mensaje.setMensaje(rs.getString("mensaje"));
					mensaje.setIdEjemplar(rs.getLong("idEjemplar"));
					mensaje.setIdPersona(rs.getLong("idPersona"));

					mensajes.add(mensaje);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mensajes;
	}

}
