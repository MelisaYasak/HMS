package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.Helper;

public class Patient extends User {
	
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	public Patient() {
		
	}

	public Patient(int id, String tcno, String password, String name, String type) {
		super(id, tcno, password, name, type);
	}
	
	public boolean addPatient(String tcno, String password, String name) throws SQLException {
		String query = "INSERT INTO user (tcno, password, name, type) VALUES (?, ?, ?, ?)";
		int key = 0;
		boolean duplicate = false;
		Connection con = dbcon.connDb();

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE tcno = '" + tcno + "'");
			while(rs.next()) {
				duplicate = true;
				Helper.showMsg("Bu T.C. Numarasýna ait kayýt bulunmaktadýr.");
				break;
			}
			if(!duplicate) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, tcno);
				preparedStatement.setString(2,password);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, "Hasta");
				preparedStatement.executeUpdate();
				key = 1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			con.close();
		}
		if(key == 1)
			return true;
		else
			return false;

	}
	public boolean addAppointment(int doctor_id, String doctor_name, int patient_id, String patient_name, String appoDate) throws SQLException {

		String query = "INSERT INTO appointment (doctor_id, doctor_name, patient_id, patient_name, appo_date) VALUES (?,?,?,?,?)";
		int key = 0;
		Connection con = dbcon.connDb();

		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, doctor_id);
			preparedStatement.setString(2, doctor_name);
			preparedStatement.setInt(3, patient_id);
			preparedStatement.setString(4, patient_name);
			preparedStatement.setString(5, appoDate);
			preparedStatement.executeUpdate();
			key = 1;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			con.close();
		}
		if (key == 1)
			return true;
		else
			return false;
	}
	
	public boolean updateWhourStatus(int doctor_id, String wDate) throws SQLException {

		String query = "UPDATE whour SET status = ? WHERE doctor_id = ? AND wdate = ? ";
		int key = 0;
		Connection con = dbcon.connDb();

		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, "p");
			preparedStatement.setInt(2, doctor_id);
			preparedStatement.setString(3, wDate);
			preparedStatement.executeUpdate();
			key = 1;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			con.close();
		}
		if (key == 1)
			return true;
		else
			return false;
		}
	
	
	
	
}
