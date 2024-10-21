package modelo;

import java.time.LocalDate;

public class Mensaje {
	
	private Long id;
	private LocalDate fechahora;
	private String mensaje;
	
	public Mensaje() {}

	public Mensaje(Long id, LocalDate fechahora, String mensaje) {
		super();
		this.id = id;
		this.fechahora = fechahora;
		this.mensaje = mensaje;
	}

	public Mensaje(LocalDate fechahora, String mensaje) {
		super();
		this.fechahora = fechahora;
		this.mensaje = mensaje;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFechahora() {
		return fechahora;
	}

	public void setFechahora(LocalDate fechahora) {
		this.fechahora = fechahora;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
	

}
