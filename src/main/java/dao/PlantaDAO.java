package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexionBD.ConexionBD;
import modelo.Planta;

public class PlantaDAO {

	private static Connection con = ConexionBD.getConnection();
	
	public static void añadirPlanta (Planta planta) {
		String sql = "INSERT INTO plantas(codigo, nombrecomun, nombrecientifico) VALUES (?, ?, ?)";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
		    pstmt.setString(1, planta.getCodigo()); 
		    pstmt.setString(2, planta.getNombrecomun());
		    pstmt.setString(3, planta.getNombrecientifico());
		    
		    pstmt.executeUpdate();
		    pstmt.close();
		    con.close();
		} catch (SQLException e) {
		    e.printStackTrace(); 
		}
	}
	
	
	public static void borrarPlanta(String codigo) {
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
}
