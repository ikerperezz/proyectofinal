package ventanas;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDatos.DBManager;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaHilo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaHilo(VentanaAdministrador ventanaAdministrador) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 363);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		DBManager manager = new DBManager();
		manager.conectar();
		JList<String> list = new JList<>(manager.implementacionHilo());
		list.setBounds(132, 83, 194, 186);
		contentPane.add(list);

		JLabel lblNewLabel = new JLabel("Jugadores más caros del mercado");
		lblNewLabel.setBounds(132, 34, 199, 36);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAdministrador v = new VentanaAdministrador();
				v.setVisible(true);
				VentanaHilo.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(362, 221, 89, 23);
		contentPane.add(btnNewButton);
		manager.disconnect();

	}
}