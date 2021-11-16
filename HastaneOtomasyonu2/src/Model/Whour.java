package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnention;

public class Whour {
	private int id, doctor_id;
	private String doctor_name, wdate, status;

	DBConnention dbcon = new DBConnention();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Whour(int id, int doctor_id, String doctor_name, String wdate, String status) {
		this.id = id;
		this.doctor_id = doctor_id;
		this.doctor_name = doctor_name;
		this.wdate = wdate;
		this.status = status;
	}

	public Whour() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public ArrayList<Whour> getWhourList(int doctor_id) throws SQLException {
		ArrayList<Whour> list = new ArrayList<>();
		Whour obj;
		Connection con = dbcon.connDb();
		try {

			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM whour WHERE status = 'a' AND doctor_id = " + doctor_id);
			while (rs.next()) {
				obj = new Whour(rs.getInt("id"), rs.getInt("doctor_id"), rs.getString("doctor_name"),
						rs.getString("wdate"), rs.getString("status"));
				list.add(obj);
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			con.close();
		}
		return list;
	}
	public void deleteWhours(int id) throws SQLException {
		String query = "DELETE FROM whour WHERE doctor_id = ?";
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
	
}
