package controlador;

import java.util.List;
import java.util.Set;

import conexionBD.ConexionBDD;
import dao.EjemplarDAO;
import modelo.Ejemplar;
import modelo.Planta;

public class ServiciosEjemplar {
	
	private ConexionBDD factoria;

	private EjemplarDAO ejemplarDao;
	
	public ServiciosEjemplar() {
		factoria = ConexionBDD.getCon();
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
	
	public Ejemplar obtenerEjemplarporId(Long id) {
		return ejemplarDao.obtenerEjemplarPorId(id);
	}
	
	

}
