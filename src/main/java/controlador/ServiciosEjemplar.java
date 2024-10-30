package controlador;

import java.util.InputMismatchException;
import java.util.Scanner;

import conexionBD.ConexionBDD;
import dao.EjemplarDAO;
import dao.PlantaDAO;
import modelo.Planta;

public class ServiciosEjemplar {
	
	private ConexionBDD factoria;
	private PlantaDAO plantaDao;
	private EjemplarDAO ejemplarDao;
	
	public ServiciosEjemplar() {
		factoria = ConexionBDD.getCon();
		plantaDao = factoria.getPlantaDAO();
		ejemplarDao = factoria.getEjemplarDAO();
	}
	
	public void crearEjemplar() {
		Scanner teclado = new Scanner(System.in);
		//ServiciosPlanta.verListaPlantas();
		int numFinal = plantaDao.listadoPlantas().size();
		int num = 0;
		do {
			try {
				System.out.println("Introduce el numero de la planta que quieres crear un ejemplar: ");
				num = teclado.nextInt();
				if (num < 1 || num > numFinal) {
					System.out
							.println("Numero incorrecto. Tienes que introducir un número entre el 1 y el " + numFinal);
				} else {
					Planta escogida = new Planta();
					escogida.setCodigo(plantaDao.listadoPlantas().get(num - 1).getCodigo());
					escogida.setNombrecientifico(plantaDao.listadoPlantas().get(num - 1).getNombrecientifico());
					escogida.setNombrecomun(plantaDao.listadoPlantas().get(num - 1).getNombrecomun());
					if (ejemplarDao.crearEjemplar(escogida) > 0) {
						System.out.println("Ejemplar añadido correctamente");
					} else {
						System.out.println("No se pudo añadir el ejemplar");
					}
					teclado.close();

				}
			} catch (InputMismatchException e) {
				System.out.println("Error. Debes introducir un número");
				teclado.nextLine(); // Limpiar el buffer del scanner
			}

		} while (num < 1 || num > numFinal);

	}

}
