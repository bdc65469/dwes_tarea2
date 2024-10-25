package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexionBD.ConexionBD;
import modelo.Ejemplar;
import modelo.Planta;

public class EjemplarDAO {

	private static Connection con = ConexionBD.getConnection();

	/*
	public void añadirEjemplar(Ejemplar e) {
		String sql = "INSERT INTO ejemplares(nombre, idplanta) VALUES (?, ?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, e.getNombre());
			pstmt.setString(2, e.getPlanta().getCodigo());
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}*/

	//Me falta añadir lo del mensaje todavia
	/**
	 * Crea una un ejemplar a partir de una planta, pero como el nombre del ejemplar lleva un id autogenerado
	 * necesitamos generar primero el id y luego actualizamos el valor con el id generado más el codigo de la 
	 * planta
	 * @param planta La planta de la que se quiere crear el ejemplar
	 * @return Numero de filas afectadas en la tabla de ejemplares
	 */
	public int crearEjemplar(Planta planta) {

		String sqlInsert = "INSERT INTO ejemplares (nombre,idplanta) VALUES (?,?)";
		int filas = 0; //Filas afectadas por el actualizar nombre

		try (PreparedStatement ps = con.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, planta.getCodigo());
			ps.setString(2, planta.getCodigo());

			int filasAfectadas = ps.executeUpdate();

			if (filasAfectadas > 0) {

				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					Long idGenerado = rs.getLong(1);
					String nombreEjemplar = planta.getCodigo() + "_" + idGenerado;
					filas = actualizarNombreEjemplar(idGenerado, nombreEjemplar);
					
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return filas;
		
	}


	/**
	 * Método para actualizar el nombre del ejemplar y que pille el id autogenerado
	 * @param idEjemplar Id autogenerado 
	 * @param nombreEjemplar nuevo nombre del ejemplar
	 * @return numero de filas afectadas
	 */
	private int actualizarNombreEjemplar(Long idEjemplar, String nombreEjemplar) {
		String sqlUpdate = "UPDATE ejemplares SET nombre = ? WHERE id = ?";
		int filas = 0;
		try (PreparedStatement psUpdate = con.prepareStatement(sqlUpdate)) {
			psUpdate.setString(1, nombreEjemplar);
			psUpdate.setLong(2, idEjemplar);
			filas = psUpdate.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return filas;
	}

}
