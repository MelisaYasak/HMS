package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Doctor extends User {

	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Doctor(int id, String tcno, String password, String name, String type) {
		super(id, tcno, password, name, type);
	}

	public Doctor() {
		super();
	}

	public boolean addWhour(int doctor_id, String doctor_name, String wdate) throws SQLException {
		String query = "INSERT INTO whour (doctor_id, doctor_name, wdate) VALUES (?, ?, ?)";
		int key = 0;
		int count = 0;
		Connection con = dbcon.connDb();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM whour WHERE status = 'a' AND doctor_id = " + doctor_id
					+ " AND wdate = '" + wdate + "'");
			while(rs.next()) {
				count++;
				break;
			}
			if(count == 0) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, doctor_id);
				preparedStatement.setString(2, doctor_name);
				preparedStatement.setString(3, wdate);
				preparedStatement.executeUpdate();
			}
			key = 1;
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
	public ArrayList<Whour> getWhourList(int doctor_id) throws SQLException{
		ArrayList<Whour> list = new ArrayList<>();
		Whour obj;
		Connection con = dbcon.connDb();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM whour WHERE status = 'a' AND doctor_id = "+ doctor_id);
			while(rs.next()) {
				obj = new Whour(rs.getInt("id"), rs.getInt("doctor_id"), rs.getString("doctor_name"),
						rs.getString("wdate"), rs.getString("status"));
				list.add(obj);
			}
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}finally{
			con.close();
		}
		return list;
	}
	
	public boolean deleteWhour(int id) throws SQLException {
		String query = "DELETE FROM whour WHERE id = ?";
		boolean key = false;
		Connection con = dbcon.connDb();
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			con.close();
		}
		if (key)
			return true;
		else
			return false;
	}
	
}
