package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaRepartirDinero extends JFrame {

	private JPanel contentPane;

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
		
		JList list = new JList();
		list.setBounds(31, 83, 217, 225);
		contentPane.add(list);
		
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
}
