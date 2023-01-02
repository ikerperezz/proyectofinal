package ventanas;

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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class CrearCuenta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private JPasswordField passwordField_1;
	private ArrayList<UsuarioPublico> up = new ArrayList<UsuarioPublico>();
	static String nombreUsuario;

	/**
	 * Create the frame.
	 */
	public CrearCuenta() {
		DBManager dbmanager = new DBManager();
		setTitle("Registrarte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Verdana", Font.PLAIN, 17));
		lblUsuario.setBounds(35, 47, 132, 14);
		contentPane.add(lblUsuario);

		JLabel lblContraseina = new JLabel("Contraseña:");
		lblContraseina.setFont(new Font("Verdana", Font.PLAIN, 17));
		lblContraseina.setBounds(35, 89, 132, 14);
		contentPane.add(lblContraseina);

		JButton btnCrearCuenta = new JButton("Crear Cuenta");
		btnCrearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbmanager.conectar();
				List<UsuarioPublico> up = dbmanager.crearLista();
				boolean crearUsuario = true;
				if (!passwordField.getText().equals(passwordField_1.getText())) {
					crearUsuario = false;
					JOptionPane.showMessageDialog(CrearCuenta.this, "Las contraseñas no coinciden.");
				}
				if (crearUsuario == true) {
					for (int i = 0; i < up.size(); i++) {
						if (textField.getText().trim().isEmpty()
								|| String.valueOf(passwordField.getText()).trim().isEmpty()) {
							crearUsuario = false;
							JOptionPane.showMessageDialog(CrearCuenta.this,
									"Hay campos obligatorios vacios, rellene todos");
							break;
						}
						if (textField.getText().equals(up.get(i).getUsuario())) {
							JOptionPane.showMessageDialog(CrearCuenta.this, "Usuario Existente");
							crearUsuario = false;
							break;

						}

					}
				}
				if (crearUsuario == true) {
					UsuarioPublico us = new UsuarioPublico(textField.getText(), passwordField.getText(), 0, 0, 1000000,
							0);
					nombreUsuario = textField.getText();
					dbmanager.actualizarUsuarios(us);
					VentanaOpcionesLiga v = new VentanaOpcionesLiga(CrearCuenta.this);
					v.setVisible(true);
					CrearCuenta.this.setVisible(false);
					Logger logger = Logger.getLogger( "Creación de cuenta");
					logger.info("Cuenta creada");
				}
				dbmanager.disconnect();
			}

		});
		btnCrearCuenta.setFont(new Font("Verdana", Font.PLAIN, 17));
		btnCrearCuenta.setBounds(103, 195, 194, 32);
		contentPane.add(btnCrearCuenta);

		passwordField = new JPasswordField();
		passwordField.setBounds(214, 89, 142, 20);
		contentPane.add(passwordField);

		textField = new JTextField();
		textField.setFont(new Font("Verdana", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBounds(214, 45, 142, 20);
		contentPane.add(textField);

		JLabel lblRepetirContrasea = new JLabel("Repetir Contraseña:");
		lblRepetirContrasea.setFont(new Font("Verdana", Font.PLAIN, 17));
		lblRepetirContrasea.setBounds(35, 131, 231, 14);
		contentPane.add(lblRepetirContrasea);

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(214, 131, 142, 20);
		contentPane.add(passwordField_1);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				InicioSesion v = new InicioSesion();
				v.setVisible(true);
				CrearCuenta.this.setVisible(false);
			}
		});
		btnVolver.setBounds(347, 11, 89, 23);
		contentPane.add(btnVolver);
	}

}
