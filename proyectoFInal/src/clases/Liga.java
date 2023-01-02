package clases;

public class Liga {
	private int idLiga;
	private String nombreLiga;
	public int getIdLiga() {
		return idLiga;
	}
	public void setIdLiga(int newId) {
		this.idLiga = newId;
	}
	public String getNombreLiga() {
		return nombreLiga;
	}
	public void setNombreLiga(String nombreLiga) {
		this.nombreLiga = nombreLiga;
	}
	public Liga(int idLiga, String nombreLiga) {
		super();
		this.idLiga = idLiga;
		this.nombreLiga = nombreLiga;
	}
	
}
