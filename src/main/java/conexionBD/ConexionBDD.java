package conexionBD;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

public class ConexionBDD {
	
	private Connection con;

	private static ConexionBDD bd;

	// el patron factory realiza la conexi�n
	private ConexionBDD() {
		Properties propiedades = new Properties();
		MysqlDataSource m = new MysqlDataSource();
		

		 try (InputStream inputStream = ConexionBD.class.getClassLoader().getResourceAsStream("db.properties")) {
		        if (inputStream == null) {
		            System.out.println("Archivo de configuración no encontrado en el classpath");
		            return;
		        }
		        propiedades.load(inputStream);
		        
		        m.setUrl(propiedades.getProperty("url"));
		        m.setUser(propiedades.getProperty("usuario"));
		        m.setPassword(propiedades.getProperty("password"));
		        con = m.getConnection();
		        		   


		} catch (FileNotFoundException e) {
			System.out.println("Error al acceder al fichero properties " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error al leer las propiedades del fichero properties" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error al conectar a la base de datos:usuario,password....");
		}
	}
	
	public static ConexionBDD getCon() {
		if (bd==null)
			bd=new ConexionBDD();
		return bd;
	}

}
