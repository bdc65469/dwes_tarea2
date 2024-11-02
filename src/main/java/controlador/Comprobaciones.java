package controlador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comprobaciones {

	/**
	 * Método para comprobar que el codigo no contiene ni espacios, ni caracteres
	 * especiales
	 * 
	 * @param code Código de la planta
	 * @return True si el código es válido, False si el código no es válido
	 */

	public static boolean comprobarEspaciosBlanco(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		return s.contains(" ");
	}

	/**
	 * Metodo que comprueba si el string introducido contiene solo espacios o numeros
	 * @param nombre String a comprobar
	 * @return True si el formato del string es correcto, false si es incorrecto
	 */
	public static boolean nombreValido(String nombre) {

		if (nombre == null || nombre.trim().isEmpty()) {
			return false;
		}
		if (nombre.matches(".*\\d.*")) {
			return false;
		}
		return true;
	}

	public static boolean esCodigoValido(String codigo) {

		if (codigo == null || codigo.isEmpty()) {
			return false;
		}
		return codigo.matches("[a-zA-Z]+");
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
	
	public static String formatoFecha(LocalDateTime l) {
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");        
	      String fechaHoraFormateada = l.format(formatter);
	      return fechaHoraFormateada;
	}

}
