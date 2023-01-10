package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import baseDatos.DBManager;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class VentanaRepartirPuntos extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<String> model;

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
		
			
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 98, 172, 237);
		contentPane.add(scrollPane);
		cargarJList();
		JList<String> list = new JList<String>(model);
		scrollPane.setViewportView(list);
		
		JLabel lblNewLabel = new JLabel("USUARIOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(33, 54, 141, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPuntos = new JLabel("PUNTOS");
		lblPuntos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPuntos.setBounds(352, 139, 74, 14);
		contentPane.add(lblPuntos);
		
		SpinnerModel spModel_1 = new SpinnerNumberModel(0, //initial value
                0, //min
                9, //max
                1); //step
		SpinnerModel spModel = new SpinnerNumberModel(0, //initial value
                0, //min
                9, //max
                1); //step
		
		JSpinner spinner = new JSpinner(spModel);
		spinner.setBounds(312, 138, 30, 20);
		contentPane.add(spinner);
		
		JSpinner haundi = new JSpinner(spModel_1);
		haundi.setBounds(272, 138, 30, 20);
		contentPane.add(haundi);
		
		JButton btnNewButton = new JButton("DAR PUNTOS");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DBManager dbmanager = new DBManager();
				dbmanager.conectar();
				
				
				int value =((int)haundi.getValue()*10) +  ((int)spinner.getValue());
				
				if(!(list.getSelectedIndex()==-1)) {
					int id = dbmanager.conseguirIdUsuario(list.getSelectedValue());
					
					int puntosIniciales=dbmanager.conseguirPuntosUsuario(list.getSelectedValue());
					
					int dineroInicial=dbmanager.conseguirDineroUsuario(list.getSelectedValue());
					
					if(dineroInicial>=0) {
					
					int puntosFinales=value+puntosIniciales;
					
					dbmanager.updatePuntosUsuarios(id, puntosFinales);
					
					JOptionPane.showMessageDialog(VentanaRepartirPuntos.this,
							"Puntos repartidos");
					
					} else {
						
						JOptionPane.showMessageDialog(VentanaRepartirPuntos.this,
								"No puedes dar puntos a ningun usuario con dinero en negativo");
						
					}
					dbmanager.disconnect();
					
					
					
					}
				else {
						JOptionPane.showMessageDialog(VentanaRepartirPuntos.this,
								"No hay ningun usuario seleccionado");
					}
					
			}
		});
		btnNewButton.setBounds(272, 194, 138, 36);
		contentPane.add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("volver");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaAdministrador v= new VentanaAdministrador();
				v.setVisible(true);
				VentanaRepartirPuntos.this.setVisible(false);
				
			}
		});
		btnNewButton_1.setBounds(272, 312, 89, 23);
		contentPane.add(btnNewButton_1);
	}
	
	public void cargarJList() {
		// TODO Auto-generated method stub
		DBManager dbmanager= new DBManager();
		dbmanager.conectar();
		Map<String, Integer> usuario = dbmanager.crearListaUsuariosPuntos();
		
		List<String> nom = dbmanager.crearListaUsuariosNombre();
		
		model = new DefaultListModel<String>();
		
	for (int i = 0; i < nom.size(); i++) {
		int value = usuario.get(nom.get(i));
		String key = nom.get(i);
		
		model.addElement(key +" - "+ value + "puntos");
	}
			
			dbmanager.disconnect();
			
		}
}
