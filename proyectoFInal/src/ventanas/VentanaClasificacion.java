package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import baseDatos.DBManager;
import clases.UsuarioPublico;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class VentanaClasificacion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<String> model;
	static UsuarioPublico usP = new UsuarioPublico("", "", 0, 0, 0, 0);
	/**
	 * Create the frame.
	 */
	public VentanaClasificacion() {
		setTitle("Clasificación");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		cargarJlist();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(153, 103, 248, 254);
		contentPane.add(scrollPane);
		
		JList<String> list = new JList<String>(model);
		scrollPane.setViewportView(list);
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazDeUsuarioPublico v = new InterfazDeUsuarioPublico(null, null, null);
				v.setVisible(true);
				VentanaClasificacion.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 346, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblnombre = new JLabel("Clasificación de la liga" + InterfazDeUsuarioPublico.usP.getIdLiga());
		lblnombre.setBounds(180, 53, 211, 39);
		contentPane.add(lblnombre);
		
		JButton btnPlantilla = new JButton("Plantilla");
		btnPlantilla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBManager dbmanager = new DBManager();
				dbmanager.conectar();
				
				
				String text=list.getSelectedValue();
				List<UsuarioPublico> us = dbmanager.crearListaDeMismaLiga(InterfazDeUsuarioPublico.usP);
				if(text.contains(InterfazDeUsuarioPublico.usP.getUsuario())) {
					VentanaEquipo v = new VentanaEquipo();
					v.setVisible(true);
					VentanaClasificacion.this.setVisible(false);
				}else {
					for (int i = 0; i < us.size(); i++) {
					if(text.contains(us.get(i).getUsuario())) {
						usP.setContraseina(us.get(i).getContraseina());
						usP.setDineroDisponible(us.get(i).getDineroDisponible());
						usP.setIdLiga(us.get(i).getIdLiga());
						usP.setIdUsuarioPublico(us.get(i).getIdUsuarioPublico());
						usP.setUsuario(us.get(i).getUsuario());
						usP.setPuntos(us.get(i).getPuntos());
						VentanaPlantillaRival v = new VentanaPlantillaRival();
						v.setVisible(true);
					}	
					}
					
					
					
				}
				
				
			}
		});
		btnPlantilla.setBounds(462, 226, 89, 23);
		contentPane.add(btnPlantilla);
		
	


	}

	public void cargarJlist() {
		// TODO Auto-generated method stub
		DBManager dbmanager = new DBManager();
		dbmanager.conectar();
		List<UsuarioPublico> us = dbmanager.crearListaDeMismaLiga(InterfazDeUsuarioPublico.usP);

		Collections.sort(us, new ComparadorPuntos());
		model = new DefaultListModel<String>();
		for (UsuarioPublico usuario : us) {
			model.addElement(usuario.toString());
		}

	}

	static class ComparadorPuntos implements Comparator<UsuarioPublico> {

		@Override
		public int compare(UsuarioPublico puntos1, UsuarioPublico puntos2) {
			return puntos2.getPuntos() - puntos1.getPuntos();
		}
	}
}
