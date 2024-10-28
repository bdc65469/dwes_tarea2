package controlador;

import java.util.Scanner;

import dao.CredencialesDAO;
import dao.PersonaDAO;

public class ServiciosPersona {
	
	public static void crearUsuario() {
		Scanner teclado = new Scanner(System.in);
		PersonaDAO p = new PersonaDAO();
		CredencialesDAO c = new CredencialesDAO();
		System.out.println("Introduce el nombre de la persona: ");
		String nombre = teclado.nextLine();

		String email = "";
		do {
			System.out.println("Introduce el email de la persona: ");
			email = teclado.nextLine();

			if (Comprobaciones.comprobarEspaciosBlanco(email)) {
				System.out.println("El email no puede contener espacios en blanco");
			}

			if (p.existeEmail(email)) {
				System.out.println("El email ya existe");
			}
		} while (Comprobaciones.comprobarEspaciosBlanco(email) || p.existeEmail(email));
		
		
		String usuario = "";
		
		do {
			System.out.println("Introduce el usuario de la persona: ");
			usuario = teclado.nextLine();

			if (Comprobaciones.comprobarEspaciosBlanco(usuario)) {
				System.out.println("El usuario no puede contener espacios en blanco");
			}

			if (c.existeUsuario(usuario)) {
				System.out.println("El usuario ya existe");
			}
		} while (Comprobaciones.comprobarEspaciosBlanco(usuario) || c.existeUsuario(usuario));
		
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
		
		int filas = p.insertarPersona(nombre, email, c.crearCredenciales(usuario, password));
		if (filas>0) {
			System.out.println("Usuario creado correctamente");
		}else {
			System.out.println("No se pudo crear el usuario");
		}
	}

}
