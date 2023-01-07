package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDatos.DBManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class VentanaAdministrador extends JFrame {

	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 */
	public VentanaAdministrador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 416, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Bienvenido Administrador");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("¿QUE QUIERES HACER?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblNewLabel_1);
		
		JButton btnCrearAdmin = new JButton("      AÑADIR NUEVO ADMINISTRADOR");
		btnCrearAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaAñadirAdmin v = new VentanaAñadirAdmin();
				v.setVisible(true);
				VentanaAdministrador.this.setVisible(false);
			}
		});
		btnCrearAdmin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(btnCrearAdmin);
		
		JButton btnRepartitPuntos = new JButton("        REPARTIR PUNTOS DE LA JORNADA");
		btnRepartitPuntos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(btnRepartitPuntos);
		
		JButton btnRepartirDinero = new JButton("         REPARTIR DINERO DE LA JORNADA");
		btnRepartirDinero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(btnRepartirDinero);
		
		JButton btnNewButton_3 = new JButton("        CAMBIAR VALOR DE LOS JUGADORES");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAñadirValor v = new VentanaAñadirValor();
			v.setVisible(true);
			VentanaAdministrador.this.setVisible(false);
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(btnNewButton_3);
		
		JButton btnCrearMercado = new JButton("CREAR MERCADO");
		btnCrearMercado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCrearMercado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBManager dbmanager = new DBManager();
				dbmanager.conectar();
				dbmanager.updateJugador();
				dbmanager.eliminarMercado();
				List<Integer> ligas = dbmanager.crearListaIdLigas();
				for (int i = 0; i < ligas.size(); i++) {
					
					dbmanager.crearMercado(ligas.get(i));
				}
				JOptionPane.showMessageDialog(VentanaAdministrador.this,
						"Mercados creados con exito");
				dbmanager.disconnect();
			}
			
			
		});
		contentPane.add(btnCrearMercado);
	}
}
