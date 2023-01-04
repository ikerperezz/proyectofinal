package clases;

public class Jugador {

	private int idJugador;
	private String nombreJugador;
	private int valor;
	private String posicion;
	private String equipo;
	private int puntos;
	private boolean titular;

	
	public boolean isTitular() {
		return titular;
	}
	public void setTitular(boolean titular) {
		this.titular = titular;
	}
	public int getIdJugador() {
		return idJugador;
	}
	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}
	public String getNombreJugador() {
		return nombreJugador;
	}
	public void setNombreJugador(String nombreJugador) {
		this.nombreJugador = nombreJugador;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public String getEquipo() {
		return equipo;
	}
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	

	public Jugador(int idJugador, String nombreJugador, int valor, String posicion, String equipo, int puntos, boolean titular) {
		super();
		this.idJugador = idJugador;
		this.nombreJugador = nombreJugador;
		this.valor = valor;
		this.posicion = posicion;
		this.equipo = equipo;
		this.puntos = puntos;
		this.titular = titular;
		
	}
	@Override
	public String toString() {
		return posicion + " - " + nombreJugador + " - " + valor +"€";
	}
	
	
}
