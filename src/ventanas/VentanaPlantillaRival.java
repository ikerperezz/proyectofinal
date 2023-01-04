package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import baseDatos.DBManager;
import clases.Jugador;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class VentanaPlantillaRival extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private DefaultListModel<String> model;

	/**
	 * Create the frame.
	 */
	public VentanaPlantillaRival() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		cargarJListTit();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(84, 23, 194, 217);
		contentPane.add(scrollPane);
		
		JList<String> list = new JList<String>(model);
		scrollPane.setViewportView(list);
		
		JButton btnDatos = new JButton("Datos");
		btnDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String juga= list.getSelectedValue();
				DBManager dbmanager= new DBManager();
				dbmanager.conectar();
				List<Jugador> jug = dbmanager.crearListaPlantilla(VentanaClasificacion.usP);
				if((list.getSelectedIndex()==-1)) {
					JOptionPane.showMessageDialog(VentanaPlantillaRival.this,
							"No hay ningún jugador seleccionado");
			}else if(!(list.getSelectedIndex()==-1)){
				for (int i = 0; i < jug.size(); i++) {
					if(juga.contains(jug.get(i).getNombreJugador())) {
						VentanaEquipo.jugador.setEquipo(jug.get(i).getEquipo());
						VentanaEquipo.jugador.setIdJugador(jug.get(i).getIdJugador());
						VentanaEquipo.jugador.setNombreJugador(jug.get(i).getNombreJugador());
						VentanaEquipo.jugador.setPosicion(jug.get(i).getPosicion());
						VentanaEquipo.jugador.setPuntos(jug.get(i).getPuntos());
						VentanaEquipo.jugador.setTitular(jug.get(i).isTitular());
						VentanaEquipo.jugador.setValor(jug.get(i).getValor());
						VentanaJugador v = new VentanaJugador();
			v.setVisible(true);
						break;
					}
					
				}
		}
				
			}
		});
		btnDatos.setBounds(323, 97, 89, 23);
		contentPane.add(btnDatos);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			VentanaPlantillaRival.this.setVisible(false);
			}
		});
		btnVolver.setBounds(323, 149, 89, 23);
		contentPane.add(btnVolver);
		

	}
	
	
	public void cargarJListTit() {
		// TODO Auto-generated method stub
		DBManager dbmanager= new DBManager();
		dbmanager.conectar();
		List<Jugador> jug = dbmanager.crearListaPlantilla(VentanaClasificacion.usP);
	
		model = new DefaultListModel<String>();
		for (int i = 0; i < jug.size(); i++) {
			if(jug.get(i).isTitular()) {
			model.addElement(jug.get(i).toString());
			}
		}

	}
}
