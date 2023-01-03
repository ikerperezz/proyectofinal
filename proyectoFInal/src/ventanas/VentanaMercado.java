package ventanas;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import baseDatos.DBManager;
import clases.BaseDatos;
import clases.Jugador;

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
		setBounds(100, 100, 635, 495);
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

		JButton btnOferta = new JButton("Realizar oferta");
		btnOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Logger logger = Logger.getLogger( "Realización de oferta");
				logger.info("Oferta realizada");
			}
		});
		btnOferta.setBounds(476, 156, 146, 23);
		contentPane.add(btnOferta);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(137, 47, 329, 333);
		contentPane.add(scrollPane);
		JList list = new JList(model);
		scrollPane.setViewportView(list);
		
		textField = new JTextField();
		textField.setBounds(476, 122, 117, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("€");
		lblNewLabel_1.setBounds(603, 122, 20, 23);
		contentPane.add(lblNewLabel_1);
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

