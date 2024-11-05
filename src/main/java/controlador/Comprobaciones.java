package controlador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comprobaciones {
	
	public Comprobaciones () {};
	
	/**
	 * verifica si el nombre de la persona es correcto, no puede contener números, ni carácteres
	 * @param nombre Nombre de la persona
	 * @return true si es correcto, false si es incorrecto
	 */
	public boolean verificarNombrePersona(String nombre) {		
		 return nombre != null && nombre.matches("^[a-zA-ZÀ-ÿÑñ'\\-\\s]{1,25}$");
	}
	
	/**
	 * Comprueba si sigue la estructura del email
	 * @param email Email del usuario
	 * @return True si el email es válido, false si no es válido
	 */
    public  boolean esEmailValido(String email) {
        return email != null && email.length() <= 50 && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

	/**
	 * Método para comprobar que el codigo no contiene ni espacios, ni caracteres
	 * especiales
	 * 
	 * @param code Código de la planta
	 * @return True si el código es válido, False si el código no es válido
	 */

	public boolean comprobarEspaciosBlanco(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}
		return s.contains(" ");
	}
	
	/**
	 * Comprueba si usuario tiene espacios en blanco, es nulo o más de 50 carácteres
	 * @param s usuario de la persona
	 * @return true si no es válido, false si es valido
	 */
	public boolean comprobarUsuario(String s) {
		if (s == null || s.length() == 0 || s.length()>50) {
			return true;
		}
		return s.contains(" ");
	}

	/**
	 * Metodo que comprueba si el string introducido contiene solo espacios o numeros
	 * @param nombre String a comprobar
	 * @return True si el formato del string es correcto, false si es incorrecto
	 */
	public boolean nombreValido(String nombre) {

		if (nombre == null || nombre.trim().isEmpty()) {
			return false;
		}
		if (nombre.matches(".*\\d.*")) {
			return false;
		}
		return true;
	}

	public boolean esCodigoValido(String codigo) {

		if (codigo == null || codigo.isEmpty()) {
			return false;
		}
		return codigo.matches("[a-zA-Z]+");
	}

	public  boolean esContrasenaValida(String contrasena) {
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
	
	public String formatoFecha(LocalDateTime l) {
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");        
	      String fechaHoraFormateada = l.format(formatter);
	      return fechaHoraFormateada;
	}

}
