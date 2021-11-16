package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Helper.*;
import Model.Appointment;
import Model.Bashekim;
import Model.Clinic;
import Model.Whour;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class BashekimGUI extends JFrame {
	public static Bashekim bashekim = new Bashekim();
	private JPanel w_pane;
	private JTextField txtbhName;
	private JTextField txtbhTcno;
	private JTextField txtbhKullaniciid;
	private JComboBox comboclinicworker = null;

	private JTable tableDoctor;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;

	public static Clinic clinic = new Clinic();
	private JTextField txtclinicName;
	private JPasswordField txtbhPassword;

	private JTable tableClinic;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;

	private JPopupMenu clinicMenu;
	private JTable tableWorker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
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
	public BashekimGUI(Bashekim bashekim) throws SQLException {
		// Doctor Model
		doctorModel = new DefaultTableModel();
		Object[] colDoctor = new Object[4]; // String tanımlanabilirdi!
		colDoctor[0] = "ID";
		colDoctor[1] = "T.C. No";
		colDoctor[2] = "Ad Soyad";
		colDoctor[3] = "Þifre";
		doctorModel.setColumnIdentifiers(colDoctor);
		doctorData = new Object[4];
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[2] = bashekim.getDoctorList().get(i).getName();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}

		// Clinic Model
		clinicModel = new DefaultTableModel();
		Object[] colClinic = new Object[2]; // String tanýmlanabilirdi!
		colClinic[0] = "ID";
		colClinic[1] = "POLÝKLÝNÝK ADI";
		clinicModel.setColumnIdentifiers(colClinic);
		clinicData = new Object[2];
		for (int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);
		}

		// Worker Model
		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "ID";
		colWorker[1] = "Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];

		setResizable(false);
		setTitle("HASTANAE YÖNETÝM SÝSTEMÝ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 510);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(215, 228, 242));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("HO\u015EGELD\u0130N\u0130Z SAYIN " + bashekim.getName());
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

		JTabbedPane tabbedpaneBh = new JTabbedPane(JTabbedPane.TOP);
		tabbedpaneBh.setFont(new Font("Arial", Font.PLAIN, 14));
		tabbedpaneBh.setBounds(10, 65, 715, 390);
		w_pane.add(tabbedpaneBh);

		JPanel panelbhdoctor = new JPanel();
		panelbhdoctor.setBorder(new LineBorder(new Color(0, 153, 204), 3, true));
		panelbhdoctor.setBackground(Color.WHITE);
		tabbedpaneBh.addTab("Doktor Yönetimi", null, panelbhdoctor, null);
		panelbhdoctor.setLayout(null);

		txtbhName = new JTextField();
		txtbhName.setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		txtbhName.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		txtbhName.setBounds(552, 40, 145, 25);
		panelbhdoctor.add(txtbhName);
		txtbhName.setColumns(10);

		txtbhTcno = new JTextField();
		txtbhTcno.setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		txtbhTcno.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		txtbhTcno.setColumns(10);
		txtbhTcno.setBounds(552, 98, 145, 25);
		panelbhdoctor.add(txtbhTcno);

		txtbhKullaniciid = new JTextField();
		txtbhKullaniciid.setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		txtbhKullaniciid.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		txtbhKullaniciid.setColumns(10);
		txtbhKullaniciid.setBounds(552, 282, 145, 25);
		panelbhdoctor.add(txtbhKullaniciid);

		JButton btnbhDelete = new JButton("S\u0130L");
		btnbhDelete.setBackground(UIManager.getColor("Button.light"));
		btnbhDelete.setForeground(Color.BLACK);
		btnbhDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtbhKullaniciid.getText().length() == 0) {
					Helper.showMsg("Lütfen geçerli bir doktor seçiniz!");
				} else {
					if (Helper.confirm("del")) {
						int selectID = Integer.parseInt(txtbhKullaniciid.getText());
						try {
							boolean control = bashekim.deleteDoctor(selectID);
							if (control) {
								Helper.showMsg("success");
								txtbhKullaniciid.setText(null);
								//Doctor.java daki deleteWhour ile düzenlenebilir mi?
								Whour wh = new Whour();
								wh.deleteWhours(selectID);
								Appointment ap = new Appointment();
								ap.deleteAppoints(selectID);
								bashekim.deleteWorkers(selectID);
								updateDoctorModel();
								updatecb();	
							}

						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnbhDelete.setFont(new Font("Calibri", Font.BOLD, 16));
		btnbhDelete.setBounds(552, 310, 145, 35);
		panelbhdoctor.add(btnbhDelete);

		JButton btnbhAdd = new JButton("EKLE");
		btnbhAdd.setBackground(UIManager.getColor("Button.light"));
		btnbhAdd.setForeground(Color.BLACK);
		btnbhAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtbhTcno.getText().length() == 0 || txtbhPassword.getText().length() == 0
						|| txtbhName.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = bashekim.addDoctor(txtbhTcno.getText(), txtbhPassword.getText(),
								txtbhName.getText());
						if (control) {
							Helper.showMsg("success");
							txtbhTcno.setText(null);
							txtbhPassword.setText(null);
							txtbhName.setText(null);
							updateDoctorModel();
							updatecb();
						}
					} catch (SQLException e1) {

						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		btnbhAdd.setFont(new Font("Calibri", Font.BOLD, 16));
		btnbhAdd.setBounds(552, 190, 145, 35);
		panelbhdoctor.add(btnbhAdd);

		JLabel lblbhname = new JLabel("Ad Soyad");
		lblbhname.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblbhname.setBounds(552, 21, 145, 25);
		panelbhdoctor.add(lblbhname);

		JLabel lblbhTcno = new JLabel("T.C. No");
		lblbhTcno.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblbhTcno.setBounds(552, 80, 145, 25);
		panelbhdoctor.add(lblbhTcno);

		JLabel lblbhpassword = new JLabel("\u015Eifre");
		lblbhpassword.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblbhpassword.setBounds(552, 134, 145, 25);
		panelbhdoctor.add(lblbhpassword);

		JLabel lblbhKullaniciid = new JLabel("Kullan\u0131c\u0131 ID");
		lblbhKullaniciid.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblbhKullaniciid.setBounds(552, 260, 145, 25);
		panelbhdoctor.add(lblbhKullaniciid);

		JScrollPane scrollpanebh_1 = new JScrollPane();
		scrollpanebh_1.setBounds(10, 11, 529, 336);
		panelbhdoctor.add(scrollpanebh_1);

		tableDoctor = new JTable(doctorModel);
		tableDoctor.setFont(new Font("Arial", Font.PLAIN, 13));
		tableDoctor.setBackground(new Color(255, 255, 255));
		scrollpanebh_1.setViewportView(tableDoctor);

		txtbhPassword = new JPasswordField();
		txtbhPassword.setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		txtbhPassword.setBounds(552, 156, 145, 25);
		panelbhdoctor.add(txtbhPassword);
		tableDoctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					txtbhKullaniciid.setText(tableDoctor.getValueAt(tableDoctor.getSelectedRow(), 0).toString());
				} catch (Exception ex) {
				}
			}
		});

		tableDoctor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectedID = Integer
							.parseInt(tableDoctor.getValueAt(tableDoctor.getSelectedRow(), 0).toString());
					String selectedName = tableDoctor.getValueAt(tableDoctor.getSelectedRow(), 2).toString();
					String selectedTcno = tableDoctor.getValueAt(tableDoctor.getSelectedRow(), 1).toString();
					String selectedPass = tableDoctor.getValueAt(tableDoctor.getSelectedRow(), 3).toString();
					try {
						boolean control = bashekim.updateDoctor(selectedID, selectedTcno, selectedPass, selectedName);
						if (control) {
							Helper.showMsg("success");
						}
					} catch (Exception e1) {

						e1.printStackTrace();
					}
				}
			}
		});

		JPanel panelclinic = new JPanel();
		panelclinic.setBorder(new LineBorder(new Color(0, 153, 204), 3, true));
		panelclinic.setBackground(Color.WHITE);
		panelclinic.setToolTipText("");
		tabbedpaneBh.addTab("Poliklinik", null, panelclinic, null);
		panelclinic.setLayout(null);

		JScrollPane scrolpaneclinic = new JScrollPane();
		scrolpaneclinic.setBounds(10, 11, 250, 335);
		panelclinic.add(scrolpaneclinic);

		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selId = Integer.parseInt(tableClinic.getValueAt(tableClinic.getSelectedRow(), 0).toString());
				Clinic selectClinic = null;
				try {
					selectClinic = clinic.getFetch(selId);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectClinic);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateClinicModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}

		});

		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("del")) {
					int selID = Integer.parseInt(tableClinic.getValueAt(tableClinic.getSelectedRow(), 0).toString());
					try {
						if (clinic.deleteClinic(selID)) {
							Helper.showMsg("success");
							updateClinicModel();
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		tableClinic = new JTable(clinicModel);
		tableClinic.setFont(new Font("Arial", Font.PLAIN, 13));
		tableClinic.setComponentPopupMenu(clinicMenu);
		tableClinic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = tableClinic.rowAtPoint(point);
				tableClinic.setRowSelectionInterval(selectedRow, selectedRow);
			}
		});
		scrolpaneclinic.setViewportView(tableClinic);

		JScrollPane scrollclinicWorker = new JScrollPane();
		scrollclinicWorker.setBounds(449, 11, 250, 335);
		panelclinic.add(scrollclinicWorker);

		tableWorker = new JTable();
		tableWorker.setFont(new Font("Arial", Font.PLAIN, 13));
		scrollclinicWorker.setViewportView(tableWorker);

		JLabel lblclinicName = new JLabel("Poliklinik Ad\u0131");
		lblclinicName.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblclinicName.setBounds(281, 26, 145, 25);
		panelclinic.add(lblclinicName);

		txtclinicName = new JTextField();
		txtclinicName.setBackground(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		txtclinicName.setFont(new Font("Yu Gothic UI", Font.PLAIN, 13));
		txtclinicName.setColumns(10);
		txtclinicName.setBounds(281, 43, 145, 25);
		panelclinic.add(txtclinicName);

		JButton btnclinicAdd = new JButton("EKLE");
		btnclinicAdd.setBackground(UIManager.getColor("Button.light"));
		btnclinicAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtclinicName.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = clinic.addClinic(txtclinicName.getText());
						if (control) {
							Helper.showMsg("success");
							updateClinicModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnclinicAdd.setFont(new Font("Calibri", Font.BOLD, 16));
		btnclinicAdd.setBounds(281, 77, 145, 35);
		panelclinic.add(btnclinicAdd);

		comboclinicworker = new JComboBox();
		comboclinicworker.setFont(new Font("Arial", Font.PLAIN, 13));
		comboclinicworker.setBounds(278, 255, 150, 35);
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			comboclinicworker.addItem(
					new Item(bashekim.getDoctorList().get(i).getId(), bashekim.getDoctorList().get(i).getName()));
		}
		panelclinic.add(comboclinicworker);

		JButton btnclinicworkerAdd = new JButton("EKLE");
		btnclinicworkerAdd.setBackground(UIManager.getColor("Button.light"));
		btnclinicworkerAdd.setFont(new Font("Calibri", Font.BOLD, 16));
		btnclinicworkerAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = tableClinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = tableClinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicId = Integer.parseInt(selClinic);
					Item doctorItem = (Item) comboclinicworker.getSelectedItem();
					try {
						boolean control = bashekim.addWorker(doctorItem.getKey(), selClinicId);
						if (control) {
							Helper.showMsg("success");
							int selClinicID = Integer.parseInt(selClinic);
							DefaultTableModel clearModel = (DefaultTableModel) tableWorker.getModel();
							clearModel.setRowCount(0);
							for (int i = 0; i < bashekim.getWorkerList(selClinicID).size(); i++) {
								workerData[0] = bashekim.getWorkerList(selClinicID).get(i).getId();
								workerData[1] = bashekim.getWorkerList(selClinicID).get(i).getName();
								workerModel.addRow(workerData);
							}
							tableWorker.setModel(workerModel);

						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					Helper.showMsg("Lütfen poliklinik seçiniz!");
				}
			}
		});
		btnclinicworkerAdd.setBounds(281, 303, 145, 35);
		panelclinic.add(btnclinicworkerAdd);

		JLabel lblclinicName_1 = new JLabel("Poliklinik Ad\u0131");
		lblclinicName_1.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblclinicName_1.setBounds(281, 165, 145, 25);
		panelclinic.add(lblclinicName_1);

		JButton btnclinicWorkerSelect = new JButton("SE\u00C7");
		btnclinicWorkerSelect.setBackground(UIManager.getColor("Button.light"));
		btnclinicWorkerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = tableClinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = tableClinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					DefaultTableModel clearModel = (DefaultTableModel) tableWorker.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < bashekim.getWorkerList(selClinicID).size(); i++) {
							workerData[0] = bashekim.getWorkerList(selClinicID).get(i).getId();
							workerData[1] = bashekim.getWorkerList(selClinicID).get(i).getName();
							workerModel.addRow(workerData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tableWorker.setModel(workerModel);
				} else {
					Helper.showMsg("Lütfen ");
				}
			}
		});
		btnclinicWorkerSelect.setFont(new Font("Calibri", Font.BOLD, 16));
		btnclinicWorkerSelect.setBounds(281, 184, 145, 35);
		panelclinic.add(btnclinicWorkerSelect);

	}

	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) tableDoctor.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[2] = bashekim.getDoctorList().get(i).getName();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
	}

	public void updateClinicModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) tableClinic.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);
		}
	}

	public void updatecb() throws SQLException {
		comboclinicworker.removeAllItems();
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			comboclinicworker.addItem(
					new Item(bashekim.getDoctorList().get(i).getId(), bashekim.getDoctorList().get(i).getName()));
		}
	}
}
