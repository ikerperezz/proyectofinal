package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDatos.DBManager;
import clases.Usuario;
import clases.UsuarioPublico;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class InterfazDeUsuarioPublico extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static UsuarioPublico usP = new UsuarioPublico("", "", 0, 0, 0, 0);

	/**
	 * Create the frame.
	 */
	public InterfazDeUsuarioPublico(Usuario usuario, UsuarioPublico idLiga, VentanaAjustes ventanaAjustes) {
		setTitle("Bienvenido");
		DBManager dbmanager = new DBManager();
		dbmanager.conectar();
		List<UsuarioPublico> us = dbmanager.crearLista();
		if (InicioSesion.nombreUsuario != null) {
			for (int i = 0; i < us.size(); i++) {
				if (us.get(i).getUsuario().equals(InicioSesion.nombreUsuario)) {
					usP.setContraseina(us.get(i).getContraseina());
					usP.setDineroDisponible(us.get(i).getDineroDisponible());
					usP.setIdLiga(us.get(i).getIdLiga());
					usP.setIdUsuarioPublico(us.get(i).getIdUsuarioPublico());
					usP.setUsuario(us.get(i).getUsuario());
					usP.setPuntos(us.get(i).getPuntos());
				}
			}
		} else {
			for (int i = 0; i < us.size(); i++) {
				if (us.get(i).getUsuario().equals(CrearCuenta.nombreUsuario)) {
					usP.setContraseina(us.get(i).getContraseina());
					usP.setDineroDisponible(us.get(i).getDineroDisponible());
					usP.setIdLiga(us.get(i).getIdLiga());
					usP.setIdUsuarioPublico(us.get(i).getIdUsuarioPublico());
					usP.setUsuario(us.get(i).getUsuario());
					usP.setPuntos(us.get(i).getPuntos());
				}
			}
		}
		dbmanager.disconnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 954, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 5, 0, 0));

		JButton botonAjustes = new JButton("AJUSTES");
		botonAjustes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				VentanaAjustes ajustes = new VentanaAjustes();
				ajustes.setVisible(true);
				InterfazDeUsuarioPublico.this.setVisible(false);

			}
		});
		
		JLabel label = new JLabel("");
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("");
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("");
		contentPane.add(label_3);
		botonAjustes.setBackground(Color.RED);
		botonAjustes.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(botonAjustes);
		
				JButton botonMercado = new JButton("MERCADO");
				botonMercado.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						VentanaMercado mercado = new VentanaMercado();
						mercado.setVisible(true);
						InterfazDeUsuarioPublico.this.setVisible(false);

					}
				});
				
						JButton botonEquipo = new JButton("EQUIPO");
						botonEquipo.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								VentanaEquipo equipo = new VentanaEquipo();
								equipo.setVisible(true);
								InterfazDeUsuarioPublico.this.setVisible(false);
							}
						});
						
								JButton botonClasificacion = new JButton("CLASIFICACION DE LIGA");
								botonClasificacion.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {

										VentanaClasificacion clasifi = new VentanaClasificacion();
										clasifi.setVisible(true);
										InterfazDeUsuarioPublico.this.setVisible(false);

									}
								});
												
														JLabel labelNombreLiga = new JLabel("Liga con ID " + usP.getIdLiga());
														labelNombreLiga.setFont(new Font("Tahoma", Font.BOLD, 20));
														contentPane.add(labelNombreLiga);
												
												JLabel label_4 = new JLabel("");
												contentPane.add(label_4);
												
												JLabel label_5 = new JLabel("");
												contentPane.add(label_5);
												
												JLabel label_6 = new JLabel("");
												contentPane.add(label_6);
												
												JLabel label_7 = new JLabel("");
												contentPane.add(label_7);
										
												JLabel labelBienvenida = new JLabel("Bienvenido " + usP.getUsuario());
												labelBienvenida.setFont(new Font("Tahoma", Font.PLAIN, 16));
												contentPane.add(labelBienvenida);
										
										JLabel label_8 = new JLabel("");
										contentPane.add(label_8);
										
										JLabel label_9 = new JLabel("");
										contentPane.add(label_9);
										
										JLabel label_10 = new JLabel("");
										contentPane.add(label_10);
										
										JLabel label_11 = new JLabel("");
										contentPane.add(label_11);
								
										JButton botonInicio = new JButton("INICIO");
										botonInicio.setFont(new Font("Tahoma", Font.BOLD, 14));
										contentPane.add(botonInicio);
								botonClasificacion.setBackground(new Color(255, 175, 175));
								botonClasificacion.setFont(new Font("Tahoma", Font.BOLD, 11));
								contentPane.add(botonClasificacion);
						botonEquipo.setBackground(Color.YELLOW);
						botonEquipo.setFont(new Font("Tahoma", Font.BOLD, 16));
						contentPane.add(botonEquipo);
				botonMercado.setBackground(Color.GREEN);
				botonMercado.setFont(new Font("Tahoma", Font.BOLD, 15));
				contentPane.add(botonMercado);
		
		JButton btnNewButton = new JButton("CLASIFICACION");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaClasificacionGeneral v = new VentanaClasificacionGeneral();
				v.setVisible(true);
				InterfazDeUsuarioPublico.this.setVisible(false);
				
			}
		});
		btnNewButton.setBackground(new Color(255, 160, 122));
		contentPane.add(btnNewButton);
	}
}
