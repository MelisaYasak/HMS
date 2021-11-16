package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.DBConnention;
import Helper.Helper;
import Model.Bashekim;
import Model.Doctor;
import Model.Patient;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField txtpatientTcno;
	private JTextField txtdocTcno;
	private JPasswordField txtpatientPassword;
	Patient patient = new Patient();
	private DBConnention con = new DBConnention();
	private JPasswordField txtdocPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setTitle("HASTANAE YÖNETÝM SÝSTEMÝ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 620);
		w_pane = new JPanel();
		w_pane.setBackground(UIManager.getColor("InternalFrame.inactiveTitleGradient"));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel labelimage = new JLabel(new ImageIcon(getClass().getResource("hospital.jpeg")));
		labelimage.setBackground(Color.RED);
		labelimage.setForeground(Color.WHITE);
		labelimage.setBounds(220, 22, 234, 206);
		w_pane.add(labelimage);

		JLabel labeltext = new JLabel("Hastane Y\u00F6netim Sistemine Ho\u015Fgeldiniz!");
		labeltext.setBounds(145, 239, 381, 40);
		labeltext.setFont(new Font("Yu Gothic", Font.BOLD | Font.ITALIC, 19));
		w_pane.add(labeltext);

		JTabbedPane tabpaneLogin = new JTabbedPane(JTabbedPane.TOP);
		tabpaneLogin.setFont(new Font("Arial", Font.PLAIN, 14));
		tabpaneLogin.setBackground(Color.WHITE);
		tabpaneLogin.setBounds(65, 276, 551, 275);
		w_pane.add(tabpaneLogin);

		JPanel panelPatient = new JPanel();
		panelPatient.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		panelPatient.setBackground(new Color(255, 255, 255));
		tabpaneLogin.addTab("Hasta Giriþi", null, panelPatient, null);
		panelPatient.setLayout(null);

		JLabel lblTcNo = new JLabel("T.C. Numaran\u0131z:");
		lblTcNo.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		lblTcNo.setBounds(60, 40, 135, 35);
		panelPatient.add(lblTcNo);

		JLabel lblpassword = new JLabel("\u015Eifreniz:");
		lblpassword.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		lblpassword.setBounds(60, 100, 135, 35);
		panelPatient.add(lblpassword);

		txtpatientTcno = new JTextField();
		txtpatientTcno.setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		txtpatientTcno.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		txtpatientTcno.setBounds(245, 40, 240, 35);
		panelPatient.add(txtpatientTcno);
		txtpatientTcno.setColumns(10);

		JButton btnpatientSignin = new JButton("Kay\u0131t Ol");
		btnpatientSignin.setBackground(UIManager.getColor("Button.light"));
		btnpatientSignin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGuý = new RegisterGUI();
				rGuý.setVisible(true);
				dispose();
			}
		});
		btnpatientSignin.setFont(new Font("Calibri", Font.BOLD, 16));
		btnpatientSignin.setBounds(70, 160, 150, 40);
		panelPatient.add(btnpatientSignin);

		JButton btnpatientLogin = new JButton("Giri\u015F Yap");
		btnpatientLogin.setBackground(UIManager.getColor("Button.light"));
		btnpatientLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtpatientTcno.getText().length() == 0 || txtpatientPassword.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						Connection conn = con.connDb();
						Statement st = conn.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user ");
						while (rs.next()) {
							if (txtpatientTcno.getText().equals(rs.getString("tcno"))
									&& txtpatientPassword.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("Hasta")) {
									Patient p = new Patient(rs.getInt("id"), rs.getString("tcno"),
											rs.getString("password"), rs.getString("name"), rs.getString("type"));
									PatientGUI pGUI = new PatientGUI(p);
									pGUI.setVisible(true);
									dispose();
									break;
								} else {
									Helper.showMsg("wrong");
								}
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		btnpatientLogin.setFont(new Font("Calibri", Font.BOLD, 16));
		btnpatientLogin.setBounds(295, 160, 150, 40);
		panelPatient.add(btnpatientLogin);

		txtpatientPassword = new JPasswordField();
		txtpatientPassword.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		txtpatientPassword.setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		txtpatientPassword.setBounds(245, 100, 240, 35);
		panelPatient.add(txtpatientPassword);

		JPanel panelDoctor = new JPanel();
		panelDoctor.setBorder(new LineBorder(new Color(0, 153, 204), 3, true));
		panelDoctor.setBackground(Color.WHITE);
		tabpaneLogin.addTab("Doktor Giriþi", null, panelDoctor, null);
		panelDoctor.setLayout(null);

		JLabel lbldocTcNo = new JLabel("T.C. Numaran\u0131z:");
		lbldocTcNo.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		lbldocTcNo.setBounds(60, 40, 135, 35);
		panelDoctor.add(lbldocTcNo);

		txtdocTcno = new JTextField();
		txtdocTcno.setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		txtdocTcno.setForeground(Color.BLACK);
		txtdocTcno.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		txtdocTcno.setColumns(10);
		txtdocTcno.setBounds(245, 40, 240, 35);
		panelDoctor.add(txtdocTcno);

		JLabel lbldocpassword = new JLabel("\u015Eifreniz:");
		lbldocpassword.setFont(new Font("Yu Gothic", Font.BOLD, 16));
		lbldocpassword.setBounds(60, 100, 135, 35);
		panelDoctor.add(lbldocpassword);

		JButton btndocLogin = new JButton("Giri\u015F Yap");
		btndocLogin.setBackground(UIManager.getColor("Button.light"));
		btndocLogin.setForeground(new Color(0, 0, 0));
		btndocLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtdocPassword.getText().length() == 0 || txtdocTcno.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						Connection conn = con.connDb();
						Statement st = conn.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while (rs.next()) {
							if (txtdocTcno.getText().equals(rs.getString("tcno"))
									&& txtdocPassword.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("Bashekim")) {
									Bashekim bhekim = new Bashekim(rs.getInt("id"), rs.getString("tcno"),
											rs.getString("password"), rs.getString("name"), rs.getString("type"));
									BashekimGUI bGUI = new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();
								} else if (rs.getString("type").equals("Doktor")) {
									Doctor doctor = new Doctor(rs.getInt("id"), rs.getString("tcno"),
											rs.getString("password"), rs.getString("name"), rs.getString("type"));
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();
								} else {
									Helper.showMsg("wrong");
								}
							}
						}

					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		btndocLogin.setFont(new Font("Calibri", Font.BOLD, 16));
		btndocLogin.setBounds(185, 160, 150, 40);
		panelDoctor.add(btndocLogin);

		txtdocPassword = new JPasswordField();
		txtdocPassword.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		txtdocPassword.setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		txtdocPassword.setBounds(245, 100, 240, 35);
		panelDoctor.add(txtdocPassword);
	}
}
