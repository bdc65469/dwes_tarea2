package controlador;

import java.util.Scanner;

import conexionBD.ConexionBDD;
import dao.CredencialesDAO;
import dao.PersonaDAO;

public class ServiciosPersona {
	
	private ConexionBDD factoria;
	private PersonaDAO personaDao;
	private CredencialesDAO credencialesDao;
	
	public ServiciosPersona() {
		factoria = ConexionBDD.getCon();
		personaDao = factoria.getPersonaDAO();
		credencialesDao = factoria.getCredencialesDAO();
	}
	
	public void crearUsuario() {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduce el nombre de la persona: ");
		String nombre = teclado.nextLine();

		String email = "";
		do {
			System.out.println("Introduce el email de la persona: ");
			email = teclado.nextLine();

			if (Comprobaciones.comprobarEspaciosBlanco(email)) {
				System.out.println("El email no puede contener espacios en blanco");
			}

			if (personaDao.existeEmail(email)) {
				System.out.println("El email ya existe");
			}
		} while (Comprobaciones.comprobarEspaciosBlanco(email) || personaDao.existeEmail(email));
		
		
		String usuario = "";
		
		do {
			System.out.println("Introduce el usuario de la persona: ");
			usuario = teclado.nextLine();

			if (Comprobaciones.comprobarEspaciosBlanco(usuario)) {
				System.out.println("El usuario no puede contener espacios en blanco");
			}

			if (credencialesDao.existeUsuario(usuario)) {
				System.out.println("El usuario ya existe");
			}
		} while (Comprobaciones.comprobarEspaciosBlanco(usuario) || credencialesDao.existeUsuario(usuario));
		
		String password = "";
		
		do {
			System.out.println("Introduce la contraseña de la persona: ");
			password = teclado.nextLine();

			if (Comprobaciones.comprobarEspaciosBlanco(password)) {
				System.out.println("La contraseña no puede contener espacios en blanco");
			}

			if (!Comprobaciones.esContrasenaValida(password)) {
				System.out.println("La contraseña no es válida. Recuerda que la contraseña tiene que tener una longitud de 6 carácteres minimo, incluyendo una letra y un número mínimo");
			}
		} while (!Comprobaciones.esContrasenaValida(password) || Comprobaciones.comprobarEspaciosBlanco(password));
		
		int filas = personaDao.insertarPersona(nombre, email, credencialesDao.crearCredenciales(usuario, password));
		if (filas>0) {
			System.out.println("Usuario creado correctamente");
		}else {
			System.out.println("No se pudo crear el usuario");
		}
	}

}
