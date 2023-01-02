package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDatos.DBManager;
import clases.Liga;
import clases.UsuarioPublico;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class VentanaUnirseLiga extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;



	/**
	 * Create the frame.
	 */
	public VentanaUnirseLiga() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		JLabel lblCodigoLiga = new JLabel("Codigo de liga:");
		panel_2.add(lblCodigoLiga);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		
		textField = new JTextField();
		panel_3.add(textField);
		textField.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		contentPane.add(panel_4);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaOpcionesLiga v = new VentanaOpcionesLiga(null);
				v.setVisible(true);
				VentanaUnirseLiga.this.setVisible(false);
			}
		});
		panel_4.add(btnVolver);
		
		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5);
		
		JButton btnUnirse = new JButton("Unirse");
		btnUnirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBManager dbmanager = new DBManager();
				dbmanager.conectar();
				boolean ligaexiste=false;
				UsuarioPublico usP = new UsuarioPublico("", "", 0, 0, 0,0);
				List <Liga> liga= dbmanager.crearListaLiga();
				for (int i = 0; i < liga.size(); i++) {
					if (String.valueOf(liga.get(i).getIdLiga()).equals(textField.getText())) {
						usP.setIdLiga(liga.get(i).getIdLiga());
						dbmanager.updateLigaEnUsuario(usP, CrearCuenta.nombreUsuario);
						dbmanager.crearPlantilla(usP.getIdLiga(), CrearCuenta.nombreUsuario);
						ligaexiste=true;
						InterfazDeUsuarioPublico v = new InterfazDeUsuarioPublico(null, null, null);
						v.setVisible(true);
						VentanaUnirseLiga.this.setVisible(false);
						Logger logger = Logger.getLogger( "Unirse a liga");
						logger.info("Te has unido a esta liga: " + textField.getText());
						break;
					}
				}
				if(ligaexiste==false) {
					JOptionPane.showMessageDialog(VentanaUnirseLiga.this,
							"Liga inexistente");
				}
				
			}
		});
		panel_5.add(btnUnirse);
	}

}
