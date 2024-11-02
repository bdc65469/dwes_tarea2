package controlador;

import java.util.List;

import conexionBD.ConexionBDD;
import dao.MensajeDAO;
import modelo.Mensaje;

public class ServiciosMensaje {
	
	private ConexionBDD factoria;
	private MensajeDAO mensajeDao;
	
	public ServiciosMensaje() {
		factoria = ConexionBDD.getCon();
		mensajeDao = factoria.getMensajeDAO();	
	}
	
	public int crearMensaje(Mensaje m) {
		return mensajeDao.crearMensaje(m);
	}
	
	public List<Mensaje> obtenerMensajesPorIdEjemplar (Long id){
		return mensajeDao.obtenerMensajesDeEjemplar(id);
	}
	
	public List<Mensaje> obtenerMensajesPorPersona (Long id){
		return mensajeDao.obtenerMensajesPorPersona(id);
	}
	
	public List<Mensaje> obtenerMensajesPorPlanta (String nombrePlanta){
		return mensajeDao.obtenerMensajesPorPlanta(nombrePlanta);
	}

}
