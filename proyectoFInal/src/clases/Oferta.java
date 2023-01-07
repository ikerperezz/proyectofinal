package clases;

public class Oferta {

		private String nombreUsuario;
		
		private int idJugador;
		private int oferta;
		private int idLiga;
		
		
		
		
		public Oferta(String nombreUsuario, int idJugador, int oferta, int idLiga) {
			super();
			this.nombreUsuario = nombreUsuario;
			this.idJugador = idJugador;
			this.oferta = oferta;
			this.idLiga = idLiga;
		}
		public int getIdJugador() {
			return idJugador;
		}
		public void setIdJugador(int idJugador) {
			this.idJugador = idJugador;
		}
		public int getOferta() {
			return oferta;
		}
		public void setOferta(int oferta) {
			this.oferta = oferta;
		}
		public int getIdLiga() {
			return idLiga;
		}
		public void setIdLiga(int idLiga) {
			this.idLiga = idLiga;
		}
		
		
		public String getNombreUsuario() {
			return nombreUsuario;
		}
		public void setNombreUsuario(String nombreUsuario) {
			this.nombreUsuario = nombreUsuario;
		}
}
