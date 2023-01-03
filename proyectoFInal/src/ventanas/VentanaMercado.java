package ventanas;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import baseDatos.DBManager;
import clases.BaseDatos;
import clases.Jugador;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import baseDatos.DBManager;
import clases.Jugador;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;

public class VentanaMercado extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<String> model;

	/**
	 * Create the frame.
	 */
	public VentanaMercado() {
		setTitle("Mercado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mercado liga " + InterfazDeUsuarioPublico.usP.getIdLiga());
		lblNewLabel.setBounds(272, 11, 49, 14);
		contentPane.add(lblNewLabel);

		cargarJlist();

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazDeUsuarioPublico v = new InterfazDeUsuarioPublico(null, null, null);
				v.setVisible(true);
				VentanaMercado.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 429, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(522, 65, 89, 23);
		contentPane.add(btnNewButton_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(137, 47, 329, 333);
		contentPane.add(scrollPane);
		JList list = new JList(model);
		scrollPane.setViewportView(list);
	}

	public void cargarJlist() {
		// TODO Auto-generated method stub
		DBManager dbmanager = new DBManager();
		dbmanager.conectar();
		List<Jugador> jug = dbmanager.crearListaMercado(InterfazDeUsuarioPublico.usP.getIdLiga());
		model = new DefaultListModel<String>();
		for (Jugador jugador : jug) {
			model.addElement(jugador.toString());
		class MyListCellRenderer extends DefaultListCellRenderer {
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
				switch (index) {
				case 0:
					Jugador jugador0 = new Jugador(index, getText(), index, getText(), getText(), index, cellHasFocus);
					String equipoJugador0 = jugador0.getEquipo();
					if (equipoJugador0.contains("Barcelona")) {
						equipo.setIcon(Barcelona);
					}else {
						if (equipoJugador0.contains("Real Madrid")) {
							equipo.setIcon(RealMadrid);
					}else {
						if (equipoJugador0.contains("Real Sociedad")) {
							equipo.setIcon(Erreala);
					}else {
						if (equipoJugador0.contains("Atletico de Madrid")) {
							equipo.setIcon(Atletico);
					}else {
						if (equipoJugador0.contains("Athletic")) {
							equipo.setIcon(Pathetic);
					}else {
						if (equipoJugador0.contains("Betis")) {
							equipo.setIcon(Betis);	
					}else {
						if (equipoJugador0.contains("Villarreal")) {
							equipo.setIcon(VillaReal);
					}else {
						if (equipoJugador0.contains("Rayo Vallecano")) {
							equipo.setIcon(Rayo);
					}else {
						if (equipoJugador0.contains("Osasuna")) {
							equipo.setIcon(OsasunaNuncaSeRinde);
					}else {
						if (equipoJugador0.contains("Valencia")) {
							equipo.setIcon(Valencia);
					}else {
						if (equipoJugador0.contains("Mallorca")) {
							equipo.setIcon(Mallorca);
						}else {
							if (equipoJugador0.contains("Girona")) {
								equipo.setIcon(Girona);
						}else {
							if (equipoJugador0.contains("Almeria")) {
								equipo.setIcon(Almeria);
						}else {
							if (equipoJugador0.contains("Getafe")) {
								equipo.setIcon(Getafe);
						}else {
							if (equipoJugador0.contains("Valladolid")) {
								equipo.setIcon(Valladolid);
						}else {
							if (equipoJugador0.contains("Espanyol")) {
								equipo.setIcon(Espanyol);	
						}else {
							if (equipoJugador0.contains("Celta")) {
								equipo.setIcon(Celta);
						}else {
							if (equipoJugador0.contains("Sevilla")) {
								equipo.setIcon(Sevilla);
						}else {
							if (equipoJugador0.contains("Cadiz")) {
								equipo.setIcon(Cadiz);
								
					}}}}}}}}}}}}}}}}}}
						equipo.setIcon(Elche);
						}
					
					break;
				case 1:
					Jugador jugador1 = new Jugador(index, getText(), index, getText(), getText(), index, cellHasFocus);
					String equipoJugador1 = jugador1.getEquipo();
					if (equipoJugador1.contains("Barcelona")) {
						equipo.setIcon(Barcelona);
					}else {
						if (equipoJugador1.contains("Real Madrid")) {
							equipo.setIcon(RealMadrid);
					}else {
						if (equipoJugador1.contains("Real Sociedad")) {
							equipo.setIcon(Erreala);
					}else {
						if (equipoJugador1.contains("Atletico de Madrid")) {
							equipo.setIcon(Atletico);
					}else {
						if (equipoJugador1.contains("Athletic")) {
							equipo.setIcon(Pathetic);
					}else {
						if (equipoJugador1.contains("Betis")) {
							equipo.setIcon(Betis);	
					}else {
						if (equipoJugador1.contains("Villarreal")) {
							equipo.setIcon(VillaReal);
					}else {
						if (equipoJugador1.contains("Rayo Vallecano")) {
							equipo.setIcon(Rayo);
					}else {
						if (equipoJugador1.contains("Osasuna")) {
							equipo.setIcon(OsasunaNuncaSeRinde);
					}else {
						if (equipoJugador1.contains("Valencia")) {
							equipo.setIcon(Valencia);
					}else {
						if (equipoJugador1.contains("Mallorca")) {
							equipo.setIcon(Mallorca);
						}else {
							if (equipoJugador1.contains("Girona")) {
								equipo.setIcon(Girona);
						}else {
							if (equipoJugador1.contains("Almeria")) {
								equipo.setIcon(Almeria);
						}else {
							if (equipoJugador1.contains("Getafe")) {
								equipo.setIcon(Getafe);
						}else {
							if (equipoJugador1.contains("Valladolid")) {
								equipo.setIcon(Valladolid);
						}else {
							if (equipoJugador1.contains("Espanyol")) {
								equipo.setIcon(Espanyol);	
						}else {
							if (equipoJugador1.contains("Celta")) {
								equipo.setIcon(Celta);
						}else {
							if (equipoJugador1.contains("Sevilla")) {
								equipo.setIcon(Sevilla);
						}else {
							if (equipoJugador1.contains("Cadiz")) {
								equipo.setIcon(Cadiz);
								
					}}}}}}}}}}}}}}}}}}
						equipo.setIcon(Elche);
						}
					break;
				case 2:
					Jugador jugador2 = new Jugador(index, getText(), index, getText(), getText(), index, cellHasFocus);
					String equipoJugador2 = jugador2.getEquipo();
					if (equipoJugador2.contains("Barcelona")) {
						equipo.setIcon(Barcelona);
					}else {
						if (equipoJugador2.contains("Real Madrid")) {
							equipo.setIcon(RealMadrid);
					}else {
						if (equipoJugador2.contains("Real Sociedad")) {
							equipo.setIcon(Erreala);
					}else {
						if (equipoJugador2.contains("Atletico de Madrid")) {
							equipo.setIcon(Atletico);
					}else {
						if (equipoJugador2.contains("Athletic")) {
							equipo.setIcon(Pathetic);
					}else {
						if (equipoJugador2.contains("Betis")) {
							equipo.setIcon(Betis);	
					}else {
						if (equipoJugador2.contains("Villarreal")) {
							equipo.setIcon(VillaReal);
					}else {
						if (equipoJugador2.contains("Rayo Vallecano")) {
							equipo.setIcon(Rayo);
					}else {
						if (equipoJugador2.contains("Osasuna")) {
							equipo.setIcon(OsasunaNuncaSeRinde);
					}else {
						if (equipoJugador2.contains("Valencia")) {
							equipo.setIcon(Valencia);
					}else {
						if (equipoJugador2.contains("Mallorca")) {
							equipo.setIcon(Mallorca);
						}else {
							if (equipoJugador2.contains("Girona")) {
								equipo.setIcon(Girona);
						}else {
							if (equipoJugador2.contains("Almeria")) {
								equipo.setIcon(Almeria);
						}else {
							if (equipoJugador2.contains("Getafe")) {
								equipo.setIcon(Getafe);
						}else {
							if (equipoJugador2.contains("Valladolid")) {
								equipo.setIcon(Valladolid);
						}else {
							if (equipoJugador2.contains("Espanyol")) {
								equipo.setIcon(Espanyol);	
						}else {
							if (equipoJugador2.contains("Celta")) {
								equipo.setIcon(Celta);
						}else {
							if (equipoJugador2.contains("Sevilla")) {
								equipo.setIcon(Sevilla);
						}else {
							if (equipoJugador2.contains("Cadiz")) {
								equipo.setIcon(Cadiz);
								
					}}}}}}}}}}}}}}}}}}
						equipo.setIcon(Elche);
						}
				}
				return equipo;
			}
		}
	}

		class RendererBusquedaJugadores extends JButton implements ListCellRenderer<clases.BaseDatos> {

			private static final long serialVersionUID = 1L;
			private ImageIcon selectedIcon;

			public RendererBusquedaJugadores() {
				selectedIcon = new ImageIcon(
						getClass().getResource("https://cdn-icons-png.flaticon.com/512/2015/2015046.png"));
				setOpaque(true);
				setIcon(selectedIcon);
			}

			@Override
			public Component getListCellRendererComponent(JList<? extends BaseDatos> list, BaseDatos value, int index,
					boolean isSelected, boolean cellHasFocus) {
				return this;
			}

		}
	}
}
