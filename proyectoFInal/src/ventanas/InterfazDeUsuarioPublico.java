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
		setBounds(100, 100, 491, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelBienvenida = new JLabel("Bienvenido " + usP.getUsuario());
		labelBienvenida.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelBienvenida.setBounds(69, 162, 326, 59);
		contentPane.add(labelBienvenida);

		JLabel labelNombreLiga = new JLabel("Liga con ID " + usP.getIdLiga());
		labelNombreLiga.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelNombreLiga.setBounds(69, 73, 330, 59);
		contentPane.add(labelNombreLiga);

		JButton botonInicio = new JButton("INICIO");
		botonInicio.setFont(new Font("Tahoma", Font.BOLD, 14));
		botonInicio.setBounds(0, 301, 89, 63);
		contentPane.add(botonInicio);

		JButton botonClasificacion = new JButton("CLASIFICACION");
		botonClasificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				VentanaClasificacion clasifi = new VentanaClasificacion();
				clasifi.setVisible(true);
				InterfazDeUsuarioPublico.this.setVisible(false);

			}
		});
		botonClasificacion.setBackground(new Color(255, 175, 175));
		botonClasificacion.setFont(new Font("Tahoma", Font.BOLD, 11));
		botonClasificacion.setBounds(99, 301, 125, 63);
		contentPane.add(botonClasificacion);

		JButton botonEquipo = new JButton("EQUIPO");
		botonEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				VentanaEquipo equipo = new VentanaEquipo();
				equipo.setVisible(true);
				InterfazDeUsuarioPublico.this.setVisible(false);
			}
		});
		botonEquipo.setBackground(Color.YELLOW);
		botonEquipo.setFont(new Font("Tahoma", Font.BOLD, 16));
		botonEquipo.setBounds(234, 301, 107, 63);
		contentPane.add(botonEquipo);

		JButton botonMercado = new JButton("MERCADO");
		botonMercado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				VentanaMercado mercado = new VentanaMercado();
				mercado.setVisible(true);
				InterfazDeUsuarioPublico.this.setVisible(false);

			}
		});
		botonMercado.setBackground(Color.GREEN);
		botonMercado.setFont(new Font("Tahoma", Font.BOLD, 15));
		botonMercado.setBounds(348, 301, 122, 63);
		contentPane.add(botonMercado);

		JButton botonAjustes = new JButton("AJUSTES");
		botonAjustes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				VentanaAjustes ajustes = new VentanaAjustes();
				ajustes.setVisible(true);
				InterfazDeUsuarioPublico.this.setVisible(false);

			}
		});
		botonAjustes.setBackground(Color.RED);
		botonAjustes.setFont(new Font("Tahoma", Font.BOLD, 15));
		botonAjustes.setBounds(370, 0, 107, 63);
		contentPane.add(botonAjustes);
	}
}
