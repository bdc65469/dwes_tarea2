package vista;

import java.time.LocalDate;
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
import modelo.Ejemplar;
import modelo.Mensaje;
import modelo.Planta;

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
		System.out.println("5.  Cerrar sesión.");
		System.out.println("0.  Cerrar aplicación.");
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
		System.out.println("1.  Crear ejemplar de una planta.");
		System.out.println("2.  Ver ejemplares de planta/as .");
		System.out.println("3.  Ver mensajes de un ejemplar .");
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
		System.out.println("Bienvenido/a " + usuario);
		System.out.println("1.  Gestionar ejemplares.");
		System.out.println("2.  Gestionar mensajes.");
		System.out.println("3.  Cerrar sesión.");
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
							this.mostrarMenuAdministrador(s);
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
				if (opcion < 0 || opcion > 3) {
					System.out.println("Opción fuera de rango. Inténtelo de nuevo.");
					continue;
				}

				switch (opcion) {
				case 1:
					this.mostrarMenuGestionEjemplares(s);
					break;
				case 2:
					this.mostrarMenuGestionarMensajes(s);
					break;
				case 3:
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

	public void mostrarMenuAdministrador (Sesion s) {
		
		Scanner teclado = new Scanner(System.in);
		int opcion = -1;
		do {
			this.menuAdministrador();
			try {
				opcion = teclado.nextInt();
				teclado.nextLine();
				if (opcion < 0 || opcion > 5) {
					System.out.println("Opción fuera de rango. Inténtelo de nuevo.");
					continue;
				}

				switch (opcion) {
				case 1:
					System.out.println("Introduce el nombre de la persona: ");
					String nombre = teclado.nextLine();
					
					String email = "";
					do {
						System.out.println("Introduce el email de la persona: ");
						email = teclado.nextLine();

						if (Comprobaciones.comprobarEspaciosBlanco(email)) {
							System.out.println("El email no puede contener espacios en blanco");
						}

						if (personaServ.existeEmail(email)) {
							System.out.println("El email ya existe");
						}
					} while (Comprobaciones.comprobarEspaciosBlanco(email) || personaServ.existeEmail(email));
					
					String usuario = "";
					
					do {
						System.out.println("Introduce el usuario de la persona: ");
						usuario = teclado.nextLine();

						if (Comprobaciones.comprobarEspaciosBlanco(usuario)) {
							System.out.println("El usuario no puede contener espacios en blanco");
						}
						
						if (usuario.equalsIgnoreCase("admin")) {
							System.out.println("Nombre de usuario reservado.");
						}

						if (credendialesServ.existeUsuario(usuario)) {
							System.out.println("El usuario ya existe");
						}
					} while (Comprobaciones.comprobarEspaciosBlanco(usuario) || credendialesServ.existeUsuario(usuario) || usuario.equalsIgnoreCase("admin"));
					
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
					
					if (personaServ.crearUsuario(nombre, email, usuario, password)>0) {
						System.out.println("Usuario registrado correctamente");
					}else {
						System.out.println("No se ha podido registrar el usuario");
					}
					break;
				case 2:
					this.mostrarMenuGestionarPlantas();
					break;
				case 3:
					this.mostrarMenuGestionEjemplares(s);
					break;
				case 4:
					this.mostrarMenuGestionarMensajes(s);
					break;
				case 5:
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
	
	public void mostrarMenuGestionarPlantas() {
		
		Scanner teclado = new Scanner(System.in);
		int opcion = -1;
		do {
			this.menuGestionarPlantas();
			try {
				opcion = teclado.nextInt();
				teclado.nextLine();
				if (opcion < 0 || opcion > 2) {
					System.out.println("Opción fuera de rango. Inténtelo de nuevo.");
					continue;
				}

				switch (opcion) {
				case 1:
					String codigo = "";
					do {
						System.out.println("Introduce el codigo de la planta (Sin números, espacios, dierisis o carácteres especiales)");
						codigo = teclado.nextLine();
						
						if (!Comprobaciones.esCodigoValido(codigo)) {
							System.out.println("Formato de codigo incorrecto. Recuerda que no puede contener números, espacios, dierisis o carácteres especiales");
						}
						
						
						if (plantaServ.existeCodigoPlanta(codigo)) {
							System.out.println("Ya existe una planta con ese codigo");
						}
						
					}while (!Comprobaciones.esCodigoValido(codigo) || plantaServ.existeCodigoPlanta(codigo));
					
					String nombrecomun = "";
					do {
						System.out.println("Introduce el nombre común de la planta. ");
						nombrecomun = teclado.nextLine();
						
						if (!Comprobaciones.nombreValido(nombrecomun)) {
							System.out.println("Nombre comun incorrecto. No puede contener número o solo espacios");
						}
					}while (!Comprobaciones.nombreValido(nombrecomun));
					
					String nombrecientifico = "";
					do {
						System.out.println("Introduce el nombre científico de la planta");
						nombrecientifico = teclado.nextLine();
						
						if (!Comprobaciones.nombreValido(nombrecientifico)) {
							System.out.println("Nombre científico incorrecto. No puede contener número o solo espacios");
						}
					}while (!Comprobaciones.nombreValido(nombrecientifico));

					
					Planta nueva = new Planta (codigo.toUpperCase(), nombrecomun, nombrecientifico);
					
					if (plantaServ.añadirPlanta(nueva)>0) {
						System.out.println("Planta añadida correctamente");
					}else {
						System.out.println("No se pudo añadir la planta");
					}				
					
					break;
				case 2:
					System.out.println("Lista de plantas");
					for (int i = 0; i < plantaServ.listaPlantas().size(); i++) {
						int numero = i + 1;
						System.out.println(numero + "ª " + plantaServ.listaPlantas().get(i));
					}
					int numFinal = plantaServ.listaPlantas().size();
					int num = 0;
					do {
						try {
							System.out.println("Introduce el numero de la planta que quieres modificar: ");
							num = teclado.nextInt();
							teclado.nextLine();
							if (num < 1 || num > numFinal) {
								System.out
										.println("Numero incorrecto. Tienes que introducir un número entre el 1 y el " + numFinal);
							} else {
								String actnombrecomun = "";
								do {
									System.out.println("Introduce el nuevo nombre común de la planta. ");
									actnombrecomun = teclado.nextLine();
									
									if (!Comprobaciones.nombreValido(actnombrecomun)) {
										System.out.println("Nombre comun incorrecto. No puede contener número o solo espacios");
									}
								}while (!Comprobaciones.nombreValido(actnombrecomun));
								
								String nombrecien = "";
								do {
									System.out.println("Introduce el nuevo nombre científico de la planta");
									nombrecien = teclado.nextLine();
									
									if (!Comprobaciones.nombreValido(nombrecien)) {
										System.out.println("Nombre científico incorrecto. No puede contener número o solo espacios");
									}
								}while (!Comprobaciones.nombreValido(nombrecien));
								if (plantaServ.actualizarPlanta(plantaServ.listaPlantas().get(num-1), actnombrecomun, nombrecien)>0) {
									System.out.println("Actualizado correctamente");
								} else {
									System.out.println("No se ha podido actualizar");
								}
							}
						} catch (InputMismatchException e) {
							System.out.println("Error. Debes introducir un número");
							teclado.nextLine(); // Limpiar el buffer del scanner
						}

					} while (num < 1 || num > numFinal);
					break;
				
				case 0:
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
				teclado.next();
			}

		} while (opcion != 0);
		
	}
	
	public void mostrarMenuGestionEjemplares (Sesion s) {
		Scanner teclado = new Scanner(System.in);
		int opcion = -1;
		do {
			this.menuGestionarEjemplares();
			try {
				opcion = teclado.nextInt();
				teclado.nextLine();
				if (opcion < 0 || opcion > 3) {
					System.out.println("Opción fuera de rango. Inténtelo de nuevo.");
					continue;
				}

				switch (opcion) {
				case 1:
					System.out.println("Lista de plantas");
					for (int i = 0; i < plantaServ.listaPlantas().size(); i++) {
						int numero = i + 1;
						System.out.println(numero + "ª " + plantaServ.listaPlantas().get(i));
					}
					int numFinal = plantaServ.listaPlantas().size();
					int num = 0;
					do {
						try {
							System.out.println("Introduce el numero de la planta que quieres crear un ejemplar: ");
							num = teclado.nextInt();
							teclado.nextLine();
							if (num < 1 || num > numFinal) {
								System.out
										.println("Numero incorrecto. Tienes que introducir un número entre el 1 y el " + numFinal);
							} else {
								Planta escogida = new Planta();
								escogida.setCodigo(plantaServ.listaPlantas().get(num - 1).getCodigo());
								escogida.setNombrecientifico(plantaServ.listaPlantas().get(num - 1).getNombrecientifico());
								escogida.setNombrecomun(plantaServ.listaPlantas().get(num - 1).getNombrecomun());
							    Ejemplar nuevoEjemplar = ejemplarServ.crearEjemplar(escogida);
							    if (nuevoEjemplar==null) {
							    	System.out.println("Error al crear el ejemplar");
							    }else {
							    	System.out.println("Introduce el mensaje que quieres añadir al ejemplar.");
								    String mensaje = teclado.nextLine();
								    Mensaje nuevo = new Mensaje (LocalDate.now(), mensaje, nuevoEjemplar.getId(), personaServ.obtenerIdPersonaPorUsuario(s.getUsuario()));
								    if(mensajeServ.crearMensaje(nuevo)>0) {
								    	System.out.println("Se ha creado el ejemplar con el mensaje correspondiente correctamente.");
								    }
							    }
							  
							}
						} catch (InputMismatchException e) {
							System.out.println("Error. Debes introducir un número");
							teclado.nextLine(); // Limpiar el buffer del scanner
						}

					} while (num < 1 || num > numFinal);					
					break;
				case 2:
				
					break;
				case 3:
					break;
				case 0:
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
				teclado.next();
			}

		} while (opcion != 0);
	}
}
