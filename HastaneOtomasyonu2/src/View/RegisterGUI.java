package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Patient;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class RegisterGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtregName;
	private JTextField txtregTcno;
	private JPasswordField pfregpassword;
	private Patient patient = new Patient();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setTitle("HASTANE KAYIT S\u0130STEM\u0130");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(UIManager.getColor("InternalFrame.inactiveTitleGradient"), 3));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblregName = new JLabel("Ad Soyad");
		lblregName.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		lblregName.setBounds(10, 15, 265, 20);
		contentPane.add(lblregName);

		txtregName = new JTextField();
		txtregName.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		txtregName.setColumns(10);
		txtregName.setBounds(10, 30, 265, 30);
		contentPane.add(txtregName);

		JLabel lblregTcno = new JLabel("T.C. No");
		lblregTcno.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		lblregTcno.setBounds(10, 73, 265, 20);
		contentPane.add(lblregTcno);

		txtregTcno = new JTextField();
		txtregTcno.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		txtregTcno.setColumns(10);
		txtregTcno.setBounds(10, 94, 265, 30);
		contentPane.add(txtregTcno);

		pfregpassword = new JPasswordField();
		pfregpassword.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		pfregpassword.setBounds(10, 155, 265, 30);
		contentPane.add(pfregpassword);

		JLabel lblregPassword = new JLabel("\u015Eifre");
		lblregPassword.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		lblregPassword.setBounds(10, 135, 265, 20);
		contentPane.add(lblregPassword);

		JButton btnregRegister = new JButton("Kay\u0131t Ol");
		btnregRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtregName.getText().length() == 0 || txtregTcno.getText().length() == 0
						|| pfregpassword.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = patient.addPatient(txtregTcno.getText(), pfregpassword.getText(),
								txtregName.getText());
						if (control) {
							Helper.showMsg("success");
							LoginGUI lGuý = new LoginGUI();
							lGuý.setVisible(true);
							dispose();
						} else
							Helper.showMsg("error");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		btnregRegister.setBackground(UIManager.getColor("Button.light"));
		btnregRegister.setFont(new Font("Calibri", Font.BOLD, 16));
		btnregRegister.setBounds(10, 196, 265, 35);
		contentPane.add(btnregRegister);

		JButton btnregBack = new JButton("Geri D\u00F6n");
		btnregBack.setBackground(UIManager.getColor("Button.light"));
		btnregBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI lGuý = new LoginGUI();
				lGuý.setVisible(true);
				dispose();
			}
		});
		btnregBack.setFont(new Font("Calibri", Font.BOLD, 16));
		btnregBack.setBounds(10, 245, 265, 35);
		contentPane.add(btnregBack);
	}
}
