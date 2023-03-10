package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDatos.DBManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class VentanaAdministrador extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 */
	public VentanaAdministrador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 401, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido Administrador");
		lblNewLabel.setBounds(5, 6, 377, 46);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("¿QUE QUIERES HACER?");
		lblNewLabel_1.setBounds(5, 68, 201, 46);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblNewLabel_1);
		
		JButton btnCrearAdmin = new JButton("      AÑADIR NUEVO ADMINISTRADOR");
		btnCrearAdmin.setBounds(5, 144, 377, 46);
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
		btnRepartitPuntos.setBounds(5, 190, 377, 46);
		btnRepartitPuntos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaRepartirPuntos v= new VentanaRepartirPuntos();
				v.setVisible(true);
				VentanaAdministrador.this.setVisible(false);
				
			}
		});
		btnRepartitPuntos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(btnRepartitPuntos);
		
		JButton btnRepartirDinero = new JButton("         REPARTIR DINERO DE LA JORNADA");
		btnRepartirDinero.setBounds(5, 236, 377, 46);
		btnRepartirDinero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaRepartirDinero v= new VentanaRepartirDinero();
				v.setVisible(true);
				VentanaAdministrador.this.setVisible(false);
				
			}
		});
		btnRepartirDinero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(btnRepartirDinero);
		
		JButton btnNewButton_3 = new JButton("        CAMBIAR VALOR DE LOS JUGADORES");
		btnNewButton_3.setBounds(5, 282, 377, 46);
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
		btnCrearMercado.setBounds(5, 328, 377, 46);
		btnCrearMercado.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCrearMercado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBManager dbmanager = new DBManager();
				dbmanager.conectar();
				dbmanager.devolverJugadoresDeUsuario();
				dbmanager.cambiarJugadoresDeUsuario();
				dbmanager.eliminarJugadoresCambiados();
				dbmanager.updateJugador();
				dbmanager.eliminarMercado();
				List<Integer> ligas = dbmanager.crearListaIdLigas();
				for (int i = 0; i < ligas.size(); i++) {
					
					dbmanager.crearMercado(ligas.get(i));
				}
				JOptionPane.showMessageDialog(VentanaAdministrador.this,
						"Mercados creados con exito");
				Thread hilo = new Thread() {
					@Override
					public void run() {
						VentanaHilo v = new VentanaHilo(VentanaAdministrador.this);
						v.setVisible(true);
					}
				};
				hilo.start();
				Logger logger = Logger.getLogger("Final de evento");
				logger.info("Evento finalizado");
				dbmanager.disconnect();
			}
			
			
		});
		contentPane.add(btnCrearMercado);
		
		JButton btnNewButton = new JButton("Cerrar sesion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InicioSesion v= new InicioSesion();
				v.setVisible(true);
				VentanaAdministrador.this.setVisible(false);
				JOptionPane.showMessageDialog(VentanaAdministrador.this,
						"Se cerro la sesion");
				
			}
		});
		btnNewButton.setBounds(254, 63, 123, 23);
		contentPane.add(btnNewButton);
	}
}
