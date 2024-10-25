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

		if (plantaDao.existeCodigo(codigo)) {
			System.out.println("El codigo ya existe en la base de datos");
			return false;
		}

		return true;

	}

	/**
	 * Método para comprobar que el codigo no contiene ni espacios, ni caracteres
	 * especiales
	 * 
	 * @param code Código de la planta
	 * @return True si el código es válido, False si el código no es válido
	 */
	private static boolean caracteresCodigo(String codigo) {
		String regex = "^[a-zA-Z]+$";
		return Pattern.matches(regex, codigo);
	}

	public static boolean comprobarEspaciosBlanco(String s) {
		if (s == null || s.length()==0) {
			return true;
		}
		return s.contains(" ");
	}
	
	public static boolean esContrasenaValida(String contrasena) {
        // Verificar longitud mínima de 6 caracteres
        if (contrasena == null || contrasena.length() < 6) {
            return false;
        }

        // Variables para verificar la presencia de al menos una letra y un número
        boolean tieneLetra = false;
        boolean tieneNumero = false;

        // Recorrer cada caracter de la contraseña
        for (char c : contrasena.toCharArray()) {
            if (Character.isLetter(c)) {
                tieneLetra = true;
            }
            if (Character.isDigit(c)) {
                tieneNumero = true;
            }
            // Si ya tiene ambos, no hace falta seguir buscando
            if (tieneLetra && tieneNumero) {
                return true;
            }
        }

        // Retorna true si cumple con ambas condiciones; de lo contrario, false
        return false;
    }

}
