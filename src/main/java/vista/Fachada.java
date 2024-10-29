package vista;

import controlador.ServiciosEjemplar;
import controlador.ServiciosFactory;
import controlador.ServiciosPersona;
import controlador.ServiciosPlanta;

public class Fachada {
	
	private static Fachada portal;
	
	ServiciosFactory f = ServiciosFactory.getServicios();
	
	ServiciosEjemplar ejemplarServ = f.getServiciosEjemplar();
	ServiciosPersona personaServ = f.getServiciosPersona();
	ServiciosPlanta plantaServ = f.getServiciosPlanta();
	
	public static Fachada getPortal() {
		if (portal==null) {
			portal = new Fachada();
		}
		return portal;
	}
	
	public void menuInvitado() {
		System.out.println("Seleccione una opcion:");
		System.out.println("1.  Ver Plantas.");
		System.out.println("2.  Iniciar sesión.");
		System.out.println("0.  Salir.");
	}
	
	public void menuAdministrador() {
		System.out.println("Bienvenido administrador");
		System.out.println("Seleccione una opcion:");
		System.out.println("1.  Registrar persona.");
		System.out.println("2.  Gestionar plantas.");
		System.out.println("3.  Gestionar ejemplares.");
		System.out.println("4.  Gestionar mensajes.");
		System.out.println("0.  Salir.");
	}
	
	public void menuGestionarPlantas() {
		System.out.println("Menú Gestión de Plantas");
		System.out.println("Seleccione una opcion:");
		System.out.println("1.  Insertar nueva planta.");
		System.out.println("2.  Modificar una planta.");
		System.out.println("0.  Salir.");
	}
	
	public void menuGestionarEjemplares() {
		System.out.println("Menú Gestión de Ejemplares");
		System.out.println("Seleccione una opcion:");
		System.out.println("1.  Introduce el número de la planta de la que quieres crear un ejemplar.");
		System.out.println("2.  Introduce el número de la planta/as que quieres ver sus ejemplares .");
		System.out.println("3.  Introduce el número del ejemplar que quieres ver sus mensajes .");
		System.out.println("0.  Salir.");
	}
	
	public void menuGestionarMensajes() {
		System.out.println("Menú Gestión de Mensajes");
		System.out.println("Seleccione una opcion:");
		System.out.println("1.  Introduce el número del ejemplar que quieres crear el mensaje.");
		System.out.println("2.  Filtrar mensajes por persona .");
		System.out.println("3.  Filtrar mensaje por un rango de fechas .");
		System.out.println("4.  Filtrar mensajes por planta .");
		System.out.println("0.  Salir.");
	}
	
	public void menuUsuarioRegistrado() {
		System.out.println("Bienvenido administrador");
		System.out.println("Seleccione una opcion:");
		System.out.println("1.  Gestionar mensajes.");
		System.out.println("0.  Salir.");
	}

}
