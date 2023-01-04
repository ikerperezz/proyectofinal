package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDatos.DBManager;
import clases.Administrador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

public class VentanaAñadirAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;


	/**
	 * Create the frame.
	 */
	public VentanaAñadirAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DATOS DEL NUEVO ADMINISTRADOR");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(31, 11, 350, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario");
		lblNombreDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombreDeUsuario.setBounds(31, 72, 192, 50);
		contentPane.add(lblNombreDeUsuario);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContrasea.setBounds(31, 148, 192, 50);
		contentPane.add(lblContrasea);
		
		textField = new JTextField();
		textField.setBounds(223, 81, 168, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("CREAR ADMINISTRADOR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (textField.getText().isEmpty()
						|| String.valueOf(passwordField.getText()).trim().isEmpty()) {

					JOptionPane.showMessageDialog(VentanaAñadirAdmin.this,
							"Hay campos obligatorios vacios, rellene todos");
					textField.setText("");
					passwordField.setText("");
				}
				
				if (!textField.getText().isEmpty()
							&& (!String.valueOf(passwordField.getText()).trim().isEmpty())){
										
					BufferedWriter bw = null;
				    FileWriter fw = null;
				    

				    try {
				        
				    	String nombreUsuario=textField.getText();
					    String contrasenia=String.valueOf(passwordField.getText());
					    String adminNuevo=String.format("%s;%s%n", nombreUsuario, contrasenia);
				        
				    	File file = new File("src/ficheroExterno/Administradores.csv");
				        				        
				        
				        fw = new FileWriter(file.getAbsoluteFile(), true);
				        			        
				        
				        bw = new BufferedWriter(fw);
				        bw.write(adminNuevo);
				        
				        
				    } catch (IOException e1) {
				        e1.printStackTrace();
				        
				    } finally {
				        try {
				                        
				            if (bw != null)
				                bw.close();
				            if (fw != null)
				                fw.close();
				            
				        } catch (IOException ex) {
				            ex.printStackTrace();
				        }
				    }
					
					
					JOptionPane.showMessageDialog(VentanaAñadirAdmin.this,
							"Administrador añadido con exito");
					
					textField.setText("");
					passwordField.setText("");
				}
					
				}
				
		});
		btnNewButton.setBounds(249, 234, 177, 41);
		contentPane.add(btnNewButton);
		
		JButton btnBorrar = new JButton("BORRAR");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textField.setText("");
				passwordField.setText("");
				
			}
		});
		btnBorrar.setBounds(31, 234, 177, 41);
		contentPane.add(btnBorrar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaAdministrador v = new VentanaAdministrador();
				v.setVisible(true);
				VentanaAñadirAdmin.this.setVisible(false);
				
			}
		});
		btnVolver.setBounds(158, 286, 120, 29);
		contentPane.add(btnVolver);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(223, 155, 168, 30);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("*Este campo es obligatorio");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setBounds(229, 123, 152, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("*Este campo es obligatorio");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setBounds(229, 196, 152, 14);
		contentPane.add(lblNewLabel_1_1);
	}
}
