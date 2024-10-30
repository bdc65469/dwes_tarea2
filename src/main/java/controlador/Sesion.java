package controlador;

public class Sesion {
	
	private String usuario;
    private Perfil perfil;
    
    public Sesion() {}

    // Enumerado Perfil
    public enum Perfil {
        INVITADO, REGISTRADO, ADMIN
    }

	public Sesion(String usuario, Perfil perfil) {
		super();
		this.usuario = usuario;
		this.perfil = perfil;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
    
	public void cerrarSesion() {
		this.usuario = "";
		this.perfil = Perfil.INVITADO;
	}
    

}
