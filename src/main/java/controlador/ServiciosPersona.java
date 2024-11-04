package controlador;


import conexionBD.ConexionBDD;
import dao.PersonaDAO;
import modelo.Persona;

public class ServiciosPersona {
	
	private ConexionBDD factoria;
	private PersonaDAO personaDao;

	
	public ServiciosPersona() {
		factoria = ConexionBDD.getCon();
		personaDao = factoria.getPersonaDAO();
		
	}
	
	public boolean existeEmail(String email) {
		return personaDao.existeEmail(email);
	}
	
	public Long obtenerIdPersonaPorUsuario(String usuario) {
		return personaDao.obtenerIdporUsuario(usuario);
	}
	
	public Persona obtenerPersonaPorId(Long id) {
		return personaDao.obtenerPersonaPorId(id);
	}

}
