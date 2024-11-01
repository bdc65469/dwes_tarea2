package controlador;

import java.util.Scanner;

import conexionBD.ConexionBDD;
import dao.CredencialesDAO;
import dao.PersonaDAO;
import modelo.Persona;

public class ServiciosPersona {
	
	private ConexionBDD factoria;
	private PersonaDAO personaDao;
	private CredencialesDAO credencialesDao;
	
	public ServiciosPersona() {
		factoria = ConexionBDD.getCon();
		personaDao = factoria.getPersonaDAO();
		credencialesDao = factoria.getCredencialesDAO();
	}
	
	public boolean existeEmail(String email) {
		return personaDao.existeEmail(email);
	}
	
	public int crearUsuario(String nombre,String email,String usuario,String password) {	
		return personaDao.insertarPersona(nombre, email, credencialesDao.crearCredenciales(usuario, password));	
	}
	
	public Long obtenerIdPersonaPorUsuario(String usuario) {
		return personaDao.obtenerIdporUsuario(usuario);
	}
	
	public Persona obtenerPersonaPorId(Long id) {
		return personaDao.obtenerPersonaPorId(id);
	}

}
