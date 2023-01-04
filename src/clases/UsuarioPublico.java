package clases;

public class UsuarioPublico extends Usuario {
	private int idUsuarioPublico;
	private int idLiga;
	private int dineroDisponible;
	private int puntos;

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getIdLiga() {
		return idLiga;
	}

	public void setIdLiga(int idLiga) {
		this.idLiga = idLiga;
	}

	public int getDineroDisponible() {
		return dineroDisponible;
	}

	public void setDineroDisponible(int dineroDisponible) {
		this.dineroDisponible = dineroDisponible;
	}

	public UsuarioPublico(String usuario, String contraseina, int idUsuarioPublico, int idLiga, int dineroDisponible,
			int puntos) {
		super(usuario, contraseina);
		this.idUsuarioPublico = idUsuarioPublico;
		this.idLiga = idLiga;
		this.dineroDisponible = dineroDisponible;
		this.puntos = puntos;
	}

	public int getIdUsuarioPublico() {
		return idUsuarioPublico;
	}

	public void setIdUsuarioPublico(int idUsuarioPublico) {
		this.idUsuarioPublico = idUsuarioPublico;
	}

	@Override
	public String toString() {
		return usuario + "-" + puntos;
	}


	
	@Override   //criterio de igualdad
	public boolean equals(Object o) {
		
		if (!(o instanceof Usuario)) {
			return false; }
		
		
		UsuarioPublico p=(UsuarioPublico) o;
		
		boolean condicion=(this.idUsuarioPublico==(p.idUsuarioPublico)) && 
		(this.idLiga==(p.idLiga)) && (this.dineroDisponible==(p.dineroDisponible))&&
		(this.puntos==(p.puntos) && (this.usuario.equals(p.usuario)) 
		&& (this.getContraseina().equals(p.getContraseina())));
		
		return condicion;
		
	}
}
