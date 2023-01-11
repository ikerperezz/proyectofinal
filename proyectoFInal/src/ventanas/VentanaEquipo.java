package ventanas;

import java.util.List;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDatos.DBManager;
import clases.Jugador;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class VentanaEquipo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<String> model;
	private DefaultListModel<String> modelsup;
	static Jugador jugador = new Jugador(0, "", 0, "", "", 0, false);
	
	

	/**
	 * Create the frame.
	 */
	public VentanaEquipo() {
		setTitle("Plantilla");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Plantilla de "+InterfazDeUsuarioPublico.usP.getUsuario());
		lblNewLabel.setBounds(196, 27, 206, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("Plantilla titular");
		lblNewLabel_2.setBounds(59, 67, 82, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Suplentes");
		lblNewLabel_3.setBounds(388, 67, 49, 14);
		contentPane.add(lblNewLabel_3);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazDeUsuarioPublico v = new InterfazDeUsuarioPublico(null, null, null);
				v.setVisible(true);
				VentanaEquipo.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 317, 89, 23);
		contentPane.add(btnNewButton);
		cargarJListTit();
		cargarJListSup();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 96, 162, 176);
		contentPane.add(scrollPane);
		JList<String> list_2 = new JList<String>(model);
		scrollPane.setViewportView(list_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(329, 96, 162, 188);
		contentPane.add(scrollPane_1);
		JList<String> list_1 = new JList<String>(modelsup);
		scrollPane_1.setViewportView(list_1);
				
				
				
		JButton btnNewButton_1 = new JButton("Cambiar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBManager dbmanager= new DBManager();
				dbmanager.conectar();
				dbmanager.crearListaPlantilla(InterfazDeUsuarioPublico.usP);
				
				String pos= list_2.getSelectedValue();
				
				String pos1 = list_1.getSelectedValue();
			
				DBManager db = new DBManager();
				db.conectar();
				List<Jugador> jug1 = db.crearListaPlantilla(InterfazDeUsuarioPublico.usP);
			for (int i = 0; i < jug1.size(); i++) {
				if(!pos.substring(0,3).equals(pos1.substring(0,3))) {
					JOptionPane.showMessageDialog(VentanaEquipo.this,
							"No puedes cambiar jugador de diferentes posiciones.");
					break;
				}
				if(pos.contains(jug1.get(i).getNombreJugador())) {
						jug1.get(i).setTitular(false);
						db.updateJugadorEnLiga(InterfazDeUsuarioPublico.usP, jug1.get(i));
					
				}
				if(pos1.contains(jug1.get(i).getNombreJugador())) {
					jug1.get(i).setTitular(true);
					db.updateJugadorEnLiga(InterfazDeUsuarioPublico.usP, jug1.get(i));
			}
			}
			cargarJListSup();
			cargarJListTit();
			list_2.setModel(model);
			list_1.setModel(modelsup);
			Logger logger = Logger.getLogger( "Cambio de jugadores");
			logger.info("Cambio realizado");
			
			db.disconnect();
			
			}
		});
		btnNewButton_1.setBounds(196, 133, 123, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnDat = new JButton("Datos");
		btnDat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String juga= list_2.getSelectedValue();
				String juga_1= list_1.getSelectedValue();
				DBManager dbmanager= new DBManager();
							dbmanager.conectar();
							List<Jugador> jug = dbmanager.crearListaPlantilla(InterfazDeUsuarioPublico.usP);
					
					if((list_1.getSelectedIndex()==-1)&&(list_2.getSelectedIndex()==-1)) {
						JOptionPane.showMessageDialog(VentanaEquipo.this,
								"No hay ningún jugador seleccionado");
					}else if(!(list_1.getSelectedIndex()==-1)&&!(list_2.getSelectedIndex()==-1)) {
						JOptionPane.showMessageDialog(VentanaEquipo.this,
								"Más de un jugador seleccionado");
						list_2.clearSelection();
						list_1.clearSelection();
					}else if(!(list_1.getSelectedIndex()==-1)){
							for (int i = 0; i < jug.size(); i++) {
								if(juga_1.contains(jug.get(i).getNombreJugador())) {
									jugador.setEquipo(jug.get(i).getEquipo());
									jugador.setIdJugador(jug.get(i).getIdJugador());
									jugador.setNombreJugador(jug.get(i).getNombreJugador());
									jugador.setPosicion(jug.get(i).getPosicion());
									jugador.setPuntos(jug.get(i).getPuntos());
									jugador.setTitular(jug.get(i).isTitular());
									jugador.setValor(jug.get(i).getValor());
									VentanaJugador v = new VentanaJugador();
							v.setVisible(true);
									break;
								}
								}
					}else if(!(list_2.getSelectedIndex()==-1)){
							for (int i = 0; i < jug.size(); i++) {
								if(juga.contains(jug.get(i).getNombreJugador())) {
									jugador.setEquipo(jug.get(i).getEquipo());
									jugador.setIdJugador(jug.get(i).getIdJugador());
									jugador.setNombreJugador(jug.get(i).getNombreJugador());
									jugador.setPosicion(jug.get(i).getPosicion());
									jugador.setPuntos(jug.get(i).getPuntos());
									jugador.setTitular(jug.get(i).isTitular());
									jugador.setValor(jug.get(i).getValor());
									VentanaJugador v = new VentanaJugador();
						v.setVisible(true);
									break;
								}
								
							}
					}
						
				
							
						
							
						
	dbmanager.disconnect();
			}
		});
		btnDat.setBounds(196, 167, 123, 23);
		contentPane.add(btnDat);
		
		JButton btnAñadirAMercado = new JButton("Añadir a mercado");
		btnAñadirAMercado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBManager dbmanager= new DBManager();
				dbmanager.conectar();
				
				
				if((list_1.getSelectedIndex()==-1)&&(list_2.getSelectedIndex()==-1)) {
					JOptionPane.showMessageDialog(VentanaEquipo.this,
							"No hay ningún jugador seleccionado");
				}else if(!(list_1.getSelectedIndex()==-1)&&!(list_2.getSelectedIndex()==-1)) {
					JOptionPane.showMessageDialog(VentanaEquipo.this,
							"Más de un jugador seleccionado");
					list_2.clearSelection();
					list_1.clearSelection();
				}else if(!(list_2.getSelectedIndex()==-1)){
					JOptionPane.showMessageDialog(VentanaEquipo.this,
							"No puedes añadir un jugador titular al mercado");
					
				}else if(!(list_1.getSelectedIndex()==-1)){
					int id = dbmanager.conseguirIdJugador(list_1.getSelectedValue());
					int valor = dbmanager.conseguirValor(id);
					dbmanager.eliminarJugadoresDePlantilla(id);
					dbmanager.meterJugadoresMercado(id, valor, InterfazDeUsuarioPublico.usP);
					JOptionPane.showMessageDialog(VentanaEquipo.this,
							"Jugador añadido al mercado");
				}
				cargarJListSup();
			cargarJListTit();
			list_2.setModel(model);
			list_1.setModel(modelsup);
			}
			
		});
		btnAñadirAMercado.setBounds(196, 201, 123, 23);
		contentPane.add(btnAñadirAMercado);
		
	
		

	}

	



	public void cargarJListTit() {
		// TODO Auto-generated method stub
		DBManager dbmanager= new DBManager();
		dbmanager.conectar();
		List<Jugador> jug = dbmanager.crearListaPlantilla(InterfazDeUsuarioPublico.usP);
	
		model = new DefaultListModel<String>();
		for (int i = 0; i < jug.size(); i++) {
			if(jug.get(i).isTitular()) {
			model.addElement(jug.get(i).toString());
			}
		}
		dbmanager.disconnect();

	}
	
	public void cargarJListSup() {
		// TODO Auto-generated method stub
		DBManager dbmanager= new DBManager();
		dbmanager.conectar();
		List<Jugador> jug = dbmanager.crearListaPlantilla(InterfazDeUsuarioPublico.usP);

		modelsup = new DefaultListModel<String>();
		for (int i = 0; i < jug.size(); i++) {
			if(!jug.get(i).isTitular()) {
			modelsup.addElement(jug.get(i).toString());
			}
		}

	}
}
