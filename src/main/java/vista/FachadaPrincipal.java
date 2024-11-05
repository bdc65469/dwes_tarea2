package vista;

import java.util.InputMismatchException;
import java.util.Scanner;

import controlador.Comprobaciones;
import controlador.ServiciosCredenciales;
import controlador.ServiciosEjemplar;
import controlador.ServiciosFactory;
import controlador.ServiciosMensaje;
import controlador.ServiciosPersona;
import controlador.ServiciosPlanta;
import controlador.Sesion;
import controlador.Sesion.Perfil;


public class FachadaPrincipal {

	private static FachadaPrincipal portal;
	
	Scanner teclado = new Scanner(System.in);

	ServiciosFactory f = ServiciosFactory.getServicios();

	ServiciosEjemplar ejemplarServ = f.getServiciosEjemplar();
	ServiciosPersona personaServ = f.getServiciosPersona();
	ServiciosPlanta plantaServ = f.getServiciosPlanta();
	ServiciosMensaje mensajeServ = f.getServiciosMensaje();
	ServiciosCredenciales credendialesServ = f.getServiciosCredenciales();
	Comprobaciones comprobaciones = f.getComprobaciones();

	Sesion s = new Sesion("", Perfil.INVITADO);
	boolean sesionActiva = true;

	public static FachadaPrincipal getPortal() {
		if (portal == null) {
			portal = new FachadaPrincipal();
		}
		return portal;
	}

	public void menuInvitado() {
		System.out.println("");
		System.out.println("***********************************************");
		System.out.println("Bienvenido al menu de gestión de un vivero");
		System.out.println("1.  Ver Plantas.");
		System.out.println("2.  Iniciar sesión.");
		System.out.println("0.  Cerrar aplicación.");
		System.out.println("Seleccione una opcion:");
	}

	public void menuAdministrador() {
		System.out.println("");
		System.out.println("***********************************************");
		System.out.println("Bienvenido administrador");
		System.out.println("1.  Registrar persona.");
		System.out.println("2.  Gestionar plantas.");
		System.out.println("3.  Gestionar ejemplares.");
		System.out.println("4.  Gestionar mensajes.");
		System.out.println("5.  Cerrar sesión.");
		System.out.println("0.  Cerrar aplicación.");
		System.out.println("Seleccione una opcion:");
	}


	public void menuUsuarioRegistrado(String usuario) {
		System.out.println("");
		System.out.println("***********************************************");
		System.out.println("Bienvenido/a " + usuario);
		System.out.println("1.  Gestionar ejemplares.");
		System.out.println("2.  Gestionar mensajes.");
		System.out.println("3.  Cerrar sesión.");
		System.out.println("0.  Cerrar aplicación.");
		System.out.println("Seleccione una opcion:");
	}

	public void mostrarMenuInvitado() {
		int opcion = -1;
		do {
			this.menuInvitado();
			try {
				opcion = teclado.nextInt();
				teclado.nextLine();
				if (opcion < 0 || opcion > 2) {
					System.err.println("Opción fuera de rango. Inténtelo de nuevo.");
					continue;
				}

				switch (opcion) {
				case 1:
					//Ver lista plantas
					if (plantaServ.listaPlantas().size() == 0) {
						System.out.println("No hay plantas almacenadas");
					} else {
						System.out.println("Lista de plantas");
						for (int i = 0; i < plantaServ.listaPlantas().size(); i++) {
							int numero = i + 1;
							System.out.println(numero + "ª " + plantaServ.listaPlantas().get(i));
						}
					}
					break;
				case 2:
					//Login
					System.out.print("Introduce tu usuario: ");
					String usuario = teclado.nextLine();
					System.out.print("Introduce tu contraseña: ");
					String contraseña = teclado.nextLine();
					if (credendialesServ.Login(usuario, contraseña)) {
						s.setUsuario(usuario);
						s.setPerfil(Perfil.REGISTRADO);
						if (usuario.equals("admin")) {
							s.setPerfil(Perfil.ADMIN);
							this.mostrarMenuAdministrador(s);

						} else {
							this.mostrarMenuUsuarioRegistrado(s);
						}
					} else {
						System.out.println("Usuario o contraseña incorrectos");
					}

					break;
				case 0:
					//Cerrar programa
					sesionActiva = false;
					teclado.close();
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("Entrada inválida. Por favor, ingrese un número entero.");
				teclado.next();
			}
		} while (sesionActiva);

		teclado.close();
	}

	public void mostrarMenuUsuarioRegistrado(Sesion s) {
		int opcion = -1;
		do {
			this.menuUsuarioRegistrado(s.getUsuario());
			try {
				opcion = teclado.nextInt();
				teclado.nextLine();
				if (opcion < 0 || opcion > 3) {
					System.err.println("Opción fuera de rango. Inténtelo de nuevo.");
					continue;
				}

				switch (opcion) {
				case 1:
					//Llama al menú de ejemplares
					FachadaEjemplares faEjem = new FachadaEjemplares(s);
					faEjem.mostrarMenuGestionEjemplares();
					break;
				case 2:
					//Llama al menú de mensajes
					FachadaMensajes faMen = new FachadaMensajes(s);
					faMen.mostrarMenuGestionarMensajes();
					break;
				case 3:
					//Cerrar sesión
					System.out.println("Hasta luego " + s.getUsuario());
					s.cerrarSesion();
					this.mostrarMenuInvitado();
					break;
				case 0:
					//Cerrar programa
					sesionActiva = false;
					s.cerrarSesion();
					teclado.close();
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("Entrada inválida. Por favor, ingrese un número entero.");
				teclado.next();
			}

		} while (sesionActiva);

	}

	public void mostrarMenuAdministrador(Sesion s) {
		int opcion = -1;
		do {
			this.menuAdministrador();
			try {
				opcion = teclado.nextInt();
				teclado.nextLine();
				if (opcion < 0 || opcion > 5) {
					System.err.println("Opción fuera de rango. Inténtelo de nuevo.");
					continue;
				}

				switch (opcion) {
				case 1:
					//Registrar persona
					String nombre = "";
					do {
						System.out.println("Introduce el nombre de la persona: ");
						nombre = teclado.nextLine();

						if (!comprobaciones.verificarNombrePersona(nombre)) {
							System.out.println(
									"Nombre no válido. Recuerde que como máximo puede contener 25 caracteres y no puede contener númenos, ni carácteres");
						}

					} while (!comprobaciones.verificarNombrePersona(nombre));

					String email = "";
					do {
						System.out.println("Introduce el email de la persona: ");
						email = teclado.nextLine();

						if (!comprobaciones.esEmailValido(email)) {
							System.out.println("Formato de email no válido");
						}

						if (personaServ.existeEmail(email)) {
							System.out.println("El email ya existe");
						}
					} while (personaServ.existeEmail(email) || !comprobaciones.esEmailValido(email));

					String usuario = "";

					do {
						System.out.println("Introduce el usuario de la persona: ");
						usuario = teclado.nextLine();

						if (comprobaciones.comprobarUsuario(usuario)) {
							System.out.println("El usuario no puede contener espacios en blanco");
						}

						if (usuario.equalsIgnoreCase("admin")) {
							System.out.println("Nombre de usuario reservado.");
						}

						if (credendialesServ.existeUsuario(usuario)) {
							System.out.println("El usuario ya existe");
						}
					} while (comprobaciones.comprobarUsuario(usuario) || credendialesServ.existeUsuario(usuario)
							|| usuario.equalsIgnoreCase("admin"));

					String password = "";
					do {
						System.out.println("Introduce la contraseña de la persona: ");
						password = teclado.nextLine();

						if (comprobaciones.comprobarEspaciosBlanco(password)) {
							System.out.println("La contraseña no puede contener espacios en blanco");
						}

						if (!comprobaciones.esContrasenaValida(password)) {
							System.out.println(
									"La contraseña no es válida. Recuerda que la contraseña tiene que tener una longitud de 6 carácteres minimo, incluyendo una letra y un número mínimo");
						}
					} while (!comprobaciones.esContrasenaValida(password)
							|| comprobaciones.comprobarEspaciosBlanco(password));

					if (credendialesServ.crearUsuario(nombre, email, usuario, password) > 0) {
						System.out.println("Usuario registrado correctamente");
					} else {
						System.err.println("No se ha podido registrar el usuario");
					}
					break;
				case 2:
					//Llama el menu de gestionar plantas
					FachadaPlantas faPla = new FachadaPlantas();
					faPla.mostrarMenuGestionarPlantas();
					break;
				case 3:
					//Llama al menu de gestionar ejemplares
					FachadaEjemplares faEjem = new FachadaEjemplares(s);
					faEjem.mostrarMenuGestionEjemplares();
					break;
				case 4:
					//Llama al menu de gestionar mensajes
					FachadaMensajes faMen = new FachadaMensajes(s);
					faMen.mostrarMenuGestionarMensajes();
					break;
				case 5:
					//Cerrar sesión
					System.out.println("Hasta luego " + s.getUsuario());
					s.cerrarSesion();
					this.mostrarMenuInvitado();
					break;
				case 0:
					//Cerrar programa
					sesionActiva = false;
					s.cerrarSesion();
					teclado.close();
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("Entrada inválida. Por favor, ingrese un número entero.");
				teclado.next();
			}

		} while (sesionActiva);

	}

	

	

	
}
