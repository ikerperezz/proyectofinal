package ventanas;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDatos.DBManager;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaClasificacionGeneral extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public VentanaClasificacionGeneral() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 324, 119);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		DBManager dbmanager= new DBManager();
		dbmanager.conectar();
		List<Integer> ar = dbmanager.sacarpuntos();
		dbmanager.disconnect();
		int max = findMax(ar, ar.size());
		int min = findMin(ar, ar.size());
		
		
		JLabel lblNewLabel_1 = new JLabel("El usuario que menos puntos lleva tiene " + min +" puntos");
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("El usuario que más puntos lleva tiene " + max +" puntos");
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazDeUsuarioPublico v = new InterfazDeUsuarioPublico(null, null, null);
				v.setVisible(true);
				VentanaClasificacionGeneral.this.setVisible(false);
			}
		});
		contentPane.add(btnNewButton);
		
		
	}

	public static int findMax(List<Integer> ar, int n) {
	    if (n == 1) {
	        return ar.get(0);
	    }
	    return Math.max(ar.get(n - 1), findMax(ar, n - 1));
	}
	
	public static int findMin(List<Integer> arr, int n) {
	    if (n == 1) {
	        return arr.get(0);
	    }
	    return Math.min(arr.get(n - 1), findMin(arr, n - 1));
	}
}
