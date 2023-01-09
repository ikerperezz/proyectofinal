package clases;

public class Oferta {

		private String nombreUsuario;
		private String nombreUsuario2;
		
		private int idJugador;
		private int oferta;
		private int idLiga;
		
		
		
		
		



		public Oferta(String nombreUsuario, String nombreUsuario2, int idJugador, int oferta, int idLiga) {
			super();
			this.nombreUsuario = nombreUsuario;
			this.nombreUsuario2 = nombreUsuario2;
			this.idJugador = idJugador;
			this.oferta = oferta;
			this.idLiga = idLiga;
		}




		public String getNombreUsuario() {
			return nombreUsuario;
		}




		public void setNombreUsuario(String nombreUsuario) {
			this.nombreUsuario = nombreUsuario;
		}




		public String getNombreUsuario2() {
			return nombreUsuario2;
		}




		public void setNombreUsuario2(String nombreUsuario2) {
			this.nombreUsuario2 = nombreUsuario2;
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
}
