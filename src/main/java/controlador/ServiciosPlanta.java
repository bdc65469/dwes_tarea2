package controlador;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import conexionBD.ConexionBDD;
import dao.PlantaDAO;
import modelo.Planta;

public class ServiciosPlanta {
	
	private ConexionBDD factoria;
	private PlantaDAO plantaDao;
	
	public ServiciosPlanta() {
		factoria = factoria.getCon();
		plantaDao = factoria.getPlantaDAO();
	}
	
	/*
	public void añadirPlanta(Planta p) {
		if (Comprobaciones.validarCodigoPlanta(p.getCodigo())) {
			plantaDao.añadirPlanta(p);
			System.out.println("Planta añadida correctamente");
		}
	}*/

	/*
	public void actualizarPlanta() {
		Scanner teclado = new Scanner(System.in);
		verListaPlantas();
		int numFinal = plantaDao.listadoPlantas().size();
		int num = 0;
		do {
			try {
				System.out.println("Introduce el numero de la planta que quieres modificar: ");
				num = teclado.nextInt();
				if (num < 1 || num > numFinal) {
					System.out
							.println("Numero incorrecto. Tienes que introducir un número entre el 1 y el " + numFinal);
				} else {
					System.out.println("Introduce el nombre común nuevo");
					String nombrecomun = teclado.next();
					System.out.println("Introduce el nombre científico nuevo");
					String nombrecien = teclado.next();
					int actualizado = plantaDao.actualizarPlanta(plantaDao.listadoPlantas().get(num - 1), nombrecomun,
							nombrecien);
					if (actualizado > 0) {
						System.out.println("Actualizado correctamente");
					} else {
						System.out.println("No se ha podido actualizar");
					}

					teclado.close();

				}
			} catch (InputMismatchException e) {
				System.out.println("Error. Debes introducir un número");
				teclado.nextLine(); // Limpiar el buffer del scanner
			}

		} while (num < 1 || num > numFinal);

	}*/
	
	public List<Planta> listaPlantas(){
		return plantaDao.listadoPlantas();
	}


}
