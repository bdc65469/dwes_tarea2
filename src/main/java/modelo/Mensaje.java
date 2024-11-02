package modelo;

import java.time.LocalDateTime;

public class Mensaje {
	
	private Long id;
	private LocalDateTime fechahora;
	private String mensaje;
	private Long idEjemplar;
	private Long idPersona;
	
	public Mensaje() {}

	public Mensaje(Long id, LocalDateTime fechahora, String mensaje, Long idEjemplar, Long idPersona) {
		super();
		this.id = id;
		this.fechahora = fechahora;
		this.mensaje = mensaje;
		this.idEjemplar = idEjemplar;
		this.idPersona = idPersona;
	}

	public Mensaje(LocalDateTime fechahora, String mensaje, Long idEjemplar, Long idPersona) {
		super();
		this.fechahora = fechahora;
		this.mensaje = mensaje;
		this.idEjemplar = idEjemplar;
		this.idPersona = idPersona;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFechahora() {
		return fechahora;
	}

	public void setFechahora(LocalDateTime fechahora) {
		this.fechahora = fechahora;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Long getIdEjemplar() {
		return idEjemplar;
	}

	public void setIdEjemplar(Long idEjemplar) {
		this.idEjemplar = idEjemplar;
	}

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", fechahora=" + fechahora + ", mensaje=" + mensaje + ", idEjemplar=" + idEjemplar
				+ ", idPersona=" + idPersona + "]";
	}

	
	
	
	
	

}
