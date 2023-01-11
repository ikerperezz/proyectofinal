package ventanas;

import java.awt.Color;
import java.awt.Component;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDatos.DBManager;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;

public class VentanaAñadirValor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<String> model;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public VentanaAñadirValor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 64, 238, 283);
		contentPane.add(scrollPane);
		cargarJList();
		JList<String> list = new JList<String>(model);
		scrollPane.setViewportView(list);
		
		JLabel lblNewLabel_1 = new JLabel("€");
		lblNewLabel_1.setBounds(560, 142, 20, 23);
		contentPane.add(lblNewLabel_1);
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
		
		JSpinner spinner_1 = new JSpinner(spModel_1);
		spinner_1.setBounds(303, 143, 30, 20);
		contentPane.add(spinner_1);
		
		
		
		JSpinner spinner_2 = new JSpinner(spModel);
		spinner_2.setBounds(329, 143, 30, 20);
		contentPane.add(spinner_2);
		
		JSpinner spinner_3 = new JSpinner(spModel2);
		spinner_3.setBounds(372, 142, 30, 20);
		contentPane.add(spinner_3);
		
		JSpinner spinner_1_1 = new JSpinner(spModel3);
		spinner_1_1.setBounds(400, 142, 30, 20);
		contentPane.add(spinner_1_1);
		
		JSpinner spinner_2_1 = new JSpinner(spModel4);
		spinner_2_1.setBounds(426, 142, 30, 20);
		contentPane.add(spinner_2_1);
		
		JSpinner spinner_3_1 = new JSpinner(spModel5);
		spinner_3_1.setBounds(466, 142, 30, 20);
		contentPane.add(spinner_3_1);
		
		JSpinner spinner_2_1_1 = new JSpinner(spModel6);
		spinner_2_1_1.setBounds(520, 142, 30, 20);
		contentPane.add(spinner_2_1_1);
		
		JSpinner spinner_1_1_1 = new JSpinner(spModel7);
		spinner_1_1_1.setBounds(494, 142, 30, 20);
		contentPane.add(spinner_1_1_1);
		
		JButton btnCambiarValor = new JButton("Cambiar Valor");
		btnCambiarValor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DBManager dbmanager = new DBManager();
				dbmanager.conectar();
				
				
				int value =((int)spinner_1.getValue()*10000000) +  ((int)spinner_2.getValue()*1000000) + ((int)spinner_3.getValue()*100000)+ ((int)spinner_1_1.getValue()*10000)+ ((int)spinner_2_1.getValue()*1000)+ ((int)spinner_3_1.getValue()*100)+ ((int)spinner_2_1_1.getValue()*10)+ ((int)spinner_1_1_1.getValue()*1);
	
				
				if(!(list.getSelectedIndex()==-1)) {
					int id = dbmanager.conseguirIdJugador(list.getSelectedValue());
			
					dbmanager.updateValorJugadores(id, value);
				dbmanager.disconnect();
				JOptionPane.showMessageDialog(VentanaAñadirValor.this,
						"Valor cambiado");
				}else {
					JOptionPane.showMessageDialog(VentanaAñadirValor.this,
							"No hay ningun jugador seleccionado");
				}
			}
		});
		btnCambiarValor.setBounds(347, 180, 146, 23);
		contentPane.add(btnCambiarValor);
		
		JLabel lblNewLabel_1_1 = new JLabel("   . ");
		lblNewLabel_1_1.setBounds(356, 146, 20, 23);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("   . ");
		lblNewLabel_1_1_1.setBounds(451, 146, 20, 23);
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAdministrador v = new VentanaAdministrador();
				v.setVisible(true);
				VentanaAñadirValor.this.setVisible(false);
			}
		});
		btnVolver.setBounds(382, 347, 89, 23);
		contentPane.add(btnVolver);
	
		list.setCellRenderer(new DefaultListCellRenderer() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			
			@Override
		    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				
				Component c= super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
					
				String escrito= textField.getText();
				
				String jugadores= (String) list.getModel().getElementAt(index);
				
									
				if((!escrito.isEmpty()) && (!escrito.isBlank()) && (jugadores.contains(escrito))) {
					
					c.setBackground(Color.red);
					
				}	else {
					
					c.setBackground(Color.white);
					
				}
					
					return c;
				}
			
		});
		
		JLabel lblNewLabel = new JLabel("Jugador a buscar:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(329, 38, 180, 42);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(466, 51, 126, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	
	
	
	}
	
	public void cargarJList() {
		// TODO Auto-generated method stub
		DBManager dbmanager= new DBManager();
		dbmanager.conectar();
		Map<String, Integer> jug = dbmanager.crearListaJugadoresValor();
		
		List<String> nom = dbmanager.crearListaJugadoresNombre();
		System.out.println(jug.get(nom.get(0)));
		model = new DefaultListModel<String>();
	for (int i = 0; i < nom.size(); i++) {
		int value = jug.get(nom.get(i));
		String key = nom.get(i);
		
		model.addElement(key +" - "+ value + "€");
	}
			
			dbmanager.disconnect();
			
		}
	}
	

