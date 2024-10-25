package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexionBD.ConexionBD;
import modelo.Planta;

public class PlantaDAO {

	private static Connection con = ConexionBD.getConnection();
	
	
	public void añadirPlanta (Planta planta) {
		String sql = "INSERT INTO plantas(codigo, nombrecomun, nombrecientifico) VALUES (?, ?, ?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
		    pstmt.setString(1, planta.getCodigo().toUpperCase()); //El código siempre debe ir en mayusculas
		    pstmt.setString(2, planta.getNombrecomun());
		    pstmt.setString(3, planta.getNombrecientifico());
		    
		    pstmt.executeUpdate();
		    pstmt.close();
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace(); 
		}
	}
	
	
	public void borrarPlanta(String codigo) {
	    String sql = "DELETE FROM plantas WHERE codigo = ?";
	    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setString(1, codigo);
	        int filasafectadas = pstmt.executeUpdate();
	        
	        if (filasafectadas > 0) {
	            System.out.println("Planta eliminada con éxito.");
	        } else {
	            System.out.println("No se encontró ninguna planta con el código: " + codigo);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }
	}
	
	public List<Planta> listadoPlantas() {

		String sqlString = "SELECT * FROM PLANTAS";
		ArrayList<Planta> plantas = new ArrayList<Planta>();

		try {
			PreparedStatement pStatement = con.prepareStatement(sqlString);
			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				Planta nueva = new Planta();
				nueva.setCodigo(rs.getString("codigo"));
				nueva.setNombrecomun(rs.getString("nombrecomun"));
				nueva.setNombrecientifico(rs.getString("nombrecientifico"));
		

				plantas.add(nueva);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return plantas;
	}
	
	/**
	 * Metodo para comprobar si existe un codigo de planta dentro de la BD
	 * @param codigo codigo de la planta
	 * @return true si ya existe el codigo, false si el codigo no existe
	 */
	public boolean existeCodigo(String codigo) {
		
		String sqlString = "SELECT codigo FROM PLANTAS";
		ArrayList<String> codigos = new ArrayList<String>();

		try {
			PreparedStatement pStatement = con.prepareStatement(sqlString);
			ResultSet rs = pStatement.executeQuery();

			while (rs.next()) {
				codigos.add(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (codigos.contains(codigo.toUpperCase())) {
			return true;
		}else {
			return false;
		}
		
	}

	public int actualizarPlanta(Planta planta, String nombrecomun, String nombrecientifico) {
	    String sql = "UPDATE plantas SET nombrecomun = ?, nombrecientifico = ? WHERE codigo = ?";
	    int filasAfectadas = 0;
	    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	        
	        pstmt.setString(1, nombrecomun);
	        pstmt.setString(2, nombrecientifico);
	        pstmt.setString(3, planta.getCodigo().toUpperCase());

	        filasAfectadas = pstmt.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return filasAfectadas;
	}


}
