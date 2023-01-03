package ventanas;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListModel;

import baseDatos.DBManager;
import clases.Jugador;
import ventanas.VentanaMercado;

public class MyListcellRenderer extends DefaultListCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		JLabel equipo = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);
		DBManager dbmanager = new DBManager();
		ImageIcon Barcelona = new ImageIcon(getClass().getResource("https://www.ecured.cu/images/thumb/9/9e/Fcbarcelona.png/199px-Fcbarcelona.png"));
		ImageIcon RealMadrid = new ImageIcon(getClass().getResource("http://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/1.png"));
		ImageIcon Erreala = new ImageIcon(getClass().getResource("http://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/16.png"));
		ImageIcon Atletico = new ImageIcon(getClass().getResource("https://www.sopitas.com/wp-content/uploads/2020/06/escudo-atletico-madrid-significado-historia.png"));
		ImageIcon Pathetic = new ImageIcon(getClass().getResource("http://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/5.png"));
		ImageIcon Betis = new ImageIcon(getClass().getResource("https://www.estadiodeportivo.com/elementosWeb/gestionCajas/EDE/Image/escudo-Real-Betis-2012.jpg"));
		ImageIcon VillaReal = new ImageIcon(getClass().getResource("http://as01.epimg.net/img/comunes/fotos/fichas/equipos/large/19.png"));
		ImageIcon Rayo = new ImageIcon(getClass().getResource("https://img.europapress.es/fotoweb/fotonoticia_20211221235826_1200.jpg"));
		ImageIcon OsasunaNuncaSeRinde = new ImageIcon(getClass().getResource("https://statics.proyectoclubes.com/images/osasuna/opengraph_image.png?2"));
		ImageIcon Valencia = new ImageIcon(getClass().getResource("https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/blt6398570d8602683e/615f81e318bb02322b3a1f30/1c834fa5be327b369511710ca95d66407bbb6b54.jpg"));
		ImageIcon Mallorca = new ImageIcon(getClass().getResource("http://assets.stickpng.com/thumbs/584ad3aeb519ea740933a8dc.png"));
		ImageIcon Girona = new ImageIcon(getClass().getResource("http://assets.stickpng.com/thumbs/584ad4b8b519ea740933a8fd.png"));
		ImageIcon Almeria = new ImageIcon(getClass().getResource("https://assets.stickpng.com/images/584ad245b519ea740933a8b3.png"));
		ImageIcon Getafe = new ImageIcon(getClass().getResource("http://assets.stickpng.com/thumbs/584ad4b1b519ea740933a8fc.png"));
		ImageIcon Valladolid = new ImageIcon(getClass().getResource("https://www.ecured.cu/images/thumb/9/9e/Fcbarcelona.png/199px-Fcbarcelona.png"));
		ImageIcon Espanyol = new ImageIcon(getClass().getResource("https://e7.pngegg.com/pngimages/549/858/png-clipart-rcd-espanyol-la-liga-rcde-stadium-football-atletico-madrid-espanyol-barcelona-escudo-i-text-logo.png"));
		ImageIcon Celta = new ImageIcon(getClass().getResource("http://assets.stickpng.com/thumbs/584ad3c4b519ea740933a8df.png"));
		ImageIcon Sevilla = new ImageIcon(getClass().getResource("https://i0.wp.com/www.lacolinadenervion.com/wp-content/uploads/2017/10/SFC.jpg?fit=800%2C455&ssl=1"));
		ImageIcon Cadiz = new ImageIcon(getClass().getResource("http://assets.stickpng.com/thumbs/584ad7abb519ea740933a953.png"));
		ImageIcon Elche = new ImageIcon(getClass().getResource("https://upload.wikimedia.org/wikipedia/commons/c/cb/Escudo_Elche_CF.png"));
		Jugador jugador0 = new Jugador(index, getText(), index, getText(), getText(), index, cellHasFocus);
		String equipoJugador0 = jugador0.getEquipo();
		VentanaMercado mercado = new VentanaMercado();
		JList listaMercado = mercado.cargarJlist();
		ListModel model = list.getModel();
		for (int i = 0; i < model.getSize(); i++) {
			if (equipoJugador0.equals("Barcelona")) {
				equipo.setIcon(Barcelona);
			}else {
				if (equipoJugador0.equals("Real Mandril")) {
					equipo.setIcon(RealMadrid);
			}else {
				if (equipoJugador0.equals("Real Suciedad")) {
					equipo.setIcon(Erreala);
			}else {
				if (equipoJugador0.equals("Atletico de Mandril")) {
					equipo.setIcon(Atletico);
			}else {
				if (equipoJugador0.equals("Aletic de Bilbado")) {
					equipo.setIcon(Pathetic);
			}else {
				if (equipoJugador0.equals("Beti")) {
					equipo.setIcon(Betis);	
			}else {
				if (equipoJugador0.equals("Villareal")) {
					equipo.setIcon(VillaReal);
			}else {
				if (equipoJugador0.equals("Rayo Vallecano")) {
					equipo.setIcon(Rayo);
			}else {
				if (equipoJugador0.equals("Osasuna")) {
					equipo.setIcon(OsasunaNuncaSeRinde);
			}else {
				if (equipoJugador0.equals("Valencia")) {
					equipo.setIcon(Valencia);
			}else {
				if (equipoJugador0.equals("Mallorca")) {
					equipo.setIcon(Mallorca);
				}else {
					if (equipoJugador0.equals("Girona")) {
						equipo.setIcon(Girona);
				}else {
					if (equipoJugador0.equals("Almeria")) {
						equipo.setIcon(Almeria);
				}else {
					if (equipoJugador0.equals("Getafe")) {
						equipo.setIcon(Getafe);
				}else {
					if (equipoJugador0.equals("Valladolid")) {
						equipo.setIcon(Valladolid);
				}else {
					if (equipoJugador0.equals("Español")) {
						equipo.setIcon(Espanyol);	
				}else {
					if (equipoJugador0.equals("Celta")) {
						equipo.setIcon(Celta);
				}else {
					if (equipoJugador0.equals("Sevilla")) {
						equipo.setIcon(Sevilla);
				}else {
					if (equipoJugador0.equals("Cadiz")) {
						equipo.setIcon(Cadiz);
						
			}}}}}}}}}}}}}}}}}}
				equipo.setIcon(Elche);
				}
			
			break;
		}
		return listaMercado;
	}
}

