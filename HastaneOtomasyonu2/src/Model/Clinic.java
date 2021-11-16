package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnention;

public class Clinic {
	
	private int id;
	private String name;
	
	DBConnention dbcon = new DBConnention();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	public Clinic() {}
	
	public Clinic(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public ArrayList<Clinic> getList() throws SQLException{
		ArrayList<Clinic> list = new ArrayList<>();
		Clinic obj;
		Connection con = dbcon.connDb();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM clinic");
			while(rs.next()) {
				obj = new Clinic(rs.getInt("id"), rs.getString("name"));
				list.add(obj);
			}
		}catch(SQLException e1) {
			e1.printStackTrace();
		}finally{
			con.close();
		}
		return list;
	}
	public boolean addClinic(String name) throws SQLException {
		Connection con = dbcon.connDb();
		String query = "INSERT INTO clinic (name) VALUES (?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.executeUpdate();
			key = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		if (key)
			return true;
		else
			return false;
		}
	
	public boolean deleteClinic(int id) throws SQLException {
		String query = "DELETE FROM clinic WHERE id = ?";
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
		}finally{
			con.close();
		}
		if (key)
			return true;
		else
			return false;
	}
	public boolean updateClinic(int id, String name) throws SQLException {
		String query = "UPDATE clinic SET name = ? WHERE id = ?";
		boolean key = false;
		Connection con = dbcon.connDb();

		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			key = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			con.close();
		}
		if (key)
			return true;
		else
			return false;
	}
	
	public Clinic getFetch(int id) throws SQLException {
		Clinic obj = new Clinic();
		Connection con = dbcon.connDb();

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM clinic WHERE id =" + id);
			while(rs.next()) {
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				break;
			}
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}finally{
			con.close();
		}
		return obj;
	}
	
	public ArrayList<User> getWorkerList(int clinic_id) throws SQLException{
		ArrayList<User> list = new ArrayList<>();
		User obj;
		Connection con = dbcon.connDb();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT u.id, u.tcno, u.password, u.name, u.type FROM worker w LEFT JOIN user u ON w.user_id = u.id WHERE clinic_id="+ clinic_id);
			while(rs.next()) {
				obj = new User(rs.getInt("u.id"), rs.getString("u.tcno"), rs.getString("u.password"), rs.getString("u.name"), rs.getString("u.type"));
				list.add(obj);
			}
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}finally{
			con.close();
		}
		return list;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
