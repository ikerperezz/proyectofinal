package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaJugador extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public VentanaJugador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 277);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 5, 0, 0));
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		contentPane.add(lblNombre);
		
		JLabel lblPos = new JLabel("Posición");
		lblPos.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		contentPane.add(lblPos);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		contentPane.add(lblValor);
		
		JLabel lblEquipo = new JLabel("Equipo");
		lblEquipo.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		contentPane.add(lblEquipo);
		
		JLabel lblPuntos = new JLabel("Puntos");
		lblPuntos.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		contentPane.add(lblPuntos);
		
		JLabel lblNombre1 = new JLabel(VentanaEquipo.jugador.getNombreJugador());
		lblNombre1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		contentPane.add(lblNombre1);
		
		JLabel lblPos1 = new JLabel(VentanaEquipo.jugador.getPosicion());
		lblPos1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		contentPane.add(lblPos1);
		
		JLabel lblValor1 = new JLabel(""+VentanaEquipo.jugador.getValor());
		lblValor1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		contentPane.add(lblValor1);
		
		JLabel lblEquipo1 = new JLabel(VentanaEquipo.jugador.getEquipo());
		lblEquipo1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		contentPane.add(lblEquipo1);
		
		JLabel lblPuntos1 = new JLabel(""+VentanaEquipo.jugador.getPuntos());
		lblPuntos1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		contentPane.add(lblPuntos1);
		
		JLabel label = new JLabel("");
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("");
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("");
		contentPane.add(label_3);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JButton btnVolver = new JButton("Volver");
		panel.add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaJugador.this.setVisible(false);
			}
		});
	}
}
