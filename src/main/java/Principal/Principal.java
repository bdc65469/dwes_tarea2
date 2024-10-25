package Principal;


import java.util.Scanner;



import controlador.Comprobaciones;
import dao.PlantaDAO;
import modelo.Planta;

public class Principal {

	public static void main(String[] args) {

		conexionBD.ConexionBD.getConnection();

		/*
		 * Scanner teclado = new Scanner(System.in);
		 * System.out.println("Dame el codigo de una nueva Planta"); String codigo =
		 * teclado.nextLine();
		 * 
		 * System.out.println("Dame el nombre comun de una nueva Planta"); String
		 * nombrecomun = teclado.nextLine();
		 * 
		 * System.out.println("Dame el nombre cientifico de una nueva Planta"); String
		 * nombrecientifico = teclado.nextLine();
		 * 
		 * Planta planta = new Planta(codigo, nombrecomun, nombrecientifico);
		 */

		// añadirPlanta(planta);
		actualizarPlanta();

	}

	public static void añadirPlanta(Planta p) {
		PlantaDAO plantaDao = new PlantaDAO();
		if (Comprobaciones.validarCodigoPlanta(p.getCodigo())) {
			plantaDao.añadirPlanta(p);
			System.out.println("Planta añadida correctamente");
		}
	}

	
	//Acordarme de capturar la excepcion para cada vez que pido un entero
	public static void actualizarPlanta() {
		Scanner teclado = new Scanner(System.in);
		PlantaDAO plantaDao = new PlantaDAO();
		verListaPlantas();
		int numFinal = plantaDao.listadoPlantas().size();
		int num = 0;
		do {
			System.out.println("Introduce el numero de la planta que quieres modificar: ");
			num = teclado.nextInt();
			if (num < 1 || num > numFinal) {
				System.out.println("Numero incorrecto. Tienes que introducir un número entre el 1 y el " + numFinal);
			}else {
				System.out.println("Introduce el nombre común nuevo");
				String nombrecomun = teclado.next();
				System.out.println("Introduce el nombre científico nuevo");
				String nombrecien = teclado.next();
				int actualizado =  plantaDao.actualizarPlanta(plantaDao.listadoPlantas().get(num-1), nombrecomun, nombrecien);
				if (actualizado > 0) {
					System.out.println("Actualizado correctamente");
				}else {
					System.out.println("No se ha podido actualizar");
				}
				
				teclado.close();
				
			}
			
			
		} while (num < 1 || num > numFinal);

	}

	public static void verListaPlantas() {
		PlantaDAO plantaDao = new PlantaDAO();
		if (plantaDao.listadoPlantas().size() == 0) {
			System.out.println("No hay plantas almacenadas");
		} else {
			System.out.println("Lista de plantas");
			for (int i = 0; i < plantaDao.listadoPlantas().size(); i++) {
				int numero = i + 1;
				System.out.println(numero + "ª " + plantaDao.listadoPlantas().get(i));
			}
		}

	}

}
