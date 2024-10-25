package controlador;

import java.text.Normalizer;
import java.util.regex.Pattern;

import dao.PlantaDAO;

public class Comprobaciones {
	
	private static PlantaDAO plantaDao = new PlantaDAO();
	
	public static boolean validarCodigoPlanta(String codigo) {
		
		if (!caracteresCodigo(codigo)) {
			System.out.println("El codigo tiene espacios o carácteres especiales");
			return false;
		}
		
		if (plantaDao.existeCodigo(codigo)){
			System.out.println("El codigo ya existe en la base de datos");
			return false;
		}
		
		return true;
			
	}
	
	/**
	 * Método para comprobar que el codigo no contiene ni espacios, ni caracteres especiales
	 * @param code Código de la planta
	 * @return True si el código es válido, False si el código no es válido
	 */
	private static boolean caracteresCodigo (String codigo) {
        String regex = "^[a-zA-Z]+$"; 
        return Pattern.matches(regex, codigo);
    }
	
	
	

}
