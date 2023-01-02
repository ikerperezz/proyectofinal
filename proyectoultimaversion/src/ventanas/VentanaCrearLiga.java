package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDatos.DBManager;
import clases.Liga;
import clases.UsuarioPublico;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class VentanaCrearLiga extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	static String nombreLiga;


	/**
	 * Create the frame.
	 */
	public VentanaCrearLiga() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		JPanel panel_7 = new JPanel();
		contentPane.add(panel_7);
		
		JPanel panel_6 = new JPanel();
		contentPane.add(panel_6);
		
		JLabel lblNombreLiga_1 = new JLabel("Nombre liga:");
		panel_6.add(lblNombreLiga_1);
		
		JPanel panel_8 = new JPanel();
		contentPane.add(panel_8);
		
		textField = new JTextField();
		textField.setColumns(10);
		panel_8.add(textField);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaOpcionesLiga v = new VentanaOpcionesLiga(null);
				v.setVisible(true);
				VentanaCrearLiga.this.setVisible(false);
			}
		});
		panel_1.add(btnCancelar);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		
		JButton btnCrearLiga = new JButton("Crear liga");
		btnCrearLiga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBManager dbmanager = new DBManager();
				dbmanager.conectar();
				nombreLiga=textField.getText();
				Liga l = new Liga(0,nombreLiga);
				UsuarioPublico usP = new UsuarioPublico("", "", 0, 0, 0,0);
				int idLiga=dbmanager.actualizarLigas(l);
				
				usP.setIdLiga(idLiga);
				dbmanager.updateLigaEnUsuario(usP, CrearCuenta.nombreUsuario);
				dbmanager.crearPlantilla(idLiga, CrearCuenta.nombreUsuario);
				
				dbmanager.disconnect();
				InterfazDeUsuarioPublico v = new InterfazDeUsuarioPublico(null, null, null);
				v.setVisible(true);
				VentanaCrearLiga.this.setVisible(false);
				Logger logger = Logger.getLogger( "Creación de liga");
				logger.info("Liga creada");
			}
		});
		panel_3.add(btnCrearLiga);
	}
}
