package ventanas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import baseDatos.DBManager;
import clases.BaseDatos;
import clases.Jugador;
import javax.swing.JSpinner;
import java.awt.Font;

public class VentanaMercado extends JFrame {

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
	public VentanaMercado() {
		setTitle("Mercado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 746, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mercado liga " + InterfazDeUsuarioPublico.usP.getIdLiga());
		lblNewLabel.setBounds(272, 11, 117, 14);
		contentPane.add(lblNewLabel);

		cargarJlist();

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazDeUsuarioPublico v = new InterfazDeUsuarioPublico(null, null, null);
				v.setVisible(true);
				VentanaMercado.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 429, 89, 23);
		contentPane.add(btnNewButton);

	

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(65, 49, 329, 333);
		contentPane.add(scrollPane);
		JList<String> list = new JList<String>(model);
		scrollPane.setViewportView(list);
		
		NumberFormat format = NumberFormat.getInstance();
	    format.setMinimumIntegerDigits(6);
	    format.setMaximumIntegerDigits(9);
	    format.setGroupingUsed(false);
		
	
		JLabel lblNewLabel_1 = new JLabel("€");
		lblNewLabel_1.setBounds(689, 122, 20, 23);
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
		spinner_1.setBounds(432, 123, 30, 20);
		contentPane.add(spinner_1);
		
		
		
		JSpinner spinner_2 = new JSpinner(spModel);
		spinner_2.setBounds(458, 123, 30, 20);
		contentPane.add(spinner_2);
		
		JSpinner spinner_3 = new JSpinner(spModel2);
		spinner_3.setBounds(501, 122, 30, 20);
		contentPane.add(spinner_3);
		
		JSpinner spinner_1_1 = new JSpinner(spModel3);
		spinner_1_1.setBounds(529, 122, 30, 20);
		contentPane.add(spinner_1_1);
		
		JSpinner spinner_2_1 = new JSpinner(spModel4);
		spinner_2_1.setBounds(555, 122, 30, 20);
		contentPane.add(spinner_2_1);
		
		JSpinner spinner_3_1 = new JSpinner(spModel5);
		spinner_3_1.setBounds(595, 122, 30, 20);
		contentPane.add(spinner_3_1);
		
		JSpinner spinner_2_1_1 = new JSpinner(spModel6);
		spinner_2_1_1.setBounds(649, 122, 30, 20);
		contentPane.add(spinner_2_1_1);
		
		JSpinner spinner_1_1_1 = new JSpinner(spModel7);
		spinner_1_1_1.setBounds(623, 122, 30, 20);
		contentPane.add(spinner_1_1_1);
		
		
		
		JButton btnOferta = new JButton("Realizar oferta");
		btnOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				DBManager dbmanager = new DBManager();
				dbmanager.conectar();
				
				
				int value =((int)spinner_1.getValue()*10000000) +  ((int)spinner_2.getValue()*1000000) + ((int)spinner_3.getValue()*100000)+ ((int)spinner_1_1.getValue()*10000)+ ((int)spinner_2_1.getValue()*1000)+ ((int)spinner_3_1.getValue()*100)+ ((int)spinner_2_1_1.getValue()*10)+ ((int)spinner_1_1_1.getValue()*1);
	
				
				if(!(list.getSelectedIndex()==-1)) {
					int id = dbmanager.conseguirIdJugador(list.getSelectedValue());
					int valor = dbmanager.conseguirValor(id);
				if(value>valor) {
					dbmanager.updateMayoroferta(value, list.getSelectedValue(), InterfazDeUsuarioPublico.usP);
				dbmanager.disconnect();
				Logger logger = Logger.getLogger( "Realización de oferta");
				logger.info("Oferta realizada");	
				JOptionPane.showMessageDialog(VentanaMercado.this,
						"Oferta realizada");
				}else {
					JOptionPane.showMessageDialog(VentanaMercado.this,
							"La oferta es menor al valor");
				}	
				}else {
					JOptionPane.showMessageDialog(VentanaMercado.this,
							"No hay ningun jugador seleccionado");
				}
				
				
				
			}
		});
		
		btnOferta.setBounds(476, 156, 146, 23);
		contentPane.add(btnOferta);
		
		JLabel lblNewLabel_1_1 = new JLabel("   . ");
		lblNewLabel_1_1.setBounds(485, 122, 20, 23);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("   . ");
		lblNewLabel_1_1_1.setBounds(580, 122, 20, 23);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblDinero = new JLabel("Saldo: "+InterfazDeUsuarioPublico.usP.getDineroDisponible() + "€");
		lblDinero.setFont(new Font("Yu Gothic UI", Font.PLAIN, 14));
		lblDinero.setBounds(432, 343, 277, 35);
		contentPane.add(lblDinero);
	}

	public JList cargarJlist() {
		// TODO Auto-generated method stub
		DBManager dbmanager = new DBManager();
		dbmanager.conectar();
		List<Jugador> jug = dbmanager.crearListaMercado(InterfazDeUsuarioPublico.usP.getIdLiga());
		model = new DefaultListModel<String>();
		for (Jugador jugador : jug) {
			model.addElement(jugador.toString());
		}
		return null;
	}

		class RendererBusquedaJugadores extends JButton implements ListCellRenderer<clases.BaseDatos> {

			private static final long serialVersionUID = 1L;
			private ImageIcon selectedIcon;

			public RendererBusquedaJugadores() {
				selectedIcon = new ImageIcon(
						getClass().getResource("https://cdn-icons-png.flaticon.com/512/2015/2015046.png"));
				setOpaque(true);
				setIcon(selectedIcon);
			}

			@Override
			public Component getListCellRendererComponent(JList<? extends BaseDatos> list, BaseDatos value, int index,
					boolean isSelected, boolean cellHasFocus) {
				return this;
			}

		}
	}

