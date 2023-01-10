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

public class VentanaRepartirDinero extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<String> model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRepartirDinero frame = new VentanaRepartirDinero();
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
	public VentanaRepartirDinero() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 83, 217, 225);
		contentPane.add(scrollPane);
		cargarJList();
		JList<String> list = new JList<String>(model);
		scrollPane.setViewportView(list);
		
		JLabel lblNewLabel = new JLabel("USUARIOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(31, 50, 141, 14);
		contentPane.add(lblNewLabel);
		
		SpinnerModel spModel_1 = new SpinnerNumberModel(0, //initial value
                0, //min
                9, //max
                1); //step
		SpinnerModel spModel = new SpinnerNumberModel(0, //initial value
                0, //min
                9, //max
                1); //step
		
		SpinnerModel spModel2 = new SpinnerNumberModel(0, //initial value
                0, //min
                9, //max
                1); //step
		
		SpinnerModel spModel3 = new SpinnerNumberModel(0, //initial value
                0, //min
                9, //max
                1); //step
		
		SpinnerModel spModel4 = new SpinnerNumberModel(0, //initial value
                0, //min
                9, //max
                1); //step
		SpinnerModel spModel5 = new SpinnerNumberModel(0, //initial value
                0, //min
                9, //max
                1); //step
		SpinnerModel spModel6 = new SpinnerNumberModel(0, //initial value
                0, //min
                9, //max
                1); //step
		
		SpinnerModel spModel7 = new SpinnerNumberModel(0, //initial value
                0, //min
                9, //max
                1); //step
		
		
		
		JSpinner spinner_1 = new JSpinner((spModel));
		spinner_1.setBounds(268, 134, 30, 20);
		contentPane.add(spinner_1);
		
		JSpinner spinner_1_1 = new JSpinner((spModel_1));
		spinner_1_1.setBounds(297, 134, 30, 20);
		contentPane.add(spinner_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("   . ");
		lblNewLabel_1_1.setBounds(322, 137, 20, 23);
		contentPane.add(lblNewLabel_1_1);
		
		JSpinner spinner_1_2 = new JSpinner((spModel2));
		spinner_1_2.setBounds(337, 134, 30, 20);
		contentPane.add(spinner_1_2);
		
		JSpinner spinner_1_3 = new JSpinner((spModel3));
		spinner_1_3.setBounds(366, 134, 30, 20);
		contentPane.add(spinner_1_3);
		
		JSpinner spinner_1_4 = new JSpinner((spModel4));
		spinner_1_4.setBounds(395, 134, 30, 20);
		contentPane.add(spinner_1_4);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("   . ");
		lblNewLabel_1_1_1.setBounds(421, 137, 20, 23);
		contentPane.add(lblNewLabel_1_1_1);
		
		JSpinner spinner_1_5 = new JSpinner((spModel5));
		spinner_1_5.setBounds(435, 134, 30, 20);
		contentPane.add(spinner_1_5);
		
		JSpinner spinner_1_6 = new JSpinner((spModel6));
		spinner_1_6.setBounds(465, 134, 30, 20);
		contentPane.add(spinner_1_6);
		
		JSpinner spinner_1_7 = new JSpinner((spModel7));
		spinner_1_7.setBounds(496, 134, 30, 20);
		contentPane.add(spinner_1_7);
		
		JLabel lblNewLabel_1 = new JLabel("€");
		lblNewLabel_1.setBounds(533, 133, 30, 23);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("DAR DINERO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DBManager dbmanager = new DBManager();
				dbmanager.conectar();
				
				int value =((int)spinner_1.getValue()*10000000) +  ((int)spinner_1_1.getValue()*1000000) + ((int)spinner_1_2.getValue()*100000)+ ((int)spinner_1_3.getValue()*10000)+ ((int)spinner_1_4.getValue()*1000)+ ((int)spinner_1_5.getValue()*100)+ ((int)spinner_1_6.getValue()*10)+ ((int)spinner_1_7.getValue()*1);
				
				if(!(list.getSelectedIndex()==-1)) {
					
					int id = dbmanager.conseguirIdUsuario(list.getSelectedValue());
					
					int dineroInicial=dbmanager.conseguirDineroUsuario(list.getSelectedValue());
					
					int dineroFinal=value+dineroInicial;
					
					dbmanager.updateDineroUsuarios(id, dineroFinal);
					dbmanager.disconnect();
					
					
					JOptionPane.showMessageDialog(VentanaRepartirDinero.this,
							"Puntos repartidos");
					}else {
						JOptionPane.showMessageDialog(VentanaRepartirDinero.this,
								"No hay ningun usuario seleccionado");
					}
					
			}
				
				
				
			
		});
		btnNewButton.setBounds(333, 185, 147, 40);
		contentPane.add(btnNewButton);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaAdministrador v= new VentanaAdministrador();
				v.setVisible(true);
				VentanaRepartirDinero.this.setVisible(false);
				
			}
		});
		btnVolver.setBounds(351, 326, 90, 23);
		contentPane.add(btnVolver);
	}
	
	public void cargarJList() {
		// TODO Auto-generated method stub
		DBManager dbmanager= new DBManager();
		dbmanager.conectar();
		
		Map<String, Integer> usuario = dbmanager.crearListaUsuariosDinero();
		
		List<String> nom = dbmanager.crearListaUsuariosNombre();
		
		model = new DefaultListModel<String>();
		
	for (int i = 0; i < nom.size(); i++) {
		int value = usuario.get(nom.get(i));
		String key = nom.get(i);
		
		model.addElement(key +" - "+ value + "€");
	}
			
			dbmanager.disconnect();
			
		}
}
