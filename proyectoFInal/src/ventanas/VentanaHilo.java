package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import baseDatos.DBManager;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;

public class VentanaHilo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaHilo(VentanaMercado ventanamercado) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 214);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Ver jugadores mas valorados");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread hilo = new Thread() {
					@Override
					public void run() {
						VentanaHilo2 v = new VentanaHilo2();
						v.setVisible(true);
						DBManager dbmanager = new DBManager();
						dbmanager.conectar();
						Connection conexion = dbmanager.getConn();
						try (PreparedStatement stmt = conexion.prepareStatement(
								"SELECT nombreJugador, valor FROM jugadores ORDER BY valor DESC LIMIT 5 ")) {
							ResultSet resultSet = stmt.executeQuery();
							DefaultListModel<String> model = new DefaultListModel<>();
							while (resultSet.next()) {
								model.addElement(resultSet.getString("nombreJugador"));
								model.addElement(resultSet.getString("valor"));
							}
							JList<String> listaJugadores = new JList<>(model);

						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				};
				hilo.start();
				Logger logger = Logger.getLogger("Final de evento");
				logger.info("Evento finalizado");
			}
		});
		getContentPane().add(btnNewButton, BorderLayout.WEST);

		JButton btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMercado v = new VentanaMercado();
				v.setVisible(true);
				VentanaHilo.this.setVisible(false);
			}
		});

	}
}
