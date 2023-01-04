package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDatos.DBManager;
import clases.UsuarioPublico;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class ModificarUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	

	/**
	 * Create the frame.
	 */
	public ModificarUsuario() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(256, 58, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNuevoUsuario = new JLabel("Nuevo usuario: ");
		lblNuevoUsuario.setBounds(55, 61, 102, 14);
		contentPane.add(lblNuevoUsuario);
		
		JLabel lblNuevaContraseina = new JLabel("Nueva contraseña:");
		lblNuevaContraseina.setBounds(55, 120, 102, 14);
		contentPane.add(lblNuevaContraseina);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(256, 117, 96, 20);
		contentPane.add(passwordField);
		
		JButton btnGuardar = new JButton("Guardar");
		
		btnGuardar.addActionListener(new ActionListener() {	
			
			
			public void actionPerformed(ActionEvent e) {
				DBManager dbmanager = new DBManager();
				dbmanager.conectar();
				List<UsuarioPublico> up = dbmanager.crearLista();
				boolean fallo = false;
				UsuarioPublico usP = new UsuarioPublico("", "", 0, 0, 0,0);
				for (int i = 0; i < up.size(); i++) {
					if(up.get(i).getUsuario().equals(InicioSesion.nombreUsuario)){
						usP.setUsuario(up.get(i).getUsuario());
						usP.setDineroDisponible(up.get(i).getDineroDisponible());
						usP.setContraseina(up.get(i).getContraseina());
						usP.setIdLiga(up.get(i).getIdLiga());
						usP.setIdUsuarioPublico(up.get(i).getIdUsuarioPublico());
						break;
					}
				}
				if(!textField.getText().equals("")) {
				usP.setUsuario(textField.getText());
				}
				if(!passwordField.getText().equals("")) {
				usP.setContraseina(passwordField.getText());
					}
				for (int i = 0; i < up.size(); i++) {
					if(up.get(i).getUsuario().equals(textField.getText())){
						fallo = true;
						JOptionPane.showMessageDialog(ModificarUsuario.this,
								"Usuario existente.");
						break;
					}
				}
				if (fallo==false) {
					dbmanager.update(usP, InicioSesion.nombreUsuario);
					JOptionPane.showMessageDialog(ModificarUsuario.this,
							"Usuario modificado.");
					InterfazDeUsuarioPublico v = new InterfazDeUsuarioPublico(null, null, null);
					v.setVisible(true);
					ModificarUsuario.this.setVisible(false);
					Logger logger = Logger.getLogger( "Modificación usuario");
					logger.info("Usuario modificado");
				}
				dbmanager.disconnect();
				
			}
		});
		btnGuardar.setBounds(234, 195, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazDeUsuarioPublico v = new InterfazDeUsuarioPublico(null, null, null);
				v.setVisible(true);
				ModificarUsuario.this.setVisible(false);
			}
		});
		btnCancelar.setBounds(68, 195, 89, 23);
		contentPane.add(btnCancelar);
	}
}
