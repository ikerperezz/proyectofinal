package ventanas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDatos.DBManager;
import clases.BaseDatos;
import clases.Jugador;
import clases.UsuarioPublico;

import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class VentanaEquipo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<String> model;
	private DefaultListModel<String> modelsup;
	private JList<BaseDatos> list;
	private ArrayList<BaseDatos> nombreJugador;
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
		lblNewLabel.setBounds(231, 26, 49, 14);
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
		JList<String> list = new JList<String>(model);

		list.setBounds(25, 96, 162, 194);
		contentPane.add(list);

	
		
		JList<String> list_1 = new JList<String>(modelsup);
	
		list_1.setBounds(329, 96, 162, 194);
		contentPane.add(list_1);
		
		JButton btnNewButton_1 = new JButton("Cambiar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBManager dbmanager= new DBManager();
				dbmanager.conectar();
				List<Jugador> jug = dbmanager.crearListaPlantilla(InterfazDeUsuarioPublico.usP);
				
				String pos= list.getSelectedValue();
				
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
			list.setModel(model);
			list_1.setModel(modelsup);
			
			
			db.disconnect();
			
			}
		});
		btnNewButton_1.setBounds(208, 157, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnDat = new JButton("Datos");
		btnDat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String juga= list.getSelectedValue();
				String juga_1= list_1.getSelectedValue();
				DBManager dbmanager= new DBManager();
							dbmanager.conectar();
							List<Jugador> jug = dbmanager.crearListaPlantilla(InterfazDeUsuarioPublico.usP);
					
					if((list_1.getSelectedIndex()==-1)&&(list.getSelectedIndex()==-1)) {
						JOptionPane.showMessageDialog(VentanaEquipo.this,
								"No hay ningún jugador seleccionado");
					}else if(!(list_1.getSelectedIndex()==-1)&&!(list.getSelectedIndex()==-1)) {
						JOptionPane.showMessageDialog(VentanaEquipo.this,
								"Más de un jugador seleccionado");
						list.clearSelection();
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
					}else if(!(list.getSelectedIndex()==-1)){
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
		btnDat.setBounds(208, 209, 89, 23);
		contentPane.add(btnDat);

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
