package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Clinic;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import Helper.*;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;

public class UpdateClinicGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtclinicpopupName;
	private static Clinic clinic = new Clinic();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(clinic);
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
	public UpdateClinicGUI(Clinic clinic) {
		setTitle("POL\u0130KL\u0130N\u0130K G\u00DCNCELLEME");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 296, 167);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(UIManager.getColor("InternalFrame.inactiveTitleGradient"), 3));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblclinicpopupName = new JLabel("Poliklinik Ad\u0131");
		lblclinicpopupName.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		lblclinicpopupName.setBounds(35, 20, 200, 25);
		contentPane.add(lblclinicpopupName);

		txtclinicpopupName = new JTextField();
		txtclinicpopupName.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		txtclinicpopupName.setColumns(10);
		txtclinicpopupName.setBounds(35, 40, 200, 25);
		txtclinicpopupName.setText(clinic.getName());
		contentPane.add(txtclinicpopupName);

		JButton btnclinicpopupUpdate = new JButton("G\u00FCncelle");
		btnclinicpopupUpdate.setFont(new Font("Calibri", Font.BOLD, 16));
		btnclinicpopupUpdate.setBackground(UIManager.getColor("Button.light"));
		btnclinicpopupUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("update")) {
					try {
						clinic.updateClinic(clinic.getId(), txtclinicpopupName.getText());
						Helper.showMsg("success");
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnclinicpopupUpdate.setBounds(35, 80, 200, 35);
		contentPane.add(btnclinicpopupUpdate);
	}
}
