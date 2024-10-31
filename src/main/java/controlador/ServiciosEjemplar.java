package controlador;

import java.util.InputMismatchException;
import java.util.Scanner;

import conexionBD.ConexionBDD;
import dao.EjemplarDAO;
import dao.PlantaDAO;
import modelo.Ejemplar;
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
	
	public Ejemplar crearEjemplar(Planta p) {
		return ejemplarDao.crearEjemplar(p);
	}
	
	
	

}
