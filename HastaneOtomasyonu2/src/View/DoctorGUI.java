package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Doctor;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDayChooser;

import Helper.*;

import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;

public class DoctorGUI extends JFrame {

	private JPanel contentPane;
	static Doctor doctor = new Doctor();
	private JTable tableWhour;
	private DefaultTableModel whourModel;
	private Object[] whourData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
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
	public DoctorGUI(Doctor doctor) {
		setTitle("HASTANE OTOMASYON S\u0130STEM\u0130");
		setResizable(false);
		// Whour Model
		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2]; // String tanýmlanabilirdi!
		colWhour[0] = "ID";
		colWhour[1] = "TARÝH";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];
		try {
			for (int i = 0; i < doctor.getWhourList(doctor.getId()).size(); i++) {
				whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
				whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
				whourModel.addRow(whourData);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("InternalFrame.inactiveTitleGradient"));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("HO\u015EGELD\u0130N\u0130Z SAYIN " + doctor.getName());
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 20, 270, 30);
		contentPane.add(lblNewLabel);

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
		contentPane.add(btnExit);

		JTabbedPane tabpanedcWhour = new JTabbedPane(JTabbedPane.TOP);
		tabpanedcWhour.setFont(new Font("Arial", Font.PLAIN, 14));
		tabpanedcWhour.setBounds(10, 65, 715, 390);
		contentPane.add(tabpanedcWhour);

		JPanel paneldcWhour = new JPanel();
		paneldcWhour.setBorder(new LineBorder(new Color(0, 153, 204), 3, true));
		paneldcWhour.setBackground(Color.WHITE);
		tabpanedcWhour.addTab("Çalýþma Saati", null, paneldcWhour, null);
		paneldcWhour.setLayout(null);

		JDateChooser dateChooserdc = new JDateChooser();
		dateChooserdc.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 12));
		dateChooserdc.setBounds(10, 9, 155, 35);
		paneldcWhour.add(dateChooserdc);

		JComboBox combodcTime = new JComboBox();
		combodcTime.setBackground(UIManager.getColor("ComboBox.background"));
		combodcTime.setModel(new DefaultComboBoxModel(new String[] { "10.00", "10.30", "11.00", "11.30", "12.00",
				"13.00", "13.30", "14.00", "14.30", "15.00", "15.30" }));
		combodcTime.setBounds(180, 9, 80, 35);
		paneldcWhour.add(combodcTime);

		JButton btndcDateAdd = new JButton("EKLE");
		btndcDateAdd.setBackground(UIManager.getColor("Button.light"));
		btndcDateAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = "";
				try {
					date = sdf.format(dateChooserdc.getDate());
				} catch (Exception e2) {

				}
				if (date.length() == 0) {
					Helper.showMsg("Lütfen geçerli bir tarih giriniz!");
				} else {
					String time = " " + combodcTime.getSelectedItem().toString() + ":00";
					String selectedDate = date + time;
					try {
						boolean control = doctor.addWhour(doctor.getId(), doctor.getName(), selectedDate);
						if (control) {
							Helper.showMsg("success");
							updateWhourModel(doctor);
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
		btndcDateAdd.setBounds(270, 9, 145, 35);
		btndcDateAdd.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		paneldcWhour.add(btndcDateAdd);

		JScrollPane scrollWhour = new JScrollPane();
		scrollWhour.setBounds(10, 54, 695, 295);
		paneldcWhour.add(scrollWhour);

		tableWhour = new JTable(whourModel);
		tableWhour.setFont(new Font("Arial", Font.PLAIN, 13));
		scrollWhour.setViewportView(tableWhour);

		JButton btndcDateDelete = new JButton("S\u0130L");
		btndcDateDelete.setBackground(UIManager.getColor("Button.light"));
		btndcDateDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectRow = tableWhour.getSelectedRow();
				if (selectRow >= 0) {
					String selRow = tableWhour.getModel().getValueAt(selectRow, 0).toString();
					int selId = Integer.parseInt(selRow);
					try {
						boolean control = doctor.deleteWhour(selId);
						if (control) {
							Helper.showMsg("success");
							updateWhourModel(doctor);
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					Helper.showMsg("Lütfen bir tarih giriniz!");
				}

			}
		});
		btndcDateDelete.setFont(new Font("Yu Gothic UI", Font.PLAIN, 16));
		btndcDateDelete.setBounds(555, 9, 145, 35);
		paneldcWhour.add(btndcDateDelete);
	}

	public void updateWhourModel(Doctor doctor) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) tableWhour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < doctor.getWhourList(doctor.getId()).size(); i++) {
			whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
		}
	}
}
