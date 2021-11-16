package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnention;

public class Appointment {
	private int id, doctorId, patientId;
	private String doctorName, patientName, appoDate;

	DBConnention dbcon = new DBConnention();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Appointment(int id, int doctorId, String doctorName, int patientId, String patientName, String appoDate) {
		this.id = id;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.doctorName = doctorName;
		this.patientName = patientName;
		this.appoDate = appoDate;
	}

	public Appointment() {
	}

	public ArrayList<Appointment> getDoctorAppointmenList(int doctorID) throws SQLException {
		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obj;
		Connection con = dbcon.connDb();

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM appointment WHERE doctor_id = " + doctorID);
			while (rs.next()) {
				obj = new Appointment(rs.getInt("id"), rs.getInt("doctor_id"), rs.getString("doctor_name"),
						rs.getInt("patient_id"), rs.getString("patient_name"), rs.getString("appo_date"));
				list.add(obj);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			con.close();
		}
		return list;
	}

	public ArrayList<Appointment> getPatientAppointmentList(int patientID) throws SQLException {
		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obj;
		Connection con = dbcon.connDb();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM appointment WHERE patient_id = " + patientID);
			while (rs.next()) {
				obj = new Appointment(rs.getInt("id"), rs.getInt("doctor_id"), rs.getString("doctor_name"),
						rs.getInt("patient_id"), rs.getString("patient_name"), rs.getString("appo_date"));
				list.add(obj);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			con.close();
		}
		return list;
	}

	public boolean deleteMyAppoint(int id) throws SQLException {
		String query = "DELETE FROM appointment WHERE id = ?";
		boolean key = false;
		Connection con = dbcon.connDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		if (key)
			return true;
		else
			return false;
	}

	// for after deleteMyAppoint fun, to update status to 'a' in whour table.
	public void updateStatus(String wdate, String doctorName) throws SQLException {
		String query = "UPDATE whour SET status = ? WHERE doctor_name = ? AND wdate = ?";
		Connection con = dbcon.connDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, "a");
			preparedStatement.setString(2, doctorName);
			preparedStatement.setString(3, wdate);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		
	}

	public void deleteAppoints(int id) throws SQLException {
		String query = "DELETE FROM appointment WHERE doctor_id = ?";
		Connection con = dbcon.connDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getAppoDate() {
		return appoDate;
	}

	public void setAppoDate(String appoDate) {
		this.appoDate = appoDate;
	}

}
