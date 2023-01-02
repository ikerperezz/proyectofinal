package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaOpcionesLiga extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;



	/**
	 * Create the frame.
	 * @param crearCuenta 
	 */
	public VentanaOpcionesLiga(CrearCuenta crearCuenta) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 214);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCrearLiga = new JButton("Crear liga");
		btnCrearLiga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCrearLiga v = new VentanaCrearLiga();
				v.setVisible(true);
				VentanaOpcionesLiga.this.setVisible(false);
			}
		});
		btnCrearLiga.setBounds(51, 98, 144, 23);
		contentPane.add(btnCrearLiga);
		
		JButton btnUnirse = new JButton("Unirse a liga");
		btnUnirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaUnirseLiga v = new VentanaUnirseLiga();
				v.setVisible(true);
				VentanaOpcionesLiga.this.setVisible(false);
			}
		});
		btnUnirse.setBounds(266, 98, 144, 23);
		contentPane.add(btnUnirse);
	}
}
