package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDatos.DBManager;
import clases.UsuarioPublico;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class InicioSesion extends JFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	static String nombreUsuario;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioSesion frame = new InicioSesion();
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
	public InicioSesion() {
		
		DBManager dbmanager = new DBManager();
		setTitle("Inicio De Sesion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel label = new JLabel("");
		contentPane.add(label);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JButton btnCrearCuenta = new JButton("Crear cuenta");
		panel.add(btnCrearCuenta);
		btnCrearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearCuenta v = new CrearCuenta();
				v.setVisible(true);
				InicioSesion.this.setVisible(false);
			}
		});
		btnCrearCuenta.setFont(new Font("Verdana", Font.PLAIN, 11));
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Verdana", Font.PLAIN, 17));
		contentPane.add(lblUsuario);
		
		JLabel lblCampoObligatorio = new JLabel("*Este campo es obligatorio");
		lblCampoObligatorio.setForeground(Color.RED);
		lblCampoObligatorio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCampoObligatorio.setVisible(false);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setFont(new Font("Verdana", Font.PLAIN, 14));
		textField.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7, BorderLayout.WEST);
		
		JPanel panel_8 = new JPanel();
		panel_1.add(panel_8, BorderLayout.NORTH);
		
		JPanel panel_9 = new JPanel();
		panel_1.add(panel_9, BorderLayout.EAST);
		
		JPanel panel_10 = new JPanel();
		panel_1.add(panel_10, BorderLayout.SOUTH);
		
		JLabel label_1 = new JLabel("");
		contentPane.add(label_1);
		contentPane.add(lblCampoObligatorio);
		
		JLabel lblCampoObligatorio1 = new JLabel("*Este campo es obligatorio");
		lblCampoObligatorio1.setForeground(Color.RED);
		lblCampoObligatorio1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCampoObligatorio1.setVisible(false);
		
		JLabel lblContraseina = new JLabel("Contraseña:");
		lblContraseina.setFont(new Font("Verdana", Font.PLAIN, 17));
		contentPane.add(lblContraseina);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		passwordField = new JPasswordField();
		panel_2.add(passwordField);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.SOUTH);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, BorderLayout.WEST);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6, BorderLayout.EAST);
		
		JLabel label_2 = new JLabel("");
		contentPane.add(label_2);
		contentPane.add(lblCampoObligatorio1);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesión");
		btnIniciarSesion.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				dbmanager.conectar();
				List<UsuarioPublico> up = dbmanager.crearLista();
				

				boolean acceso = false;
				boolean camposVacios = false;

				for (int i = 0; i < up.size(); i++) {
					if (textField.getText().trim().isEmpty()
							|| String.valueOf(passwordField.getText()).trim().isEmpty()) {

						JOptionPane.showMessageDialog(InicioSesion.this,
								"Hay campos obligatorios vacios, rellene todos");
						lblCampoObligatorio.setVisible(true);
						lblCampoObligatorio1.setVisible(true);
						camposVacios=true;
						break;
					}
					if (textField.getText().equals(up.get(i).getUsuario())
							&& (passwordField.getText().equals(up.get(i).getContraseina()))) {
						nombreUsuario=textField.getText();
						InterfazDeUsuarioPublico v = new InterfazDeUsuarioPublico(null, null, null);
						v.setVisible(true);
						InicioSesion.this.setVisible(false);
						Logger logger = Logger.getLogger( "Inicio sesión");
						logger.info("Sesión iniciada");
						passwordField.setText("");
						acceso = true;
						break;
						

					}

				}

				if (acceso == false && camposVacios==false) {
					JOptionPane.showMessageDialog(InicioSesion.this,
							"Usuario o contrasena incorrecto, intentelo de nuevo");

					textField.setText("");
					passwordField.setText("");
			
			
		} 
		dbmanager.disconnect();
			}
		});
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textField.setText("");
				passwordField.setText("");
				
			}
		});
		btnBorrar.setFont(new Font("Verdana", Font.PLAIN, 17));
		contentPane.add(btnBorrar);
		btnIniciarSesion.setFont(new Font("Verdana", Font.PLAIN, 17));
		contentPane.add(btnIniciarSesion);

		
	
	}
}
