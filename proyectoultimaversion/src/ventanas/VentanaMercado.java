package ventanas;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import baseDatos.DBManager;
import clases.BaseDatos;
import clases.Jugador;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

public class VentanaMercado extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<String> model;


	/**
	 * Create the frame.
	 */
	public VentanaMercado() {
		setTitle("Mercado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(272, 11, 49, 14);
		contentPane.add(lblNewLabel);
		
		cargarJlist();
		JList list = new JList(model);
		list.setBounds(103, 110, 302, 286);
		contentPane.add(list);
		
		JList list_1 = new JList();
		list_1.setBounds(408, 181, 115, 156);
		contentPane.add(list_1);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(526, 433, 49, 14);
		contentPane.add(lblNewLabel_1);
		
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
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(522, 65, 89, 23);
		contentPane.add(btnNewButton_1);
	}

public void cargarJlist() {
		// TODO Auto-generated method stub
		DBManager dbmanager= new DBManager();
		dbmanager.conectar();
		List<Jugador> jug = dbmanager.crearListaMercado(InterfazDeUsuarioPublico.usP.getIdLiga());
		model = new DefaultListModel<String>();
		for (Jugador jugador: jug) {
			model.addElement(jugador.toString());
		}	
	
}





class RendererBusquedaJugadores extends JButton implements ListCellRenderer<clases.BaseDatos>{
	
	private static final long serialVersionUID = 1L;
	private ImageIcon selectedIcon;
	 
	 public RendererBusquedaJugadores() {
         selectedIcon = new ImageIcon(getClass().getResource("https://cdn-icons-png.flaticon.com/512/2015/2015046.png"));
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
