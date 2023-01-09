package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaRepartirPuntos extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRepartirPuntos frame = new VentanaRepartirPuntos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaRepartirPuntos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList list = new JList();
		list.setBounds(33, 98, 172, 237);
		contentPane.add(list);
		
		JLabel lblNewLabel = new JLabel("USUARIOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(33, 54, 141, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPuntos = new JLabel("PUNTOS");
		lblPuntos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPuntos.setBounds(352, 139, 74, 14);
		contentPane.add(lblPuntos);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(312, 138, 30, 20);
		contentPane.add(spinner);
		
		JSpinner haundi = new JSpinner();
		haundi.setBounds(272, 138, 30, 20);
		contentPane.add(haundi);
		
		JButton btnNewButton = new JButton("DAR PUNTOS");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		btnNewButton.setBounds(287, 194, 105, 36);
		contentPane.add(btnNewButton);
	}
}
