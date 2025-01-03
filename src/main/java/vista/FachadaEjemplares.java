package vista;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import controlador.Comprobaciones;
import controlador.ServiciosEjemplar;
import controlador.ServiciosFactory;
import controlador.ServiciosMensaje;
import controlador.ServiciosPersona;
import controlador.ServiciosPlanta;
import controlador.Sesion;
import modelo.Ejemplar;
import modelo.Mensaje;
import modelo.Planta;

public class FachadaEjemplares {
	
	Scanner teclado = new Scanner(System.in);
	ServiciosFactory f = ServiciosFactory.getServicios();
	private ServiciosPlanta plantaServ = f.getServiciosPlanta();
	private ServiciosEjemplar ejemplarServ = f.getServiciosEjemplar();
	private Comprobaciones comprobaciones = f.getComprobaciones();
	private ServiciosPersona personaServ = f.getServiciosPersona();
	private ServiciosMensaje mensajeServ = f.getServiciosMensaje();
	
	private Sesion s;
	
	public FachadaEjemplares(Sesion s) {
		this.s = s;
	}
	
	public void menuGestionarEjemplares() {
		System.out.println("");
		System.out.println("***********************************************");
		System.out.println("Menú Gestión de Ejemplares");
		System.out.println("1.  Crear ejemplar de una planta.");
		System.out.println("2.  Ver ejemplares de planta/as.");
		System.out.println("3.  Ver mensajes de un ejemplar.");
		System.out.println("0.  Salir.");
		System.out.println("Seleccione una opcion:");
	}
	
	public void mostrarMenuGestionEjemplares() {
		int opcion = -1;
		do {
			this.menuGestionarEjemplares();
			try {
				opcion = teclado.nextInt();
				teclado.nextLine();
				if (opcion < 0 || opcion > 3) {
					System.err.println("Opción fuera de rango. Inténtelo de nuevo.");
					continue;
				}

				switch (opcion) {
				case 1:
					// Crear ejemplar
					if (plantaServ.listaPlantas().size() == 0) {
						System.out.println("No hay plantas registradas.");
					} else {
						System.out.println("LISTA DE PLANTAS");
						System.out.printf("%-10s %-30s %-40s %20s%n", "ÍNDICE", "NOMBRE COMÚN", "NOMBRE CIENTÍFICO", "CODIGO");
						System.out.println("----------------------------------------------------------------------------------------------------------------------------");
						for (int i = 0; i < plantaServ.listaPlantas().size(); i++) {
							System.out.printf("%-10s %-30s %-40s %20s%n", i+1, plantaServ.listaPlantas().get(i).getNombrecomun(), plantaServ.listaPlantas().get(i).getNombrecientifico(), plantaServ.listaPlantas().get(i).getCodigo());
						}
						int numFinal = plantaServ.listaPlantas().size();
						int num = 0;
						do {
							try {
								System.out.println("Introduce el numero de la planta que quieres crear un ejemplar: ");
								num = teclado.nextInt();
								teclado.nextLine();
								if (num < 1 || num > numFinal) {
									System.err.println(
											"Numero incorrecto. Tienes que introducir un número entre el 1 y el "
													+ numFinal);
								} else {
									Planta escogida = new Planta();
									escogida.setCodigo(plantaServ.listaPlantas().get(num - 1).getCodigo());
									escogida.setNombrecientifico(
											plantaServ.listaPlantas().get(num - 1).getNombrecientifico());
									escogida.setNombrecomun(plantaServ.listaPlantas().get(num - 1).getNombrecomun());
									Ejemplar nuevoEjemplar = ejemplarServ.crearEjemplar(escogida);
									if (nuevoEjemplar == null) {
										System.err.println("Error al crear el ejemplar");
									} else {
										String mensaje = "Nuevo ejemplar de "+escogida.getNombrecomun()+" creado.";
										Mensaje nuevo = new Mensaje(LocalDateTime.now(), mensaje, nuevoEjemplar.getId(),
												personaServ.obtenerIdPersonaPorUsuario(s.getUsuario()));
										if (mensajeServ.crearMensaje(nuevo) > 0) {
											System.out.println(
													"Se ha creado el ejemplar con el mensaje correspondiente correctamente.");
										}
									}

								}
							} catch (InputMismatchException e) {
								System.err.println("Error. Debes introducir un número");
								teclado.nextLine(); // Limpiar el buffer del scanner
							}

						} while (num < 1 || num > numFinal);
					}
					break;
				case 2:
					// Ver ejemplares de plantas
					ArrayList<Integer> ejemplaresPlantas = new ArrayList<Integer>();
					if (plantaServ.listaPlantas().size() == 0) {
						System.out.println("No hay plantas registradas.");
					} else {
						System.out.println("LISTA DE PLANTAS");
						System.out.printf("%-10s %-30s %-40s %20s%n", "ÍNDICE", "NOMBRE COMÚN", "NOMBRE CIENTÍFICO", "CODIGO");
						System.out.println("----------------------------------------------------------------------------------------------------------------------------");
						for (int i = 0; i < plantaServ.listaPlantas().size(); i++) {
							System.out.printf("%-10s %-30s %-40s %20s%n", i+1, plantaServ.listaPlantas().get(i).getNombrecomun(), plantaServ.listaPlantas().get(i).getNombrecientifico(), plantaServ.listaPlantas().get(i).getCodigo());
						}
						int numFinal1 = plantaServ.listaPlantas().size();
						int num1 = 0;
						do {
							try {
								System.out.println(
										"Introduce el numero de la/s planta/as que quieres ver los ejemplares. Pulsa 0 cuando quieras salir: ");
								num1 = teclado.nextInt();
								teclado.nextLine();
								if (num1 < 0 || num1 > numFinal1) {
									System.err.println(
											"Numero incorrecto. Tienes que introducir un número entre el 1 y el "
													+ numFinal1 + " o el 0 para salir");
								} else {
									if (num1 == 0) {
										break;
									}
									if (ejemplaresPlantas.contains(num1)) {
										System.out.println("Ya introdujiste esa planta;");
									} else {
										ejemplaresPlantas.add(num1);
									}
								}

							} catch (InputMismatchException e) {
								System.err.println("Error. Debes introducir un número");
								teclado.nextLine(); // Limpiar el buffer del scanner
							}

						} while (num1 != 0);
						for (Integer i : ejemplaresPlantas) {
							if (ejemplarServ.filtarEjemplaresPlanta(plantaServ.listaPlantas().get(i - 1)).size() == 0) {
								System.out.println("No hay ejemplares de la planta "
										+ plantaServ.listaPlantas().get(i - 1).getNombrecomun());
							} else {
								System.out.println("EJEMPLARES PLANTA -->  "
										+ plantaServ.listaPlantas().get(i - 1).getNombrecomun());
								System.out.printf("%-50s %-40s %-60s %n", "NOMBRE EJEMPLAR", "NºMENSAJES", "FECHA DEL ÚLTIMO MENSAJE");
								System.out.println("-------------------------------------------------------------------------------------------------------------------");
								for (Ejemplar e : ejemplarServ
										.filtarEjemplaresPlanta(plantaServ.listaPlantas().get(i - 1))) {
									
									if (mensajeServ.obtenerMensajesPorIdEjemplar(e.getId()).size() == 0) {
										System.out.printf("%-50s %-40s %-60s %n", e.getNombre(), 0, "NULA");
									} else {
										List<Mensaje> listaMensajes = mensajeServ.obtenerMensajesPorIdEjemplar(e.getId());
										LocalDateTime ultimo = mensajeServ.obtenerMensajesPorIdEjemplar(e.getId()).get(0).getFechahora();										
										for (int m=0; m<listaMensajes.size(); m++) {
											if(ultimo.isBefore(listaMensajes.get(m).getFechahora())) {
												ultimo = listaMensajes.get(m).getFechahora();
											}
										}
										
										System.out.printf("%-50s %-40s %-60s %n", e.getNombre() , listaMensajes.size(), comprobaciones.formatoFecha(ultimo));
										
									}
								}
								System.out.println("");
							}

						}
					}
					break;
				case 3:
					// Ver mensajes de un ejemplar
					if (ejemplarServ.listadoEjemplares().size() == 0) {
						System.out.println("No hay ejemplares registrados.");
					} else {
						System.out.println("LISTA DE EJEMPLARES");
						System.out.printf("%-10s %-30s%n", "ÍNDICE", "NOMBRE");
						System.out.println("---------------------");
						for (int i = 0; i < ejemplarServ.listadoEjemplares().size(); i++) {
							System.out.printf("%-10s %-30s%n", i+1, ejemplarServ.listadoEjemplares().get(i).getNombre());
						}
						int numFinalEjem = ejemplarServ.listadoEjemplares().size();
						int numEjem = 0;
						do {
							try {
								System.out.println("Introduce el numero del que quieres ver sus mensajes: ");
								numEjem = teclado.nextInt();
								teclado.nextLine();
								if (numEjem < 1 || numEjem > numFinalEjem) {
									System.err.println(
											"Numero incorrecto. Tienes que introducir un número entre el 1 y el "
													+ numFinalEjem);
								} else {
									if (mensajeServ
											.obtenerMensajesPorIdEjemplar(
													ejemplarServ.listadoEjemplares().get(numEjem - 1).getId())
											.size() == 0) {
										System.out.println("No hay mensajes del ejemplar "
												+ ejemplarServ.listadoEjemplares().get(numEjem - 1).getNombre());
									} else {
										System.out.println("Mensajes del ejemplar "
												+ ejemplarServ.listadoEjemplares().get(numEjem - 1).getNombre() + "\n");
										System.out.printf("%-80s %-30s %-20s %20s%n", "MENSAJE", "FECHA", "AUTOR", "EMAIL AUTOR");
										System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
										for (Mensaje m : mensajeServ.obtenerMensajesPorIdEjemplar(
												ejemplarServ.listadoEjemplares().get(numEjem - 1).getId())) {
											
											System.out.printf("%-80s %-30s %-20s %20s%n",m.getMensaje(),
													comprobaciones.formatoFecha(m.getFechahora()),
													personaServ.obtenerPersonaPorId(m.getIdPersona()).getNombre(),
													personaServ.obtenerPersonaPorId(m.getIdPersona()).getEmail());
										}
									}
								}
							} catch (InputMismatchException e) {
								System.err.println("Error. Debes introducir un número");
								teclado.nextLine(); // Limpiar el buffer del scanner
							}

						} while (numEjem < 1 || numEjem > numFinalEjem);
					}
					break;
				case 0:
					//Cerrar menu
					return;
				}
			} catch (InputMismatchException e) {
				System.err.println("Entrada inválida. Por favor, ingrese un número entero.");
				teclado.next();
			}

		} while (opcion != 0);
	}

}
