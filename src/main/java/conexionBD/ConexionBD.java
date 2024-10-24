package conexionBD;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

public class ConexionBD {

	private static String URL;
	private static String USUARIO;
	private static String PASS;

	private static void cargarConfiguracion() {

		Properties propiedades = new Properties();
	    // Usa el ClassLoader para buscar el archivo en el classpath
	    try (InputStream inputStream = ConexionBD.class.getClassLoader().getResourceAsStream("db.properties")) {
	        if (inputStream == null) {
	            System.out.println("Archivo de configuración no encontrado en el classpath");
	            return;
	        }
	        propiedades.load(inputStream);
	        
	        URL = propiedades.getProperty("url");
	        USUARIO = propiedades.getProperty("usuario");
	        PASS = propiedades.getProperty("password");

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public static Connection getConnection() {
	    Connection connection = null;
	    try {
	        MysqlDataSource m = new MysqlDataSource();
	        ConexionBD.cargarConfiguracion();
	        m.setUrl(URL);
	        m.setUser(USUARIO);
	        m.setPassword(PASS);
	        connection = m.getConnection();
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }
	    return connection; 
	}

}
