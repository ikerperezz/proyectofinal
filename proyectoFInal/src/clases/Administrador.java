package clases;

public class Administrador {
	
	String nombreUsuario;
	String contraseina;
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getContraseina() {
		return contraseina;
	}
	
	public void setContraseina(String contraseina) {
		this.contraseina = contraseina;
	}

	public Administrador(String nombreUsuario, String contraseina) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.contraseina = contraseina;
	}

	@Override
	public String toString() {
		return "Administrador [nombreUsuario=" + nombreUsuario + ", contraseina=" + contraseina + "]";
	}
	
}

