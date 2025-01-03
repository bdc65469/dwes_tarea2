package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import modelo.Ejemplar;
import modelo.Planta;

public class EjemplarDAO {

	private Connection con;

	public EjemplarDAO(Connection con) {
		this.con = con;
	}

	/**
	 * Crea una un ejemplar a partir de una planta, pero como el nombre del ejemplar
	 * lleva un id autogenerado necesitamos generar primero el id y luego
	 * actualizamos el valor con el id generado más el codigo de la planta
	 * 
	 * @param planta La planta de la que se quiere crear el ejemplar
	 * @return Numero de filas afectadas en la tabla de ejemplares
	 */
	public Ejemplar crearEjemplar(Planta planta) {

		String sqlInsert = "INSERT INTO ejemplares (nombre,idplanta) VALUES (?,?)";
		Ejemplar creado = null;

		try (PreparedStatement ps = con.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, planta.getCodigo());
			ps.setString(2, planta.getCodigo());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				Long idGenerado = rs.getLong(1);
				String nombreEjemplar = planta.getCodigo() + "_" + idGenerado;
				actualizarNombreEjemplar(idGenerado, nombreEjemplar);
				creado = new Ejemplar (idGenerado, nombreEjemplar);
			}
			
		
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return creado;

	}

	
	private void actualizarNombreEjemplar(Long idEjemplar, String nombreEjemplar) {
		String sqlUpdate = "UPDATE ejemplares SET nombre = ? WHERE id = ?";
		try (PreparedStatement psUpdate = con.prepareStatement(sqlUpdate)) {
			psUpdate.setString(1, nombreEjemplar);
			psUpdate.setLong(2, idEjemplar);
			psUpdate.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Obtiene todos los ejemplares de una planta
	 * 
	 * @param p Planta de la que queremos buscar los ejemplares
	 * @return Un conjunto de ejemplares
	 */
	public Set<Ejemplar> obtenerEjemplaresPorPlanta(Planta p) {
		String sqlString = "SELECT * FROM Ejemplares WHERE idplanta = ?";
		Set<Ejemplar> ejemplares = new HashSet<>();

		try (PreparedStatement pstmt = con.prepareStatement(sqlString)) {
			pstmt.setString(1, p.getCodigo()); 

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {

					Ejemplar ejemplar = new Ejemplar();
					ejemplar.setId(rs.getLong("id"));
					ejemplar.setNombre(rs.getString("nombre"));

					ejemplares.add(ejemplar);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ejemplares;
	}
	
	public List<Ejemplar> listadoEjemplares() {

		String sqlString = "SELECT * FROM ejemplares ORDER BY nombre ASC";
		ArrayList<Ejemplar> ejemplares = new ArrayList<Ejemplar>();

		try {
			PreparedStatement pStatement = con.prepareStatement(sqlString);
			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				Ejemplar nuevo = new Ejemplar();
				nuevo.setId(rs.getLong(1));
				nuevo.setNombre(rs.getString(2));
				
				ejemplares.add(nuevo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ejemplares;
	}

	public Ejemplar obtenerEjemplarPorId(Long id) {
		String sqlString = "SELECT nombre FROM ejemplares WHERE id = ?";
		Ejemplar ejemplar = null;

		try (PreparedStatement pstmt = con.prepareStatement(sqlString)) {
			pstmt.setLong(1, id);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					ejemplar = new Ejemplar();
					ejemplar.setId(id);
					ejemplar.setNombre(rs.getString("nombre"));					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ejemplar;
	}

}
