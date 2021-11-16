package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Appointment;
import Model.Bashekim;
import Model.Clinic;
import Model.Patient;
import Model.Whour;

import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class PatientGUI extends JFrame {

	private JPanel w_pane;
	static Patient patient = new Patient();

	private JTable tablepDoctorList;
	private DefaultTableModel doctorModel;
	private Object[] doctorData = null;

	private Clinic clinic = new Clinic();

	private JTable tablepWhour;
	private Whour whour = new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourData = null;

	private int selectDoctorID = 0;
	private String selectDoctorName = null;

	private Appointment appoint = new Appointment();
	private JPopupMenu appointMenu = null;
	private JTable tablepMyAppoint;
	private DefaultTableModel appointModel;
	private Object[] appointData = null;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientGUI frame = new PatientGUI(patient);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public PatientGUI(Patient patient) throws SQLException {

		// Doctor Model
		doctorModel = new DefaultTableModel();
		Object[] colDoctor = new Object[2]; // String tanýmlanabilirdi!
		colDoctor[0] = "ID";
		colDoctor[1] = "Ad Soyad";
		doctorModel.setColumnIdentifiers(colDoctor);
		doctorData = new Object[2];

		// Whour Model
		whourModel = new DefaultTableModel();
		Object[] colwhour = new Object[2]; // String tanýmlanabilirdi!
		colwhour[0] = "ID";
		colwhour[1] = "TARÝH";
		whourModel.setColumnIdentifiers(colwhour);
		whourData = new Object[2];

		// Appoint Model
		appointModel = new DefaultTableModel();
		Object[] colAppoint = new Object[3]; // String tanýmlanabilirdi!
		colAppoint[0] = "ID";
		colAppoint[1] = "DOKTOR";
		colAppoint[2] = "TARÝH";
		appointModel.setColumnIdentifiers(colAppoint);
		appointData = new Object[3];
		for (int i = 0; i < appoint.getPatientAppointmentList(patient.getId()).size(); i++) {
			appointData[0] = appoint.getPatientAppointmentList(patient.getId()).get(i).getId();
			appointData[1] = appoint.getPatientAppointmentList(patient.getId()).get(i).getDoctorName();
			appointData[2] = appoint.getPatientAppointmentList(patient.getId()).get(i).getAppoDate();
			appointModel.addRow(appointData);
		}

		setResizable(false);
		setTitle("HASTANAE YÖNETÝM SÝSTEMÝ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(UIManager.getColor("InternalFrame.inactiveTitleGradient"));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("HO\u015EGELD\u0130N\u0130Z SAYIN " + patient.getName());
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 20, 270, 30);
		w_pane.add(lblNewLabel);

		JButton btnExit = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnExit.setBackground(UIManager.getColor("Button.light"));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI lguý = new LoginGUI();
				lguý.setVisible(true);
				dispose();
			}
		});
		btnExit.setFont(new Font("Calibri", Font.BOLD, 16));
		btnExit.setBounds(575, 20, 150, 35);
		w_pane.add(btnExit);

		JTabbedPane tabpanePatient = new JTabbedPane(JTabbedPane.TOP);
		tabpanePatient.setFont(new Font("Arial", Font.PLAIN, 14));
		tabpanePatient.setBounds(10, 65, 715, 390);
		w_pane.add(tabpanePatient);

		JPanel panelAppoint = new JPanel();
		panelAppoint.setBorder(new LineBorder(new Color(0, 153, 204), 3, true));
		panelAppoint.setBackground(Color.WHITE);
		tabpanePatient.addTab("Randevu Sistemi", null, panelAppoint, null);
		panelAppoint.setLayout(null);

		JScrollPane scrollpDoctorList = new JScrollPane();
		scrollpDoctorList.setBounds(10, 35, 255, 315);
		panelAppoint.add(scrollpDoctorList);

		tablepDoctorList = new JTable(doctorModel);
		tablepDoctorList.setFont(new Font("Arial", Font.PLAIN, 13));
		scrollpDoctorList.setViewportView(tablepDoctorList);

		JLabel lblpDoctorList = new JLabel("Doktor Listesi");
		lblpDoctorList.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblpDoctorList.setBounds(10, 11, 104, 15);
		panelAppoint.add(lblpDoctorList);

		JLabel lblpClinicName = new JLabel("Poliklinik Ad\u0131");
		lblpClinicName.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblpClinicName.setBounds(280, 63, 112, 15);
		panelAppoint.add(lblpClinicName);

		JComboBox combopClinicList = new JComboBox();
		combopClinicList.setFont(new Font("Arial", Font.PLAIN, 13));
		combopClinicList.setBounds(275, 89, 165, 35);
		combopClinicList.addItem("--Poliklinik Seç--");
		combopClinicList.addActionListener(new ActionListener() {
			// Bu Item ile aldýðýmýz poliklinik ve idleri terminale yazdýrmak için
			@Override
			public void actionPerformed(ActionEvent e) {
				if (combopClinicList.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) tablepDoctorList.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < clinic.getWorkerList(item.getKey()).size(); i++) {
							doctorData[0] = clinic.getWorkerList(item.getKey()).get(i).getId();
							doctorData[1] = clinic.getWorkerList(item.getKey()).get(i).getName();
							doctorModel.addRow(doctorData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					DefaultTableModel clearModel = (DefaultTableModel) tablepDoctorList.getModel();
					clearModel.setRowCount(0);
				}
			}

		});
		panelAppoint.add(combopClinicList);

		for (int i = 0; i < clinic.getList().size(); i++) {
			combopClinicList.addItem(new Item(clinic.getList().get(i).getId(), clinic.getList().get(i).getName()));
		}

		JLabel lblDoktorIsmi = new JLabel("Doktor \u0130smi");
		lblDoktorIsmi.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblDoktorIsmi.setBounds(284, 175, 127, 20);
		panelAppoint.add(lblDoktorIsmi);

		JButton btnpDoctorSelect = new JButton("Se\u00E7");
		btnpDoctorSelect.setBackground(UIManager.getColor("Button.light"));
		btnpDoctorSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tablepDoctorList.getSelectedRow();
				if (row >= 0) {
					String value = tablepDoctorList.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) tablepWhour.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < whour.getWhourList(id).size(); i++) {
							whourData[0] = whour.getWhourList(id).get(i).getId();
							whourData[1] = whour.getWhourList(id).get(i).getWdate();
							whourModel.addRow(whourData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					selectDoctorID = id;
					selectDoctorName = tablepDoctorList.getModel().getValueAt(row, 1).toString();
				} else {
					Helper.showMsg("Lütfen bir doktor seçiniz!");
				}
			}
		});
		btnpDoctorSelect.setFont(new Font("Calibri", Font.BOLD, 16));
		btnpDoctorSelect.setBounds(284, 206, 145, 35);
		panelAppoint.add(btnpDoctorSelect);

		JLabel lblalmaSaatleri = new JLabel("\u00C7al\u0131\u015Fma Saatleri");
		lblalmaSaatleri.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblalmaSaatleri.setBounds(468, 11, 104, 15);
		panelAppoint.add(lblalmaSaatleri);

		JScrollPane scrollpDoctorList_1 = new JScrollPane();
		scrollpDoctorList_1.setBounds(450, 35, 255, 315);
		panelAppoint.add(scrollpDoctorList_1);

		tablepWhour = new JTable(whourModel);
		tablepWhour.setFont(new Font("Arial", Font.PLAIN, 13));
		scrollpDoctorList_1.setViewportView(tablepWhour);

		JButton btnpAppoint = new JButton("RANDEVU AL");
		btnpAppoint.setBackground(UIManager.getColor("Button.light"));
		btnpAppoint.setFont(new Font("Calibri", Font.BOLD, 16));
		btnpAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = tablepWhour.getSelectedRow();
				if (selRow >= 0) {
					String date = tablepWhour.getModel().getValueAt(selRow, 1).toString();
					try {
						boolean control = patient.addAppointment(selectDoctorID, selectDoctorName, patient.getId(),
								patient.getName(), date);
						if (control) {
							Helper.showMsg("success");
							patient.updateWhourStatus(selectDoctorID, date);
							updateWhourModel(selectDoctorID);
							updateAppointModel(patient.getId());
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					Helper.showMsg("Lütfen geçerli bir tarih giriniz!");
				}
			}
		});
		btnpAppoint.setBounds(284, 284, 145, 35);
		panelAppoint.add(btnpAppoint);

		JLabel lblNewLabel_1 = new JLabel("Randevu");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblNewLabel_1.setBounds(284, 264, 127, 20);
		panelAppoint.add(lblNewLabel_1);

		JPanel panelMyAppoint = new JPanel();
		panelMyAppoint.setBorder(new LineBorder(new Color(0, 153, 204), 3, true));
		panelMyAppoint.setBackground(Color.WHITE);
		tabpanePatient.addTab("Randevularým", null, panelMyAppoint, null);
		panelMyAppoint.setLayout(null);

		JScrollPane scrollpMyAppoint = new JScrollPane();
		scrollpMyAppoint.setBounds(10, 10, 690, 340);
		panelMyAppoint.add(scrollpMyAppoint);
		
		appointMenu = new JPopupMenu();
		JMenuItem deleteMenu = new JMenuItem("SÝL");
		appointMenu.add(deleteMenu);
		
		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("del")) {
					int selID = Integer.parseInt(tablepMyAppoint.getValueAt(tablepMyAppoint.getSelectedRow(), 0).toString());
					String selDName = tablepMyAppoint.getValueAt(tablepMyAppoint.getSelectedRow(), 1).toString();
					String selWdate = tablepMyAppoint.getValueAt(tablepMyAppoint.getSelectedRow(), 2).toString();
					try {
						if(appoint.deleteMyAppoint(selID)) {
						Helper.showMsg("success");
						updateAppointModel(patient.getId());
						//FIXME : Ýsme göre deðil de idye göre almak için Item kullan.
						appoint.updateStatus(selWdate, selDName);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			
		});

		tablepMyAppoint = new JTable(appointModel);
		tablepMyAppoint.setFont(new Font("Arial"	, Font.PLAIN, 13));
		tablepMyAppoint.setComponentPopupMenu(appointMenu);
		tablepMyAppoint.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = tablepMyAppoint.rowAtPoint(point);
				tablepMyAppoint.setRowSelectionInterval(selectedRow, selectedRow);
			}
		});
		scrollpMyAppoint.setViewportView(tablepMyAppoint);
		tablepWhour.getColumnModel().getColumn(0).setPreferredWidth(5);

	}

	public void updateWhourModel(int doctor_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) tablepWhour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < whour.getWhourList(doctor_id).size(); i++) {
			whourData[0] = whour.getWhourList(doctor_id).get(i).getId();
			whourData[1] = whour.getWhourList(doctor_id).get(i).getWdate();
			whourModel.addRow(whourData);
		}
	}

	public void updateAppointModel(int patient_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) tablepMyAppoint.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < appoint.getPatientAppointmentList(patient_id).size(); i++) {
			appointData[0] = appoint.getPatientAppointmentList(patient_id).get(i).getId();
			appointData[1] = appoint.getPatientAppointmentList(patient_id).get(i).getDoctorName();
			appointData[2] = appoint.getPatientAppointmentList(patient_id).get(i).getAppoDate();
			appointModel.addRow(appointData);
		}
	}
}