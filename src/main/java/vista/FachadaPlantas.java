package vista;

import java.util.InputMismatchException;
import java.util.Scanner;

import controlador.Comprobaciones;
import controlador.ServiciosFactory;
import controlador.ServiciosPlanta;
import modelo.Planta;

public class FachadaPlantas {
	
	private Scanner teclado = new Scanner(System.in);
	private ServiciosFactory f = ServiciosFactory.getServicios();
	private ServiciosPlanta plantaServ = f.getServiciosPlanta();
	private Comprobaciones comprobaciones = f.getComprobaciones();
	
	public void menuGestionarPlantas() {
		System.out.println("");
		System.out.println("***********************************************");
		System.out.println("Menú Gestión de Plantas");
		System.out.println("1.  Insertar nueva planta.");
		System.out.println("2.  Modificar una planta.");
		System.out.println("0.  Salir.");
		System.out.println("Seleccione una opcion:");
	}
	
	public void mostrarMenuGestionarPlantas() {
		int opcion = -1;
		do {
			this.menuGestionarPlantas();
			try {
				opcion = teclado.nextInt();
				teclado.nextLine();
				if (opcion < 0 || opcion > 2) {
					System.err.println("Opción fuera de rango. Inténtelo de nuevo.");
					continue;
				}

				switch (opcion) {
				case 1:
					// Insertar nueva planta
					String codigo = "";
					do {
						System.out.println(
								"Introduce el codigo de la planta (Sin números, espacios, dierisis o carácteres especiales)");
						codigo = teclado.nextLine();

						if (!comprobaciones.esCodigoValido(codigo)) {
							System.out.println(
									"Formato de codigo incorrecto. Recuerda que no puede contener números, espacios, dierisis o carácteres especiales");
						}

						if (plantaServ.existeCodigoPlanta(codigo)) {
							System.out.println("Ya existe una planta con ese codigo");
						}

					} while (!comprobaciones.esCodigoValido(codigo) || plantaServ.existeCodigoPlanta(codigo));

					String nombrecomun = "";
					do {
						System.out.println("Introduce el nombre común de la planta. ");
						nombrecomun = teclado.nextLine();

						if (!comprobaciones.nombreValido(nombrecomun)) {
							System.out.println("Nombre comun incorrecto. No puede contener número o solo espacios");
						}
					} while (!comprobaciones.nombreValido(nombrecomun));

					String nombrecientifico = "";
					do {
						System.out.println("Introduce el nombre científico de la planta");
						nombrecientifico = teclado.nextLine();

						if (!comprobaciones.nombreValido(nombrecientifico)) {
							System.out
									.println("Nombre científico incorrecto. No puede contener número o solo espacios");
						}
					} while (!comprobaciones.nombreValido(nombrecientifico));

					Planta nueva = new Planta(codigo.toUpperCase(), nombrecomun, nombrecientifico);

					if (plantaServ.añadirPlanta(nueva) > 0) {
						System.out.println("Planta añadida correctamente");
					} else {
						System.out.println("No se pudo añadir la planta");
					}

					break;
				case 2:
					// Modificar una planta
					if (plantaServ.listaPlantas().size() == 0) {
						System.out.println("No hay plantas registradas");
					} else {
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
									System.err.println(
											"Numero incorrecto. Tienes que introducir un número entre el 1 y el "
													+ numFinal);
								} else {
									String actnombrecomun = "";
									do {
										System.out.println("Introduce el nuevo nombre común de la planta. ");
										actnombrecomun = teclado.nextLine();

										if (!comprobaciones.nombreValido(actnombrecomun)) {
											System.out.println(
													"Nombre comun incorrecto. No puede contener número o solo espacios");
										}
									} while (!comprobaciones.nombreValido(actnombrecomun));

									String nombrecien = "";
									do {
										System.out.println("Introduce el nuevo nombre científico de la planta");
										nombrecien = teclado.nextLine();

										if (!comprobaciones.nombreValido(nombrecien)) {
											System.out.println(
													"Nombre científico incorrecto. No puede contener número o solo espacios");
										}
									} while (!comprobaciones.nombreValido(nombrecien));
									if (plantaServ.actualizarPlanta(plantaServ.listaPlantas().get(num - 1),
											actnombrecomun, nombrecien) > 0) {
										System.out.println("Actualizado correctamente");
									} else {
										System.err.println("No se ha podido actualizar");
									}
								}
							} catch (InputMismatchException e) {
								System.err.println("Error. Debes introducir un número");
								teclado.nextLine(); // Limpiar el buffer del scanner
							}

						} while (num < 1 || num > numFinal);
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
