package controlador;

public class ServiciosFactory {

	public static ServiciosFactory servicios;

	public static ServiciosFactory getServicios() {
		if (servicios == null)
			servicios = new ServiciosFactory();
		return servicios;

	}
	
	public ServiciosEjemplar getServiciosEjemplar() {
		return new ServiciosEjemplar();
	}
	
	public ServiciosPersona getServiciosPersona() {
		return new ServiciosPersona();
	}
	
	public ServiciosPlanta getServiciosPlanta() {
		return new ServiciosPlanta();
	}

}
