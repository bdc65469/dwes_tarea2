package controlador;

import conexionBD.ConexionBDD;
import dao.EjemplarDAO;
import dao.MensajeDAO;
import dao.PersonaDAO;
import modelo.Mensaje;

public class ServiciosMensaje {
	
	private ConexionBDD factoria;
	private PersonaDAO personaDao;
	private EjemplarDAO ejemplarDao;
	private MensajeDAO mensajeDao;
	
	public ServiciosMensaje() {
		factoria = ConexionBDD.getCon();
		personaDao = factoria.getPersonaDAO();
		ejemplarDao = factoria.getEjemplarDAO();
		mensajeDao = factoria.getMensajeDAO();	
	}
	
	public int crearMensaje(Mensaje m) {
		return mensajeDao.crearMensaje(m);
	}

}
