package Principal;

import java.util.InputMismatchException;
import java.util.Scanner;

import controlador.Comprobaciones;
import controlador.ServiciosPlanta;
import dao.CredencialesDAO;
import dao.EjemplarDAO;
import dao.PersonaDAO;
import dao.PlantaDAO;
import modelo.Ejemplar;
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
		ServiciosPlanta.verListaPlantas();

	}

	

	

	

}
