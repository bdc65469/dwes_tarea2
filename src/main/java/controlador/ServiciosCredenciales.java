package controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import conexionBD.ConexionBDD;
import dao.CredencialesDAO;
import dao.PersonaDAO;

public class ServiciosCredenciales {

	private ConexionBDD factoria;
	private CredencialesDAO credencialesDao;
	private PersonaDAO personaDao;

	public ServiciosCredenciales() {
		factoria = ConexionBDD.getCon();
		credencialesDao = factoria.getCredencialesDAO();
		personaDao = factoria.getPersonaDAO();
	}
	
	
	public int crearUsuario(String nombre,String email,String usuario,String password) {	
		return credencialesDao.crearCredenciales(usuario, password, personaDao.insertarPersona(nombre, email));
	}

	public boolean Login(String usuario, String contrasena) {
		boolean valido = false;
		Properties propiedades = new Properties();
		if (!credencialesDao.Login(usuario, contrasena)) {
			
			
			try (InputStream inputStream = ConexionBDD.class.getClassLoader().getResourceAsStream("db.properties")) {
				if (inputStream == null) {
					System.err.println("Archivo de configuración no encontrado en el classpath");
				
				}
				propiedades.load(inputStream);
				
				
				if (usuario.equals(propiedades.getProperty("usuarioAdmin")) && contrasena.equals(propiedades.getProperty("contrasenaAdmin"))) {
					valido = true;
				}
				

			} catch (FileNotFoundException e) {
				System.err.println("Error al acceder al fichero properties " + e.getMessage());
			} catch (IOException e) {
				System.err.println("Error al leer las propiedades del fichero properties" + e.getMessage());
			}
		}else {
			valido = true;
		}
		return valido;
	}
	
	public boolean existeUsuario (String usuario) {
		return credencialesDao.existeUsuario(usuario);
	}

}
