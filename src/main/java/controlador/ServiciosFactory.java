package controlador;

public class ServiciosFactory {

	private static ServiciosFactory servicios;
	
	private ServiciosCredenciales serviCre;
	private ServiciosEjemplar serviEjem;
	private ServiciosMensaje serviMen;
	private ServiciosPersona serviPer;
	private ServiciosPlanta serviPlan;
	private Comprobaciones comprobaciones;
	
	private ServiciosFactory() {
		
		serviCre = new ServiciosCredenciales();
		serviEjem = new ServiciosEjemplar();
		serviMen = new ServiciosMensaje();
		serviPer = new ServiciosPersona();
		serviPlan = new ServiciosPlanta();
		comprobaciones = new Comprobaciones();
		
	}

	public static ServiciosFactory getServicios() {
		if (servicios == null)
			servicios = new ServiciosFactory();
		return servicios;

	}
	
	public ServiciosEjemplar getServiciosEjemplar() {
		return serviEjem;
	}
	
	public ServiciosPersona getServiciosPersona() {
		return serviPer;
	}
	
	public ServiciosPlanta getServiciosPlanta() {
		return serviPlan;
	}
	
	public ServiciosMensaje getServiciosMensaje() {
		return serviMen;
	}
	
	public ServiciosCredenciales getServiciosCredenciales() {
		return serviCre;
	}
	
	public Comprobaciones getComprobaciones() {
		return comprobaciones;
	}

}
