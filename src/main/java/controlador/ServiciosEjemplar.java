package controlador;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
	
	public Set<Ejemplar> filtarEjemplaresPlanta(Planta p){
		return ejemplarDao.obtenerEjemplaresPorPlanta(p);
	}
	
	public List<Ejemplar> listadoEjemplares(){
		return ejemplarDao.listadoEjemplares();
	}
	
	

}
