package vista;

import java.util.InputMismatchException;
import java.util.Scanner;

import controlador.ServiciosCredenciales;
import controlador.ServiciosEjemplar;
import controlador.ServiciosFactory;
import controlador.ServiciosMensaje;
import controlador.ServiciosPersona;
import controlador.ServiciosPlanta;
import controlador.Sesion;
import controlador.Sesion.Perfil;

public class Fachada {

	private static Fachada portal;

	ServiciosFactory f = ServiciosFactory.getServicios();

	ServiciosEjemplar ejemplarServ = f.getServiciosEjemplar();
	ServiciosPersona personaServ = f.getServiciosPersona();
	ServiciosPlanta plantaServ = f.getServiciosPlanta();
	ServiciosMensaje mensajeServ = f.getServiciosMensaje();
	ServiciosCredenciales credendialesServ = f.getServiciosCredenciales();

	Sesion s = new Sesion("", Perfil.INVITADO);
	boolean sesionActiva = true;

	public static Fachada getPortal() {
		if (portal == null) {
			portal = new Fachada();
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
		System.out.println("0.  Salir.");
		System.out.println("Seleccione una opcion:");
	}

	public void menuGestionarPlantas() {
		System.out.println("");
		System.out.println("***********************************************");
		System.out.println("Menú Gestión de Plantas");
		System.out.println("1.  Insertar nueva planta.");
		System.out.println("2.  Modificar una planta.");
		System.out.println("0.  Salir.");
		System.out.println("Seleccione una opcion:");
	}

	public void menuGestionarEjemplares() {
		System.out.println("");
		System.out.println("***********************************************");
		System.out.println("Menú Gestión de Ejemplares");
		System.out.println("1.  Introduce el número de la planta de la que quieres crear un ejemplar.");
		System.out.println("2.  Introduce el número de la planta/as que quieres ver sus ejemplares .");
		System.out.println("3.  Introduce el número del ejemplar que quieres ver sus mensajes .");
		System.out.println("0.  Salir.");
		System.out.println("Seleccione una opcion:");
	}

	public void menuGestionarMensajes() {
		System.out.println("");
		System.out.println("***********************************************");
		System.out.println("Menú Gestión de Mensajes");
		System.out.println("1.  Crear mensaje.");
		System.out.println("2.  Filtrar mensajes por persona.");
		System.out.println("3.  Filtrar mensajes por un rango de fechas.");
		System.out.println("4.  Filtrar mensajes por planta.");
		System.out.println("5.  Salir del menú de gestión de mensajes.");
		System.out.println("0.  Cerrar aplicación.");
		System.out.println("Seleccione una opcion:");
	}

	public void menuUsuarioRegistrado(String usuario) {
		System.out.println("");
		System.out.println("***********************************************");
		System.out.println("Bienvenido " + usuario);
		System.out.println("1.  Gestionar mensajes.");
		System.out.println("2.  Cerrar sesión.");
		System.out.println("0.  Cerrar aplicación.");
		System.out.println("Seleccione una opcion:");
	}

	public void mostrarMenuInvitado() {
		Scanner teclado = new Scanner(System.in);
		int opcion = -1;
		do {
			this.menuInvitado();
			try {
				opcion = teclado.nextInt();
				teclado.nextLine();
				if (opcion < 0 || opcion > 2) {
					System.out.println("Opción fuera de rango. Inténtelo de nuevo.");
					continue;
				}

				switch (opcion) {
				case 1:
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
					System.out.print("Introduce tu usuario: ");
					String usuario = teclado.nextLine();
					System.out.print("Introduce tu contraseña: ");
					String contraseña = teclado.nextLine();
					if (credendialesServ.Login(usuario, contraseña)) {
						s.setUsuario(usuario);
						s.setPerfil(Perfil.REGISTRADO);
						if (usuario.equals("admin")) {
							this.menuAdministrador();
							s.setPerfil(Perfil.ADMIN);
						} else {
							this.mostrarMenuUsuarioRegistrado(s);
						}
					} else {
						System.out.println("Usuario o contraseña incorrectos");
					}

					break;
				case 0:
					sesionActiva = false;
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
				teclado.next();
			}
		} while (sesionActiva);

		teclado.close();
	}

	public void mostrarMenuUsuarioRegistrado(Sesion s) {
		Scanner teclado = new Scanner(System.in);
		int opcion = -1;
		do {
			this.menuUsuarioRegistrado(s.getUsuario());
			try {
				opcion = teclado.nextInt();
				teclado.nextLine();
				if (opcion < 0 || opcion > 2) {
					System.out.println("Opción fuera de rango. Inténtelo de nuevo.");
					continue;
				}

				switch (opcion) {
				case 1:
					this.mostrarMenuGestionarMensajes(s);
					break;
				case 2:
					System.out.println("Hasta luego " + s.getUsuario());
					s.cerrarSesion();
					this.mostrarMenuInvitado();
					break;
				case 0:
					sesionActiva = false;
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
				teclado.next();
			}

		} while (sesionActiva);

	}

	public void mostrarMenuGestionarMensajes(Sesion s) {
		Scanner teclado = new Scanner(System.in);
		int opcion = -1;
		do {
			this.menuGestionarMensajes();
			try {
				opcion = teclado.nextInt();
				teclado.nextLine();
				if (opcion < 0 || opcion > 5) {
					System.out.println("Opción fuera de rango. Inténtelo de nuevo.");
					continue;
				}

				switch (opcion) {
				case 1:

					break;
				case 2:

					break;
				case 3:

					break;
				case 4:

					break;
				case 5:
					this.mostrarMenuUsuarioRegistrado(s);
					break;
				case 0:
					sesionActiva = false;
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
				teclado.next();
			}

		} while (sesionActiva);
	}

}
