package controlador;

import java.util.List;

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
	
	public List<Planta> listaPlantas(){
		return plantaDao.listadoPlantas();
	}
	
	public boolean existeCodigoPlanta(String codigo) {
		return plantaDao.existeCodigo(codigo);
	}
	
	public int añadirPlanta (Planta p) {
		return plantaDao.añadirPlanta(p);
	}
	
	public int actualizarPlanta (Planta p, String nombrecomun, String nombrecientifico) {
		return plantaDao.actualizarPlanta(p, nombrecomun, nombrecientifico);
	}


}
